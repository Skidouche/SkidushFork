
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.BlockDicksoniaSapling;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemPetrifiedDicksoniaSapling extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:petrified_dicksonia_sapling")
	public static final Item block = null;
	public ItemPetrifiedDicksoniaSapling(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.petrified_dicksonia_sapling);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:petrified_dicksonia_sapling", "inventory"));
	}
	public static class ItemCustom extends ItemPetrified {
		public ItemCustom() {
			super(BlockDicksoniaSapling.block);
			setTranslationKey("pf_petrified_dicksonia_sapling");
			setRegistryName("petrified_dicksonia_sapling");

		}
	}
}
