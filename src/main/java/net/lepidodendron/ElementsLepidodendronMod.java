package net.lepidodendron;

import net.lepidodendron.gui.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.*;
import java.util.function.Supplier;

public class ElementsLepidodendronMod implements IFuelHandler, IWorldGenerator {
	public final List<ModElement> elements = new ArrayList<>();
	public final List<Supplier<Block>> blocks = new ArrayList<>();
	public final List<Supplier<Item>> items = new ArrayList<>();
	public final List<Supplier<Biome>> biomes = new ArrayList<>();
	public final List<Supplier<EntityEntry>> entities = new ArrayList<>();
	public final List<Supplier<Potion>> potions = new ArrayList<>();
	public static Map<ResourceLocation, net.minecraft.util.SoundEvent> sounds = new HashMap<>();
	public ElementsLepidodendronMod() {
		sounds.put(new ResourceLocation("lepidodendron", "wet_crunch_plants"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "wet_crunch_plants")));
		sounds.put(new ResourceLocation("lepidodendron", "dry_crunch_plants"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dry_crunch_plants")));
		sounds.put(new ResourceLocation("lepidodendron", "respawner"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "respawner")));

		sounds.put(new ResourceLocation("lepidodendron", "eoarthropleura_idle"),
			new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoarthropleura_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "eoarthropleura_hurt"),
			new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoarthropleura_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "eoarthropleura_death"),
			new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoarthropleura_death")));
		sounds.put(new ResourceLocation("lepidodendron", "pneudodesmus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pneudodesmus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "pneudodesmus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pneudodesmus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pneudodesmus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pneudodesmus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "palaeodictyoptera_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "palaeodictyoptera_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "palaeodictyoptera_flight"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "palaeodictyoptera_flight")));

		sounds.put(new ResourceLocation("lepidodendron", "limnoscelis_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "limnoscelis_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "limnoscelis_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "limnoscelis_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "limnoscelis_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "limnoscelis_death")));

		sounds.put(new ResourceLocation("lepidodendron", "amphibamus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "amphibamus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "amphibamus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "amphibamus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "amphibamus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "amphibamus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "ichthyostega_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ichthyostega_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "ichthyostega_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ichthyostega_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "ichthyostega_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ichthyostega_death")));

		sounds.put(new ResourceLocation("lepidodendron", "trigonotarbid_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "trigonotarbid_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "trigonotarbid_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "trigonotarbid_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "trigonotarbid_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "trigonotarbid_death")));

		sounds.put(new ResourceLocation("lepidodendron", "megalocephalus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalocephalus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "megalocephalus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalocephalus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "megalocephalus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalocephalus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "hylonomus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hylonomus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "hylonomus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hylonomus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "hylonomus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hylonomus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "moschops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "moschops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "moschops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "moschops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "moschops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "moschops_death")));

		sounds.put(new ResourceLocation("lepidodendron", "eryops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eryops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "eryops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eryops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "eryops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eryops_death")));

		sounds.put(new ResourceLocation("lepidodendron", "cotylorhynchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cotylorhynchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "cotylorhynchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cotylorhynchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "cotylorhynchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cotylorhynchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "ophiacodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ophiacodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "ophiacodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ophiacodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "ophiacodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ophiacodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "dimetrodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dimetrodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "dimetrodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dimetrodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "dimetrodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dimetrodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "inostrancevia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "inostrancevia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "inostrancevia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "inostrancevia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "inostrancevia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "inostrancevia_death")));

		sounds.put(new ResourceLocation("lepidodendron", "gorgonops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "gorgonops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "gorgonops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "gorgonops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "gorgonops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "gorgonops_death")));

		sounds.put(new ResourceLocation("lepidodendron", "sphenacodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sphenacodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "sphenacodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sphenacodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "sphenacodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sphenacodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "pederpes_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pederpes_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "pederpes_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pederpes_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pederpes_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pederpes_death")));

		sounds.put(new ResourceLocation("lepidodendron", "scutosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "scutosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "scutosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "scutosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "scutosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "scutosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "vivaxosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vivaxosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "vivaxosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vivaxosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "vivaxosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vivaxosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "estemmenosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "estemmenosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "estemmenosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "estemmenosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "estemmenosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "estemmenosuchus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "estemmenosuchus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "estemmenosuchus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "diictodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diictodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "diictodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diictodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "diictodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diictodon_death")));
		sounds.put(new ResourceLocation("lepidodendron", "diictodon_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diictodon_alarm")));

		sounds.put(new ResourceLocation("lepidodendron", "meganeuropsis_flight"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "meganeuropsis_flight")));
		sounds.put(new ResourceLocation("lepidodendron", "meganeuropsis_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "meganeuropsis_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "meganeuropsis_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "meganeuropsis_death")));

		sounds.put(new ResourceLocation("lepidodendron", "mesosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mesosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "mesosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mesosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "mesosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mesosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "claudiosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "claudiosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "claudiosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "claudiosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "claudiosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "claudiosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "diadectes_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diadectes_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "diadectes_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diadectes_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "diadectes_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "diadectes_death")));

		sounds.put(new ResourceLocation("lepidodendron", "labidosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "labidosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "labidosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "labidosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "labidosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "labidosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "platyhystrix_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "platyhystrix_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "platyhystrix_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "platyhystrix_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "platyhystrix_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "platyhystrix_death")));

		sounds.put(new ResourceLocation("lepidodendron", "pholiderpeton_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pholiderpeton_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "pholiderpeton_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pholiderpeton_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pholiderpeton_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pholiderpeton_death")));

		sounds.put(new ResourceLocation("lepidodendron", "prionosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "prionosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "prionosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "prionosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "prionosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "prionosuchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "acanthostomatops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostomatops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "acanthostomatops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostomatops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "acanthostomatops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostomatops_death")));

		sounds.put(new ResourceLocation("lepidodendron", "dvinia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dvinia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "dvinia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dvinia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "dvinia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dvinia_death")));
		sounds.put(new ResourceLocation("lepidodendron", "dvinia_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dvinia_alarm")));

		sounds.put(new ResourceLocation("lepidodendron", "panderichthys_breathe"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panderichthys_breathe")));

		sounds.put(new ResourceLocation("lepidodendron", "acanthostega_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostega_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "acanthostega_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostega_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "acanthostega_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "acanthostega_death")));

		sounds.put(new ResourceLocation("lepidodendron", "tapinocephalus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tapinocephalus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "tapinocephalus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tapinocephalus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "tapinocephalus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tapinocephalus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "tapinocephalus_headbut"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tapinocephalus_headbut")));

		sounds.put(new ResourceLocation("lepidodendron", "bug_flight"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bug_flight")));
		sounds.put(new ResourceLocation("lepidodendron", "bug_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bug_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "bug_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bug_death")));

		sounds.put(new ResourceLocation("lepidodendron", "proterogyrinus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proterogyrinus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "proterogyrinus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proterogyrinus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "proterogyrinus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proterogyrinus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "remigiomontanus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "remigiomontanus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "remigiomontanus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "remigiomontanus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "remigiomontanus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "remigiomontanus_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "procynosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "procynosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "procynosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "procynosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "procynosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "procynosuchus_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "anteosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "anteosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "anteosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "anteosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "anteosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "anteosaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "anteosaurus_threat"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "anteosaurus_threat")));

		sounds.put(new ResourceLocation("lepidodendron", "coelophysis_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "coelophysis_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "coelophysis_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "coelophysis_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "coelophysis_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "coelophysis_death")));
		sounds.put(new ResourceLocation("lepidodendron", "coelophysis_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "coelophysis_roar")));
		
		sounds.put(new ResourceLocation("lepidodendron", "lystrosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lystrosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "lystrosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lystrosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "lystrosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lystrosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "suminia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "suminia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "suminia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "suminia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "suminia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "suminia_death")));
		sounds.put(new ResourceLocation("lepidodendron", "suminia_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "suminia_alarm")));

		sounds.put(new ResourceLocation("lepidodendron", "lisowicia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lisowicia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "lisowicia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lisowicia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "lisowicia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lisowicia_death")));
		sounds.put(new ResourceLocation("lepidodendron", "lisowicia_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lisowicia_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "liliensternus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "liliensternus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "liliensternus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "liliensternus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "liliensternus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "liliensternus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "liliensternus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "liliensternus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "weigeltisaurid_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "weigeltisaurid_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "weigeltisaurid_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "weigeltisaurid_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "weigeltisaurid_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "weigeltisaurid_death")));

		sounds.put(new ResourceLocation("lepidodendron", "eoraptor_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoraptor_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "eoraptor_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoraptor_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "eoraptor_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eoraptor_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "shringasaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shringasaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "shringasaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shringasaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "shringasaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shringasaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "herrerasaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "herrerasaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "herrerasaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "herrerasaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "herrerasaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "herrerasaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "herrerasaurus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "herrerasaurus_roar")));
		
		sounds.put(new ResourceLocation("lepidodendron", "archosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "archosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "archosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "archosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "archosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "archosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "plateosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "plateosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "plateosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "plateosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "plateosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "plateosaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "plateosaurus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "plateosaurus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "cynognathus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cynognathus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "cynognathus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cynognathus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "cynognathus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cynognathus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "cynognathus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cynognathus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "placerias_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "placerias_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "placerias_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "placerias_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "placerias_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "placerias_death")));
		sounds.put(new ResourceLocation("lepidodendron", "placerias_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "placerias_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "elginia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "elginia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "elginia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "elginia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "elginia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "elginia_death")));

		sounds.put(new ResourceLocation("lepidodendron", "bunostegos_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bunostegos_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "bunostegos_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bunostegos_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "bunostegos_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "bunostegos_death")));

		sounds.put(new ResourceLocation("lepidodendron", "pristerognathus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pristerognathus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "pristerognathus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pristerognathus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pristerognathus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pristerognathus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "euchambersia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euchambersia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "euchambersia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euchambersia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "euchambersia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euchambersia_death")));
		sounds.put(new ResourceLocation("lepidodendron", "euchambersia_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euchambersia_alarm")));

		sounds.put(new ResourceLocation("lepidodendron", "thrinaxodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thrinaxodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "thrinaxodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thrinaxodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "thrinaxodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thrinaxodon_death")));
		sounds.put(new ResourceLocation("lepidodendron", "thrinaxodon_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thrinaxodon_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "postosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "postosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "postosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "postosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "postosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "postosuchus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "postosuchus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "postosuchus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "effigia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "effigia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "effigia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "effigia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "effigia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "effigia_death")));

		sounds.put(new ResourceLocation("lepidodendron", "sillosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sillosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "sillosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sillosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "sillosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sillosuchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "edaphosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "edaphosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "edaphosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "edaphosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "edaphosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "edaphosaurus_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "austriadactylus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "austriadactylus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "austriadactylus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "austriadactylus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "austriadactylus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "austriadactylus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "caelestiventus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caelestiventus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "caelestiventus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caelestiventus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "caelestiventus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caelestiventus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "eudimorphodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eudimorphodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "eudimorphodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eudimorphodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "eudimorphodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eudimorphodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "caviramus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caviramus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "caviramus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caviramus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "caviramus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "caviramus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "shonisaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shonisaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "shonisaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shonisaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "shonisaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shonisaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "shonisaurus_blowhole"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "shonisaurus_blowhole")));

		sounds.put(new ResourceLocation("lepidodendron", "protozygoptera_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "protozygoptera_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "protozygoptera_flight"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "protozygoptera_flight")));
		
		sounds.put(new ResourceLocation("lepidodendron", "desmatosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "desmatosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "desmatosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "desmatosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "desmatosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "desmatosuchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "hypsognathus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hypsognathus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "hypsognathus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hypsognathus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "lessemsaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lessemsaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "lessemsaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lessemsaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "lessemsaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lessemsaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "lessemsaurus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lessemsaurus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "tiny_mammaliaform_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiny_mammaliaform_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "tiny_mammaliaform_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiny_mammaliaform_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "tiny_mammaliaform_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiny_mammaliaform_death")));

		sounds.put(new ResourceLocation("lepidodendron", "cartorhynchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cartorhynchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "cartorhynchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cartorhynchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "cartorhynchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cartorhynchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "smilosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smilosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "smilosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smilosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "smilosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smilosuchus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "vancleavea_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vancleavea_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "vancleavea_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vancleavea_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "vancleavea_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "vancleavea_death")));

		sounds.put(new ResourceLocation("lepidodendron", "atopodentatus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "atopodentatus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "atopodentatus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "atopodentatus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "atopodentatus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "atopodentatus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "euparkeria_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euparkeria_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "euparkeria_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euparkeria_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "euparkeria_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "euparkeria_death")));

		sounds.put(new ResourceLocation("lepidodendron", "pseudotherium_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pseudotherium_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "pseudotherium_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pseudotherium_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pseudotherium_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pseudotherium_death")));

		sounds.put(new ResourceLocation("lepidodendron", "keichousaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "keichousaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "keichousaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "keichousaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "turtle_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "turtle_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "turtle_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "turtle_death")));

		sounds.put(new ResourceLocation("lepidodendron", "sphenotitan_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sphenotitan_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "sphenotitan_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sphenotitan_death")));

		sounds.put(new ResourceLocation("lepidodendron", "mussaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mussaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "mussaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mussaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "mussaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mussaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "henodus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "henodus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "henodus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "henodus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "henodus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "henodus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "mastodonsaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mastodonsaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "mastodonsaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mastodonsaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "mastodonsaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "mastodonsaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "titanoptera_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "titanoptera_idle")));

		sounds.put(new ResourceLocation("lepidodendron", "melosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "melosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "melosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "melosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "melosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "melosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "dasyceps_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dasyceps_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "dasyceps_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dasyceps_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "dasyceps_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dasyceps_death")));

		sounds.put(new ResourceLocation("lepidodendron", "ascendonanus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ascendonanus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "ascendonanus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ascendonanus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "ascendonanus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "ascendonanus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "aulacephalodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "aulacephalodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "aulacephalodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "aulacephalodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "aulacephalodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "aulacephalodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "cacops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cacops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "cacops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cacops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "cacops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cacops_death")));

		sounds.put(new ResourceLocation("lepidodendron", "casineria_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "casineria_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "casineria_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "casineria_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "casineria_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "casineria_death")));

		sounds.put(new ResourceLocation("lepidodendron", "clevosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "clevosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "clevosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "clevosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "cymbospondylus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cymbospondylus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "cymbospondylus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cymbospondylus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "cymbospondylus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "cymbospondylus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "hyperodapedon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hyperodapedon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "hyperodapedon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hyperodapedon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "hyperodapedon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "hyperodapedon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "jonkeria_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "jonkeria_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "jonkeria_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "jonkeria_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "jonkeria_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "jonkeria_death")));

		sounds.put(new ResourceLocation("lepidodendron", "lotosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lotosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "lotosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lotosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "lotosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "lotosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "panzhousaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panzhousaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "panzhousaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panzhousaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "panzhousaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panzhousaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "purlovia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "purlovia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "purlovia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "purlovia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "purlovia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "purlovia_death")));

		sounds.put(new ResourceLocation("lepidodendron", "scleromochlus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "scleromochlus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "scleromochlus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "scleromochlus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "tiarajudens_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiarajudens_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "tiarajudens_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiarajudens_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "tiarajudens_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tiarajudens_death")));

		sounds.put(new ResourceLocation("lepidodendron", "pantylus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pantylus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "pantylus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "pantylus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "erythrosuchus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "erythrosuchus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "erythrosuchus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "erythrosuchus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "erythrosuchus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "erythrosuchus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "erythrosuchus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "erythrosuchus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "tetraceratops_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tetraceratops_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "tetraceratops_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tetraceratops_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "tetraceratops_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tetraceratops_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "eorhynchochelys_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eorhynchochelys_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "eorhynchochelys_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eorhynchochelys_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "eorhynchochelys_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "eorhynchochelys_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "datheosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "datheosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "datheosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "datheosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "datheosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "datheosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "teleocrater_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "teleocrater_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "teleocrater_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "teleocrater_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "teleocrater_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "teleocrater_death")));

		sounds.put(new ResourceLocation("lepidodendron", "tanystropheus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tanystropheus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "tanystropheus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tanystropheus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "tanystropheus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "tanystropheus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "proburnetia_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proburnetia_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "proburnetia_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proburnetia_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "proburnetia_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "proburnetia_death")));

		sounds.put(new ResourceLocation("lepidodendron", "sauroctonus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sauroctonus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "sauroctonus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sauroctonus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "sauroctonus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sauroctonus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "sauroctonus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "sauroctonus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "arizonasaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "arizonasaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "arizonasaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "arizonasaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "arizonasaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "arizonasaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "smok_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smok_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "smok_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smok_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "smok_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "smok_death")));

		sounds.put(new ResourceLocation("lepidodendron", "poposaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "poposaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "poposaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "poposaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "poposaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "poposaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "thecodontosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thecodontosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "thecodontosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thecodontosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "thecodontosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "thecodontosaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "megalosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "megalosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "megalosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalosaurus_death")));
		sounds.put(new ResourceLocation("lepidodendron", "megalosaurus_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "megalosaurus_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "arthropleura_step"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "arthropleura_step")));

		sounds.put(new ResourceLocation("lepidodendron", "endothiodon_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "endothiodon_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "endothiodon_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "endothiodon_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "endothiodon_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "endothiodon_death")));

		sounds.put(new ResourceLocation("lepidodendron", "rubidgea_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "rubidgea_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "rubidgea_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "rubidgea_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "rubidgea_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "rubidgea_death")));
		
		sounds.put(new ResourceLocation("lepidodendron", "saltriovenator_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "saltriovenator_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "saltriovenator_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "saltriovenator_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "saltriovenator_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "saltriovenator_death")));
		sounds.put(new ResourceLocation("lepidodendron", "saltriovenator_roar"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "saltriovenator_roar")));

		sounds.put(new ResourceLocation("lepidodendron", "chaoyangsauridae_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "chaoyangsauridae_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "chaoyangsauridae_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "chaoyangsauridae_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "chaoyangsauridae_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "chaoyangsauridae_death")));
		sounds.put(new ResourceLocation("lepidodendron", "chaoyangsauridae_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "chaoyangsauridae_alarm")));

		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_death")));
		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_alarm"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_alarm")));
		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_chatter"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_chatter")));
		sounds.put(new ResourceLocation("lepidodendron", "dryosaurid_long_chatter"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "dryosaurid_long_chatter")));

		sounds.put(new ResourceLocation("lepidodendron", "panguraptor_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panguraptor_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "panguraptor_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panguraptor_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "panguraptor_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "panguraptor_death")));

		sounds.put(new ResourceLocation("lepidodendron", "silesaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "silesaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "silesaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "silesaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "silesaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "silesaurus_death")));

		sounds.put(new ResourceLocation("lepidodendron", "kentrosaurus_idle"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "kentrosaurus_idle")));
		sounds.put(new ResourceLocation("lepidodendron", "kentrosaurus_hurt"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "kentrosaurus_hurt")));
		sounds.put(new ResourceLocation("lepidodendron", "kentrosaurus_death"),
				new net.minecraft.util.SoundEvent(new ResourceLocation("lepidodendron", "kentrosaurus_death")));

	}


	public void preInit(FMLPreInitializationEvent event) {
		try {
			for (ASMDataTable.ASMData asmData : event.getAsmData().getAll(ModElement.Tag.class.getName())) {
				Class<?> clazz = Class.forName(asmData.getClassName());
				if (clazz.getSuperclass() == ElementsLepidodendronMod.ModElement.class)
					elements.add((ElementsLepidodendronMod.ModElement) clazz.getConstructor(this.getClass()).newInstance(this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(elements);
		elements.forEach(ElementsLepidodendronMod.ModElement::initElements);
		{
			this.addNetworkMessage(LepidodendronModVariables.WorldSavedDataSyncMessageHandler.class,
					LepidodendronModVariables.WorldSavedDataSyncMessage.class, Side.SERVER, Side.CLIENT);
		}

	}

	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		for (Map.Entry<ResourceLocation, net.minecraft.util.SoundEvent> sound : sounds.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));


	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator cg, IChunkProvider cp) {
		elements.forEach(element -> element.generateWorld(random, chunkX * 16, chunkZ * 16, world, world.provider.getDimension(), cg, cp));
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		for (ModElement element : elements) {
			int ret = element.addFuel(fuel);
			if (ret != 0)
				return ret;
		}
		return 0;
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.player.world.isRemote) {
			WorldSavedData mapdata = LepidodendronModVariables.MapVariables.get(event.player.world);
			WorldSavedData worlddata = LepidodendronModVariables.WorldVariables.get(event.player.world);
			if (mapdata != null)
				LepidodendronMod.PACKET_HANDLER.sendTo(new LepidodendronModVariables.WorldSavedDataSyncMessage(0, mapdata),
						(EntityPlayerMP) event.player);
			if (worlddata != null)
				LepidodendronMod.PACKET_HANDLER.sendTo(new LepidodendronModVariables.WorldSavedDataSyncMessage(1, worlddata),
						(EntityPlayerMP) event.player);
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.player.world.isRemote) {
			WorldSavedData worlddata = LepidodendronModVariables.WorldVariables.get(event.player.world);
			if (worlddata != null)
				LepidodendronMod.PACKET_HANDLER.sendTo(new LepidodendronModVariables.WorldSavedDataSyncMessage(1, worlddata),
						(EntityPlayerMP) event.player);
		}
	}
	private int messageID = 0;
	public <T extends IMessage, V extends IMessage> void addNetworkMessage(Class<? extends IMessageHandler<T, V>> handler, Class<T> messageClass,
			Side... sides) {
		for (Side side : sides)
			LepidodendronMod.PACKET_HANDLER.registerMessage(handler, messageClass, messageID, side);
		messageID++;
	}

	public static class GuiHandler implements IGuiHandler {
		@Override
		public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			if (id == GUIAcidBath.GUIID) {
				return new GUIAcidBath.GUILepidodendronAcidBath(world, x, y, z, player);
			}
			if (id == GUIDNACentrifuge.GUIID) {
				return new GUIDNACentrifuge.GUILepidodendronDNACentrifuge(world, x, y, z, player);
			}
			if (id == GUIDNAForge.GUIID) {
				return new GUIDNAForge.GUILepidodendronDNAForge(world, x, y, z, player);
			}
			if (id == GUICoalTarProcessor.GUIID) {
				return new GUICoalTarProcessor.GUILepidodendronCoalTarProcessor(world, x, y, z, player);
			}
			if (id == GUIOligopoolMachine.GUIID) {
				return new GUIOligopoolMachine.GUILepidodendronOligopoolMachine(world, x, y, z, player);
			}
			if (id == GUILabBench.GUIID) {
				return new GUILabBench.GUILepidodendronLabBench(world, x, y, z, player);
			}
			if (id == GUIMicroscope.GUIID) {
				return new GUIMicroscope.GUILepidodendronMicroscope(world, x, y, z, player);
			}
			if (id == GUISorterFossil.GUIID) {
				return new GUISorterFossil.GUILepidodendronSorterFossil(world, x, y, z, player);
			}
			if (id == GUITaxidermyTable.GUIID) {
				return new GUITaxidermyTable.GUILepidodendronTaxidermyTable(world, x, y, z, player);
			}
			return null;
		}

		@Override
		public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
			if (id == GUIAcidBath.GUIID) {
				return new GUIAcidBath.GuiWindow(world, x, y, z, player);
			}
			if (id == GUIDNACentrifuge.GUIID) {
				return new GUIDNACentrifuge.GuiWindow(world, x, y, z, player);
			}
			if (id == GUIDNAForge.GUIID) {
				return new GUIDNAForge.GuiWindow(world, x, y, z, player);
			}
			if (id == GUICoalTarProcessor.GUIID) {
				return new GUICoalTarProcessor.GuiWindow(world, x, y, z, player);
			}
			if (id == GUIOligopoolMachine.GUIID) {
				return new GUIOligopoolMachine.GuiWindow(world, x, y, z, player);
			}
			if (id == GUILabBench.GUIID) {
				return new GUILabBench.GuiWindow(world, x, y, z, player);
			}
			if (id == GUIMicroscope.GUIID) {
				return new GUIMicroscope.GuiWindow(world, x, y, z, player);
			}
			if (id == GUISorterFossil.GUIID) {
				return new GUISorterFossil.GuiWindow(world, x, y, z, player);
			}
			if (id == GUITaxidermyTable.GUIID) {
				return new GUITaxidermyTable.GuiWindow(world, x, y, z, player);
			}
			return null;
		}

	}



	public List<ModElement> getElements() {
		return elements;
	}

	public List<Supplier<Block>> getBlocks() {
		return blocks;
	}

	public List<Supplier<Item>> getItems() {
		return items;
	}

	public List<Supplier<Biome>> getBiomes() {
		return biomes;
	}

	public List<Supplier<EntityEntry>> getEntities() {
		return entities;
	}

	public List<Supplier<Potion>> getPotions() {
		return potions;
	}

	public static class ModElement implements Comparable<ModElement> {
		@Retention(RetentionPolicy.RUNTIME)
		public @interface Tag {
		}
		protected final ElementsLepidodendronMod elements;
		protected final int sortid;
		public ModElement(ElementsLepidodendronMod elements, int sortid) {
			this.elements = elements;
			this.sortid = sortid;
		}

		public void initElements() {
		}

		public void init(FMLInitializationEvent event) {
		}

		public void preInit(FMLPreInitializationEvent event) {
		}

		public void generateWorld(Random random, int posX, int posZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		}

		public void serverLoad(FMLServerStartingEvent event) {
		}

		public void registerModels(ModelRegistryEvent event) {
		}

		public int addFuel(ItemStack fuel) {
			return 0;
		}

		@Override
		public int compareTo(ModElement other) {
			return this.sortid - other.sortid;
		}
	}

}
