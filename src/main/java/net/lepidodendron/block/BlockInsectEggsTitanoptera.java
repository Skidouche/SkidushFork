
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.entity.EntityPrehistoricFloraTitanopteraNymph;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockInsectEggsTitanoptera extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:insect_eggs_titanoptera")
	public static final Block block = null;
	public BlockInsectEggsTitanoptera(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.insect_eggs_titanoptera);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("insect_eggs_titanoptera"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(BlockInsectEggsTitanoptera.TileEntityCustom.class, "lepidodendron:tileentityinsect_eggs_titanoptera");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:insect_eggs_titanoptera", "inventory"));
	}
	public static class BlockCustom extends BlockInsectEggs {
		public BlockCustom() {
			setTranslationKey("pf_insect_eggs_titanoptera");
			this.setTickRandomly(true);
			setCreativeTab(null);
		}

		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state) {
			world.removeTileEntity(pos);
			super.breakBlock(world, pos, state);
		}

		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new BlockInsectEggsTitanoptera.TileEntityCustom();
		}

		public BlockInsectEggsTitanoptera.TileEntityCustom createNewTileEntity(World worldIn, int meta) {
			return new BlockInsectEggsTitanoptera.TileEntityCustom();
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (!(worldIn.isRemote)) {
				EntityPrehistoricFloraAgeableBase.summon(worldIn, EntityList.getKey(EntityPrehistoricFloraTitanopteraNymph.class).toString(), "", (double)pos.getX() , (double)pos.getY(), (double)pos.getZ());
			}
			worldIn.destroyBlock(pos, false);
		}

		/*@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: " + EntityPrehistoricFloraTitanopteraGigatitan.getHabitat() + " Insect");
				tooltip.add("Periods: " + EntityPrehistoricFloraTitanopteraGigatitan.getPeriod());
				super.addInformation(stack, player, tooltip, advanced);
			}
		}*/
	}

	public static class TileEntityCustom extends TileEntity {

	}
}
