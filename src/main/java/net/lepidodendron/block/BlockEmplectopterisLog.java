
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.List;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockEmplectopterisLog extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:emplectopteris_log")
	public static final Block block = null;
	public BlockEmplectopterisLog(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.emplectopteris_log);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("emplectopteris_log"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("lepidodendron:Emplectopteris_log", "inventory"));

	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		OreDictionary.registerOre("plantdnaPNlepidodendron:emplectopteris_sapling", BlockEmplectopterisLog.block);
		OreDictionary.registerOre("logWood", BlockEmplectopterisLog.block);
	}


	public static final PropertyBool WATER = PropertyBool.create("water");

	public static class BlockCustom extends Block {

	public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool ATTACHMENT = PropertyBool.create("attachment");
    public static final PropertyBool ROOTS = PropertyBool.create("roots");

    	public BlockCustom() {
			super(Material.WOOD);
			setTranslationKey("pf_emplectopteris_log");
			setSoundType(SoundType.WOOD);
			setHardness(1F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabLepidodendronPlants.tab);
			
        	this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UP, Boolean.valueOf(false)).withProperty(DOWN, Boolean.valueOf(false)).withProperty(ATTACHMENT, Boolean.valueOf(false)));
		}


		@Override
		public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 5;
		}

		@Override
		public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 5;
		}

		int[] surroundings;

		@Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){
			
	    	Block block = worldIn.getBlockState(pos.down()).getBlock();
	        Block block1 = worldIn.getBlockState(pos.up()).getBlock();
	        Block block2 = worldIn.getBlockState(pos.north()).getBlock();
	        Block block3 = worldIn.getBlockState(pos.east()).getBlock();
	        Block block4 = worldIn.getBlockState(pos.south()).getBlock();
	        Block block5 = worldIn.getBlockState(pos.west()).getBlock();
	        
			boolean attachmentblock = false;
			boolean attachmentblock1 = false;
			boolean waterstate = Boolean.valueOf(worldIn.getBlockState(pos.down()).getMaterial() == Material.WATER);

			if (block != this) {
				IBlockState blockState = worldIn.getBlockState(pos.down());
				//Is the block it is standing on a full solid one?
            	if ((blockState.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) 
                	|| blockState.isOpaqueCube() || (blockState.isFullBlock())) {
					attachmentblock = true;
				}
			}

			if ((block1 == BlockEmplectopterisShoot.block) || (block1 == BlockEmplectopterisShootPlaceable.block)) {
				attachmentblock1 = true;
	    	}
			
	        return state.withProperty(WATER, waterstate).withProperty(ROOTS, Boolean.valueOf(attachmentblock)).withProperty(DOWN, Boolean.valueOf(block == this || attachmentblock || block == BlockEmplectopterisShoot.block || block == BlockEmplectopterisShootPlaceable.block)).withProperty(UP, Boolean.valueOf(block1 == this || attachmentblock1)).withProperty(NORTH, Boolean.valueOf(block2 == this)).withProperty(EAST, Boolean.valueOf(block3 == this)).withProperty(SOUTH, Boolean.valueOf(block4 == this)).withProperty(WEST, Boolean.valueOf(block5 == this)).withProperty(ATTACHMENT, (attachmentblock || waterstate));
	    }

	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        state = state.getActualState(source, pos);
	        float f = 0.1875F;
	        float f1 = ((Boolean)state.getValue(WEST)).booleanValue() ? 0.0F : 0.1875F;
	        float f2 = ((Boolean)state.getValue(DOWN)).booleanValue() ? 0.0F : 0.1875F;
	        float f3 = ((Boolean)state.getValue(NORTH)).booleanValue() ? 0.0F : 0.1875F;
	        float f4 = ((Boolean)state.getValue(EAST)).booleanValue() ? 1.0F : 0.8125F;
	        float f5 = ((Boolean)state.getValue(UP)).booleanValue() ? 1.0F : 0.8125F;
	        float f6 = ((Boolean)state.getValue(SOUTH)).booleanValue() ? 1.0F : 0.8125F;
	        return new AxisAlignedBB((double)f1, (double)f2, (double)f3, (double)f4, (double)f5, (double)f6);
	    }
	
	    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
	    {
	        if (!isActualState)
	        {
	            state = state.getActualState(worldIn, pos);
	        }
	
	        float f = 0.1875F;
	        float f1 = 0.8125F;
	        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D));
	
	        if (((Boolean)state.getValue(WEST)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(EAST)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(UP)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.8125D, 0.1875D, 0.8125D, 1.0D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(DOWN)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.1875D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(NORTH)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D));
	        }
	
	        if (((Boolean)state.getValue(SOUTH)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D));
	        }
	    }
	
	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        return 0;
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
	
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, SOUTH, WEST, UP, DOWN, ATTACHMENT, ROOTS, WATER});
	    }

		@Override
		public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
			return true;
		}

		@Override public boolean isWood(IBlockAccess world, BlockPos pos){ 
				return true;
			}

		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	    {
	        int i = 4;
	        int j = 5;
	
	        if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5)))
	        {
	            for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4)))
	            {
	                IBlockState iblockstate = worldIn.getBlockState(blockpos);
	
	                if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
	                {
	                    iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
	                }
	            }
	        }

	        if (Math.random() > 0.66) {
				//Spawn another sapling:
				if (!worldIn.isRemote) {
					EntityItem entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockEmplectopterisSapling.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					worldIn.spawnEntity(entityToSpawn);
				}
			}

	        //Propagate the destruction
			Block block = worldIn.getBlockState(pos.down()).getBlock();
	        Block block1 = worldIn.getBlockState(pos.up()).getBlock();
	        Block block2 = worldIn.getBlockState(pos.north()).getBlock();
	        Block block3 = worldIn.getBlockState(pos.east()).getBlock();
	        Block block4 = worldIn.getBlockState(pos.south()).getBlock();
	        Block block5 = worldIn.getBlockState(pos.west()).getBlock();

	        if (block == this) {
	        	worldIn.destroyBlock(pos.down(), true);
	        }
	        if (block1 == this) {
	        	worldIn.destroyBlock(pos.up(), true);
	        }
	        if (block2 == this) {
	        	worldIn.destroyBlock(pos.north(), true);
	        }
	        if (block3 == this) {
	        	worldIn.destroyBlock(pos.east(), true);
	        }
	        if (block4 == this) {
	        	worldIn.destroyBlock(pos.south(), true);
	        }
	        if (block5 == this) {
	        	worldIn.destroyBlock(pos.west(), true);
	        }

	    }
	    
 		@Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			super.neighborChanged(state, world, pos, neighborBlock, fromPos);

			boolean isSafeToStay = false;

			if (world.isAirBlock(pos.down())
				&& (world.getBlockState(pos.down().north()).getBlock() == Blocks.WATER
					|| world.getBlockState(pos.down().east()).getBlock() == Blocks.WATER
					|| world.getBlockState(pos.down().south()).getBlock() == Blocks.WATER
					|| world.getBlockState(pos.down().west()).getBlock() == Blocks.WATER
				)
					&& (world.getBlockState(pos.down(2)).getBlockFaceShape(world, pos.down(2), EnumFacing.UP) == BlockFaceShape.SOLID)
				)//In this case the water will flow back in and support the plant sufficiently
			{
				isSafeToStay = true;
			}

			if (world.isAirBlock(pos.down()) && fromPos.getY() == pos.getY()-1 && !isSafeToStay) {
				world.destroyBlock(pos, true);
			}
				
		}
		

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}
		
	}	

}
