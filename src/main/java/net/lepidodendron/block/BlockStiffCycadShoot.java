
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockStiffCycadShoot extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:stiff_cycad_shoot_worldgen")
	public static final Block block = null;
	public BlockStiffCycadShoot(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.stiff_cycad_shoot_worldgen);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("stiff_cycad_shoot_worldgen"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
		//		new ModelResourceLocation("lepidodendron:stiff_cycad_shoot_worldgen", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY).build());
	}
	public static class BlockCustom extends BlockLeaves {
		public BlockCustom() {
			super();
			setTranslationKey("pf_stiff_cycad_shoot_worldgen");
			setSoundType(SoundType.PLANT);
			setHardness(0.2F);
			setResistance(0.2F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(null);
			this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
		}

		@Override
		public BlockPlanks.EnumType getWoodType(int meta) {
			return null;
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockStiffCycadShootPlaceable.block, (int) (1)));
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

		public boolean isFullCube(IBlockState state)
	    {
	        return false;
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
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockStiffCycadShootPlaceable.block, (int) (1));
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.FOLIAGE;
		}

		@Override
		protected int getSaplingDropChance(IBlockState state) {
			return 1;
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			if (!LepidodendronConfig.doPropagation) {
				return Item.getItemFromBlock(BlockStiffCycadSapling.block);
			}
			return null;
		}

		public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        	return false;
    	}

		@Override
		protected boolean canSilkHarvest()
	    {
	        return true;
	    }

	    @Override
        public ItemStack getSilkTouchDrop(IBlockState state)  {
            return new ItemStack(BlockStiffCycadShootPlaceable.block, (int) (1));
        }

		@Override
		public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
			if (Math.random() > 0.66 && !LepidodendronConfig.doPropagation) {
				//Spawn another sapling:
				if (!worldIn.isRemote) {
					EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockStiffCycadSapling.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					worldIn.spawnEntity(entityToSpawn);
				}
			}
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
			//super.updateTick(worldIn, pos, state, rand);
			if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue() && ((Boolean)state.getValue(DECAYABLE)).booleanValue())
			{
				Block block = worldIn.getBlockState(pos.down()).getBlock();
				if (block != BlockStiffCycadLog.block) {
					worldIn.setBlockToAir(pos);
					if (!worldIn.isRemote) {
						EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockStiffCycadSapling.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						worldIn.spawnEntity(entityToSpawn);
					}
				}
				else if (Math.random()>0.8 && Math.random()>0.8) {
					worldIn.setBlockState(pos, BlockStiffCycadShootCone.block.getDefaultState(), 3);
				}
			}

		}

		@Override
		public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
			super.onEntityCollision(world, pos, state, entity);
			if (entity instanceof EntityPlayer) {entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);}
			if (entity instanceof EntityVillager) {entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);}
			if (entity instanceof EntityMob) {entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);}
		}

		 public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.9375D, 0.9375D);
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

	}
}
