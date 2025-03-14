package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockCzekanowskiaSapling;
import net.lepidodendron.procedure.ProcedureWorldGenCzekanowskia;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.HashMap;
import java.util.Random;

public class WorldGenCzekanowskia extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        return true;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position, boolean needsWater)
    {
        boolean flag = false;
        int offset = 8;
        if (needsWater) {
            offset = 5;
        }
        for (int i = 0; i < 36; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(offset) - rand.nextInt(offset), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(offset) - rand.nextInt(offset));

            if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockCzekanowskiaSapling.block.canPlaceBlockAt(worldIn, blockpos)
            )
            if (!needsWater) {
                if ((worldIn.canSeeSky(blockpos))
                    && worldIn.isAirBlock(blockpos.up()) && worldIn.isAirBlock((blockpos.up(2))) && worldIn.isAirBlock((blockpos.up(3))) && worldIn.isAirBlock((blockpos.up(4)))) {

                    //Grow:
                    //if (Math.random() > 0.3) {
                    //    worldIn.setBlockState(blockpos, BlockSahnioxylonShootPlaceable.block.getDefaultState());
                    //}
                    //else {
                        HashMap<String, Object> $_dependencies = new HashMap<>();
                        $_dependencies.put("x", blockpos.getX());
                        $_dependencies.put("y", blockpos.getY());
                        $_dependencies.put("z", blockpos.getZ());
                        $_dependencies.put("world", worldIn);
                        ProcedureWorldGenCzekanowskia.executeProcedure($_dependencies);
                    //}
                }
                flag = true;
            }
            else {
                boolean waterCriteria = false;
                //Is there water nearby?
                int xct = -3;
                int yct;
                int zct;
                while ((xct < 4) && (!waterCriteria)) {
                    yct = -2;
                    while ((yct <= 0) && (!waterCriteria)) {
                        zct = -3;
                        while ((zct < 4) && (!waterCriteria)) {
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
                    if ((worldIn.canSeeSky(blockpos))
                            && worldIn.isAirBlock(blockpos.up()) && worldIn.isAirBlock((blockpos.up(2))) && worldIn.isAirBlock((blockpos.up(3))) && worldIn.isAirBlock((blockpos.up(4)))) {

                        //if (Math.random() > 0.3) {
                        //    worldIn.setBlockState(blockpos, BlockSahnioxylonShootPlaceable.block.getDefaultState());
                       // }
                        //else {
                            HashMap<String, Object> $_dependencies = new HashMap<>();
                            $_dependencies.put("x", blockpos.getX());
                            $_dependencies.put("y", blockpos.getY());
                            $_dependencies.put("z", blockpos.getZ());
                            $_dependencies.put("world", worldIn);
                            ProcedureWorldGenCzekanowskia.executeProcedure($_dependencies);
                        //}
                    }
                    flag = true;
                }
            }
        }

        return flag;
    }
}
