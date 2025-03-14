
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemPetrifiedSwampHorsetail extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:petrified_swamp_horsetail")
	public static final Item block = null;
	public ItemPetrifiedSwampHorsetail(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.petrified_swamp_horsetail);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:petrified_swamp_horsetail", "inventory"));
	}
	public static class ItemCustom extends ItemPetrified {
		public ItemCustom() {
			super(null);
			setTranslationKey("pf_petrified_swamp_horsetail");
			setRegistryName("petrified_swamp_horsetail");
			
		}

		@Override
		public ItemStack getPlantStack() {
			return new ItemStack(ItemSwampHorsetailItem.block, 1);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Horsetail plant");
				tooltip.add("Periods: [Carboniferous -] Permian - Triassic - Jurassic - Cretaceous - Paleogene - Neogene - Quaternary");
				tooltip.add("Note: placed either next to water or at water surface of one-block deep water, over grass, dirt, clay or sand; spreads if there is light.");
				tooltip.add("Propagation: spores");}
			super.addInformation(stack, player, tooltip, advanced);
		}

	}
}
