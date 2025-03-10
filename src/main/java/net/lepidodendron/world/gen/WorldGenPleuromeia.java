package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockPleuromeiaSapling;
import net.lepidodendron.block.BlockPleuromeiaStem;
import net.lepidodendron.procedure.ProcedureWorldGenPleuromeia;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPleuromeia extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        return true;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position, boolean needsWater)
    {
        boolean flag = false;
        int offset = 7;
        if (needsWater) {
            offset = 3;
        }
        for (int i = 0; i < 8; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(offset) - rand.nextInt(offset), rand.nextInt(8) - rand.nextInt(8), rand.nextInt(offset) - rand.nextInt(offset));

            if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockPleuromeiaSapling.block.canPlaceBlockAt(worldIn, blockpos) &&
                    (
                        (worldIn.getBlockState(blockpos.down()).getMaterial() == Material.GROUND)
                            || (worldIn.getBlockState(blockpos.down()).getMaterial() == Material.SAND)
                    )
                    && (worldIn.getBlockState(blockpos.east()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.west()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.north()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.south()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.east().up()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.west().up()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.north().up()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.south().up()).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.east().up(2)).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.west().up(2)).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.north().up(2)).getBlock() != BlockPleuromeiaStem.block)
                    && (worldIn.getBlockState(blockpos.south().up(2)).getBlock() != BlockPleuromeiaStem.block)
            )
            if (!needsWater) {
                {
                    java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
                    $_dependencies.put("x", blockpos.getX());
                    $_dependencies.put("y", blockpos.getY());
                    $_dependencies.put("z", blockpos.getZ());
                    $_dependencies.put("world", worldIn);
                    ProcedureWorldGenPleuromeia.executeProcedure($_dependencies);
                    flag = true;
                }
            }
            else {
                boolean waterCriteria = false;
                //Is there water nearby?
                int xct = -5;
                int yct;
                int zct;
                while ((xct < 6) && (!waterCriteria)) {
                    yct = -6;
                    while ((yct <= 1) && (!waterCriteria)) {
                        zct = -5;
                        while ((zct < 6) && (!waterCriteria)) {
                            if ((worldIn.getBlockState(position.add(xct, yct, zct))).getMaterial() == Material.WATER) {
                                waterCriteria = true;
                            }
                            zct = zct + 1;
                        }
                        yct = yct + 1;
                    }
                    xct = xct + 1;
                }
                if (waterCriteria) {
                    java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
                    $_dependencies.put("x", blockpos.getX());
                    $_dependencies.put("y", blockpos.getY());
                    $_dependencies.put("z", blockpos.getZ());
                    $_dependencies.put("world", worldIn);
                    ProcedureWorldGenPleuromeia.executeProcedure($_dependencies);
                    flag = true;
                }
            }
        }

        return flag;
    }
}
