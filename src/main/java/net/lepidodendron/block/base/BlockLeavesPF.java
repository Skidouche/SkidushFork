
package net.lepidodendron.block.base;

import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLeavesPF extends BlockLeaves {

	public BlockLeavesPF() {
		super();
		setSoundType(SoundType.PLANT);
		setHardness(0.2F);
		setResistance(0.2F);
		setLightLevel(0F);
		setLightOpacity(1);
		setCreativeTab(TabLepidodendronPlants.tab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
	}

	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this, 1));
	}

	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{CHECK_DECAY, DECAYABLE});
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) != 0).withProperty(CHECK_DECAY, (meta & 2) != 0);
	}

	public int getMetaFromState(IBlockState state) {
		int i = 0;
		if (!(Boolean) state.getValue(DECAYABLE))
			i |= 1;
		if ((Boolean) state.getValue(CHECK_DECAY))
			i |= 2;
		return i;
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
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return true;
	}

}


