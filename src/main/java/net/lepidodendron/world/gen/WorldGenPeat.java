package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockPeat;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPeat extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if ((!worldIn.provider.isNether() || blockpos.getY() < 254) && (blockpos.getY() >= worldIn.getSeaLevel() - 1) && worldIn.isAirBlock(blockpos)
            	&& (
            		((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GROUND)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GRASS)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.SAND)
                    || ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.ROCK)
                    || ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.CLAY)
            	)
            )
            if (rand.nextInt(2) == 0)
            {
                worldIn.setBlockState(blockpos.down(), BlockPeat.block.getDefaultState(), 2);
                flag = true;
            }
            else {
                if (((worldIn.getBlockState(blockpos.down(2))).getMaterial() == Material.GROUND)
                        || ((worldIn.getBlockState(blockpos.down(2))).getMaterial() == Material.GRASS)
                        || ((worldIn.getBlockState(blockpos.down(2))).getMaterial() == Material.SAND)
                        || ((worldIn.getBlockState(blockpos.down(2))).getMaterial() == Material.ROCK)
                        || ((worldIn.getBlockState(blockpos.down(2))).getMaterial() == Material.CLAY)) {
                    worldIn.setBlockState(blockpos.down(2), BlockPeat.block.getDefaultState(), 2);
                    flag = true;
                }
            }
        }

        return flag;
    }
}
