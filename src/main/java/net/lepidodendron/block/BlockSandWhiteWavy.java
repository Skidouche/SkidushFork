
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMisc;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockSandWhiteWavy extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:sand_white_wavy")
	public static final Block block = null;
	public BlockSandWhiteWavy(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.sand_white_wavy);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("sand_white_wavy"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:sand_white_wavy", "inventory"));
	}
	
	public static class BlockCustom extends BlockFalling {
		public BlockCustom() {
			super(Material.SAND);
			this.setSoundType(SoundType.SAND);
			setHardness(0.5F);
			setResistance(0.5F);
			setTranslationKey("pf_sand_white_wavy");
			setHarvestLevel("shovel", 0);
			setCreativeTab(TabLepidodendronMisc.tab);
		}

		@Override
		public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
			net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

			switch (plantType)
			{
				case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
				case Desert: return true;
				case Beach:
					boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.south()).getMaterial() == Material.WATER);
					return hasWater;
			}

			return super.canSustainPlant(state, world, pos, direction, plantable);
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.WHITE_STAINED_HARDENED_CLAY;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(BlockSandWhite.block, (int) (1), 0));
		}

		@Override
		protected boolean canSilkHarvest()
		{
			return true;
		}

		@Override
		public ItemStack getSilkTouchDrop(IBlockState state)  {
			return new ItemStack(BlockSandWhiteWavy.block, (int) (1), 0);
		}

	    
	}
}
