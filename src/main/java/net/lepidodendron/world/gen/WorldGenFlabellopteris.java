package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockFlabellopteris;
import net.lepidodendron.block.BlockFlabellopterisSpore;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFlabellopteris extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 100; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockFlabellopteris.block.canPlaceBlockAt(worldIn, blockpos))
            {
            	if (Math.random() < 0.7) {
					worldIn.setBlockState(blockpos, BlockFlabellopteris.block.getDefaultState(), 2);
				}
				else {
					worldIn.setBlockState(blockpos, BlockFlabellopterisSpore.block.getDefaultState(), 2);
				}
                flag = true;
            }
        }

        return flag;
    }
}
