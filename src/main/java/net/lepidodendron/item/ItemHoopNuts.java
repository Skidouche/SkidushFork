
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.BlockHoopSapling;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemHoopNuts extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:hoop_araucaria_nuts")
	public static final Item block = null;
	public ItemHoopNuts(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.hoop_araucaria_nuts);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemFoodCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:hoop_araucaria_nuts", "inventory"));
	}
	public static class ItemFoodCustom extends ItemPrehistoricPlantable {
		public ItemFoodCustom() {
			super(BlockHoopSapling.block.getDefaultState(),1);
			setTranslationKey("pf_hoop_araucaria_nuts");
			setRegistryName("hoop_araucaria_nuts");
			setCreativeTab(TabLepidodendronPlants.tab);
		}
	}
}
