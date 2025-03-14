package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.*;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenAcrocomia extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenAcrocomia(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyDirection FRUITFACING = BlockDirectional.FACING;

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenAcrocomia!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenAcrocomia!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenAcrocomia!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenAcrocomia!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = x;
		int yy = y;
		int zz = z;
		World world = (World) dependencies.get("world");
		int TrunkHeight = 0;
		double counter = 0;
		boolean TreeCheck = true;
		
		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& material != Material.GRASS
			&& material != Material.GROUND
			&& material != Material.GLASS
			&& material != Material.IRON
			&& material != Material.ROCK
			&& material != Material.SAND
			&& material != Material.WOOD
			) {			

			//Trunk:
			TrunkHeight = (int) (Math.round(Math.random() * 3D) + Math.round(Math.random() * 3D) + 6D);

			//Check there is space:
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!world.isAirBlock(new BlockPos((int) x + counter, (int) y + TrunkHeight + 2, (int) z))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!world.isAirBlock(new BlockPos((int) x, (int) y + TrunkHeight + 2, (int) z + counter))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!world.isAirBlock(new BlockPos((int) x + counter, (int) y + TrunkHeight + 3, (int) z))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!world.isAirBlock(new BlockPos((int) x, (int) y + TrunkHeight + 3, (int) z + counter))) TreeCheck = false;
				counter = counter + 1;
			}
			
			if (!TreeCheck) return;
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));

			counter = 0;
			while (counter <= TrunkHeight) {
				xx = x;
				yy = y + (int) counter;
				zz = z;
				ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockAcrocomiaLog.block, EnumFacing.UP);
				
				counter = counter + 1;
			}

			//Place the crown:
			xx = x - 1;
			yy = y + TrunkHeight;
			zz = z;
			if (Math.random() > 0.6) {
				world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaFruitBunch.block.getDefaultState().withProperty(FRUITFACING, EnumFacing.WEST), 3);
			}

			xx = x + 1;
			yy = y + TrunkHeight;
			zz = z;
			if (Math.random() > 0.6) {
				world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaFruitBunch.block.getDefaultState().withProperty(FRUITFACING, EnumFacing.EAST), 3);
			}

			xx = x;
			yy = y + TrunkHeight;
			zz = z + 1;
			if (Math.random() > 0.6) {
				world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaFruitBunch.block.getDefaultState().withProperty(FRUITFACING, EnumFacing.SOUTH), 3);
			}

			xx = x;
			yy = y + TrunkHeight;
			zz = z - 1;
			if (Math.random() > 0.6) {
				world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaFruitBunch.block.getDefaultState().withProperty(FRUITFACING, EnumFacing.NORTH), 3);
			}


			xx = x;
			yy = y + TrunkHeight + 1;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShoot.block.getDefaultState(), 3);

			xx = x;
			yy = y + TrunkHeight + 2;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShoot02.block.getDefaultState(), 3);

			xx = x;
			yy = y + TrunkHeight + 3;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShoot03.block.getDefaultState(), 3);

			xx = x - 2;
			yy = y + TrunkHeight + 1;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 3);

			xx = x + 2;
			yy = y + TrunkHeight + 1;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 3);

			xx = x;
			yy = y + TrunkHeight + 1;
			zz = z + 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 3);

			xx = x;
			yy = y + TrunkHeight + 1;
			zz = z - 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 3);

			xx = x - 2;
			yy = y + TrunkHeight + 2;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 3);

			xx = x + 2;
			yy = y + TrunkHeight + 2;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 3);
			
			xx = x;
			yy = y + TrunkHeight + 2;
			zz = z + 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 3);
			
			xx = x;
			yy = y + TrunkHeight + 2;
			zz = z - 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 3);

			xx = x - 2;
			yy = y + TrunkHeight + 3;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 3);

			xx = x + 2;
			yy = y + TrunkHeight + 3;
			zz = z;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 3);
			
			xx = x;
			yy = y + TrunkHeight + 3;
			zz = z + 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 3);
			
			xx = x;
			yy = y + TrunkHeight + 3;
			zz = z - 2;
			world.setBlockState(new BlockPos((int) xx, (int) yy, (int) zz), BlockAcrocomiaShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 3);

		}
	}

}