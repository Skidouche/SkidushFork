package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockTreeLeaves;
import net.lepidodendron.block.BlockWoodenLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenLepidodendronYoung2 extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenLepidodendronYoung2(ElementsLepidodendronMod instance) {
		super(instance, 13);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("vines") == null) {
			System.err.println("Failed to load dependency vines for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("vines2") == null) {
			System.err.println("Failed to load dependency vines2 for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		if (dependencies.get("SaplingSpawn") == null) {
			System.err.println("Failed to load dependency SaplingSpawn for procedure WorldGenLepidodendronYoung2!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = x;
		int yy = y;
		int zz = z;
		World world = (World) dependencies.get("world");
		boolean vines = (boolean) dependencies.get("vines");
		boolean vines2 = (boolean) dependencies.get("vines2");
		boolean SaplingSpawn = (boolean) dependencies.get("SaplingSpawn");
		double height = 0;
		double counter = 0;
		int vinecounter = 0;
		
		if (((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))) ||
			(((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
			 && (world.canSeeSky(new BlockPos((int) x, (int) y + 1, (int) z))))) {	
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BlockWoodenLog.block.getDefaultState(), 3);
			
			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world,
					new BlockPos((int) x, (int) (y + 1), (int) z))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.LEAVES)) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockWoodenLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), world,
					new BlockPos((int) x, (int) y, (int) (z + 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.LEAVES)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), BlockWoodenLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))), world,
					new BlockPos((int) x, (int) y, (int) (z - 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.LEAVES)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), BlockWoodenLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), world,
					new BlockPos((int) (x + 1), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), BlockWoodenLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.UP), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), world,
					new BlockPos((int) (x - 1), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), BlockWoodenLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.UP), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}

			//height = (double) (18) + Math.round((Math.random() * 10) / 2) + Math.round((Math.random() * 10) / 2)
			//		+ Math.round((Math.random() * 10) / 2);
			height = (double) 5 + Math.round(Math.random() * 5);
			counter = (double) 1;
			while (((counter) <= (height))) {
				block = world.getBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z)), world,
						new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z))) {
					world.setBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z), BlockWoodenLog.block.getDefaultState(), 3);
				}
				counter = (double) ((counter) + 1);
			}

			//Random vines for world-gen only:
			if ((vines) && (Math.random() > 0.6)) {
				xx = x;
				yy =  y + (int) height - 7;
				zz = z;
				ProcedureVines.executeProcedure(xx, yy, zz, world);
			}
			//Random vines for world-gen only:
			if ((vines2) && (Math.random() > 0.6)) {
				xx = x;
				yy =  y + (int) height - 7;
				zz = z;
				ProcedureVines2.executeProcedure(xx, yy, zz, world);
			}
			

			//Leaf shoot:
			yy = (int) ((height) + (y - 1));
			xx = x;
			zz = z + 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x;
			zz = z - 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x + 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x - 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			yy = yy + 1;
			xx = x;
			zz = z + 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x;
			zz = z - 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x + 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x - 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			yy = yy + 1;
			xx = x;
			zz = z + 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x;
			zz = z - 1;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x + 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			xx = x - 1;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			yy = yy + 1;
			xx = x;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			yy = yy + 1;
			xx = x;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}

			yy = yy + 1;
			xx = x;
			zz = z;
			block = world.getBlockState(new BlockPos(xx, yy, zz)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos(xx, yy, zz)), world,
					new BlockPos(xx, yy, zz))) {
				world.setBlockState(new BlockPos(xx, yy, zz), BlockTreeLeaves.block.getDefaultState(),
						3);
			}


		}
	}
}
