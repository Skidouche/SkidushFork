
package net.lepidodendron.block;

import net.lepidodendron.creativetab.TabLepidodendronStatic;
import net.lepidodendron.item.entities.ItemBivalveRaw;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

	public abstract class BlockBivalve extends Block implements net.minecraftforge.common.IShearable {

	public static final PropertyDirection FACING = BlockDirectional.FACING;
	public static final Block block = null;
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);


	public BlockBivalve() {
		super(Material.WATER);
		setSoundType(SoundType.STONE);
		setHardness(0.4F);
		setResistance(0.2F);
		setLightLevel(0F);
		setLightOpacity(3);
		//this.setTickRandomly(true);
		setCreativeTab(TabLepidodendronStatic.tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0).withProperty(FACING, EnumFacing.UP));
	}


		@Override
		public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
			return true;
		}

	@Override public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos){ return true; }

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, (int) (1)));
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer()
{
	return BlockRenderLayer.CUTOUT;
}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{LEVEL, FACING});
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
		return MapColor.FOLIAGE;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return new ItemStack(ItemBivalveRaw.block, (int) (1)).getItem();
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		super.neighborChanged(state, world, pos, neighborBlock, fromPos);
		world.scheduleUpdate(pos, this, 1);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
		}
		else {
			if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

				//Test the orientation of this block and then check if it is still connected:
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.NORTH) {
				IBlockState iblockstate = worldIn.getBlockState(pos.south());
				if (worldIn.isAirBlock(pos.south()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.south(), EnumFacing.NORTH) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.south()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.SOUTH) {
				IBlockState iblockstate = worldIn.getBlockState(pos.north());
				if (worldIn.isAirBlock(pos.north()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.north(), EnumFacing.SOUTH) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.north()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.EAST) {
				IBlockState iblockstate = worldIn.getBlockState(pos.west());
				if (worldIn.isAirBlock(pos.west()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.west(), EnumFacing.EAST) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.west()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.WEST) {
				IBlockState iblockstate = worldIn.getBlockState(pos.east());
				if (worldIn.isAirBlock(pos.east()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.east(), EnumFacing.WEST) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.east()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.UP) {
				IBlockState iblockstate = worldIn.getBlockState(pos.down());
				if (worldIn.isAirBlock(pos.down()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.down()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
			if ((EnumFacing) state.getValue(BlockDirectional.FACING) == EnumFacing.DOWN) {
				IBlockState iblockstate = worldIn.getBlockState(pos.up());
				if (worldIn.isAirBlock(pos.up()) ||
					(
						(iblockstate.getBlockFaceShape(worldIn, pos.up(), EnumFacing.DOWN) != BlockFaceShape.SOLID)
						&& (!iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos.up()))
					)
				)
					{
						worldIn.setBlockToAir(pos);

					}
				}
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	public boolean canBlockStay(World worldIn, BlockPos pos)
	{
		return this.canPlaceBlockAt(worldIn, pos);
	}

	//@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{

		//System.err.println("Can place");

		if ((isWaterBlock(worldIn, pos)) && (isWaterBlock(worldIn, pos.up()))) {
			return super.canPlaceBlockAt(worldIn, pos);
		}
		//if (((world.getBlockState(pos.down()).getMaterial() != Material.SAND)
		//	&& (world.getBlockState(pos.down()).getMaterial() != Material.ROCK)
		//	&& (world.getBlockState(pos.down()).getMaterial() != Material.GROUND)
		//	&& (world.getBlockState(pos.down()).getMaterial() != Material.CLAY))) {
		//	return false;
		//}
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
	{
		boolean blockface  = true;
		if (side == EnumFacing.NORTH) {
			if (worldIn.getBlockState(pos.south()).getBlockFaceShape(worldIn, pos.south(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.SOUTH) {
			if (worldIn.getBlockState(pos.north()).getBlockFaceShape(worldIn, pos.north(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.EAST) {
			if (worldIn.getBlockState(pos.west()).getBlockFaceShape(worldIn, pos.west(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.WEST) {
			if (worldIn.getBlockState(pos.east()).getBlockFaceShape(worldIn, pos.east(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.UP) {
			if (worldIn.getBlockState(pos.down()).getBlockFaceShape(worldIn, pos.down(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.DOWN) {
			if (worldIn.getBlockState(pos.up()).getBlockFaceShape(worldIn, pos.up(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}


		return (blockface && canPlaceBlockAt(worldIn, pos));

	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

	public boolean isWaterBlock(World world, BlockPos pos) {
		if (world.getBlockState(pos).getMaterial() == Material.WATER) {
			//IBlockState iblockstate = world.getBlockState(pos);
			//if (((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0) {
				return true;
			//}
		}
		return false;
	}

}

