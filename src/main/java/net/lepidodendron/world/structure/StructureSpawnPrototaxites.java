
package net.lepidodendron.world.structure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronConfigPlants;
import net.lepidodendron.LepidodendronDecorationHandler;
import net.lepidodendron.block.BlockPrototaxites;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class StructureSpawnPrototaxites extends ElementsLepidodendronMod.ModElement {
	public StructureSpawnPrototaxites(ElementsLepidodendronMod instance) {
		super(instance, 48);
	}

	@Override
	public void generateWorld(Random random, int i2, int k2, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		boolean isNetherType = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfigPlants.dimPrototaxites))
			dimensionCriteria = true;
		if (!LepidodendronConfigPlants.genPrototaxites && !LepidodendronConfig.genAllPlants)
			dimensionCriteria = false;
		if (!dimensionCriteria)
			return;

		boolean biomeCriteria = false;
		Biome biome = world.getBiome(new BlockPos(i2, world.getSeaLevel(), k2));
		if ((!matchBiome(biome, LepidodendronConfig.genGlobalBlacklist)) && (!matchBiome(biome, LepidodendronConfigPlants.genPrototaxitesBlacklistBiomes))) {
			biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH))
				biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN))
				biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
				biomeCriteria = false;
			//if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
			//	biomeCriteria = false;
		}
		if (matchBiome(biome, LepidodendronConfigPlants.genPrototaxitesOverrideBiomes))
			biomeCriteria = true;
		if (!biomeCriteria)
			return;

		int GenChance = 15500;
		int count = 2;
		if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM)) {
			GenChance = 80000;
			count = 10;
		}
			
		double GenMultiplier = LepidodendronConfigPlants.multiplierPrototaxites;
		if (GenMultiplier < 0) {GenMultiplier = 0;}
		GenChance = Math.min(300000, (int) Math.round((double) GenChance * GenMultiplier));
		//Is this a transformed biome?
		if (LepidodendronDecorationHandler.matchBiome(biome, LepidodendronConfigPlants.genTransformBiomes)) {
			//if (biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":")).equalsIgnoreCase("minecraft"))
				GenChance = Math.min(GenChance * 5, 300000);
		}
		
		if ((random.nextInt(1000000) + 1) <= GenChance) {
			//int count = 2;
			for (int a = 0; a < count; a++) {
				int i = i2 + random.nextInt(16) + 8;
				int k = k2 + random.nextInt(16) + 8;
				int height = 255;
				if (isNetherType) {
					boolean notpassed = true;
					while (height > 0) {
						if (notpassed && world.isAirBlock(new BlockPos(i, height, k)))
							notpassed = false;
						else if (!notpassed && !world.isAirBlock(new BlockPos(i, height, k)))
							break;
						height--;
					}
				} else {
					while (height > 0) {
						if (
							(!world.isAirBlock(new BlockPos(i, height, k)))
							&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.VINE)
							&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.WEB)
							&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.PLANTS)
						)
							break;
						height--;
					}
				}
				int j = height;

				if (!canSurviveAt(world, new BlockPos(i, j + 1, k)))
					continue;
		
				int maxheight = LepidodendronConfigPlants.maxheightPrototaxites;
				int minheight = LepidodendronConfigPlants.minheightPrototaxites;
				if (maxheight < 0) {maxheight = 0;}
				if (maxheight > 250) {maxheight = 250;}
				if (minheight < 1) {minheight = 1;}
				if (minheight > 250) {minheight = 250;}
				if (j + 1 < minheight)
					continue;
				if (j + 1 > maxheight && maxheight != 0)
					continue;
					
					
				biomeCriteria = false;
				biome = world.getBiome(new BlockPos(i, j + 1, k));
				if ((!matchBiome(biome, LepidodendronConfig.genGlobalBlacklist)) && (!matchBiome(biome, LepidodendronConfigPlants.genPrototaxitesBlacklistBiomes))) {
					biomeCriteria = false;
					if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY))
						biomeCriteria = true;
					if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
						biomeCriteria = true;
					if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH))
						biomeCriteria = false;
					if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN))
						biomeCriteria = false;
					if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
						biomeCriteria = false;
				}
				if (matchBiome(biome, LepidodendronConfigPlants.genPrototaxitesOverrideBiomes))
					biomeCriteria = true;
				if (!biomeCriteria)
					continue;
				if (world.isRemote)
					return;

				BlockPos spawnTo = new BlockPos(i, j + 1, k);
				
				int x = spawnTo.getX();
				int y = spawnTo.getY();
				int z = spawnTo.getZ();
				if (!world.isBlockLoaded(spawnTo)) {
					continue;
				}
				if (!world.isAreaLoaded(spawnTo, 3)) {
					continue;
				}

				
				if ((world.canSeeSky(spawnTo)) || 
				(((world.getBlockState(spawnTo)).getMaterial() == Material.SNOW)
				&& world.canSeeSky(spawnTo.up()))) {
					world.setBlockToAir(spawnTo);
					world.setBlockToAir(spawnTo.up());
				}
				world.setBlockState(spawnTo, BlockPrototaxites.block.getDefaultState(), 3);

			}
		}
	}

	
		public boolean shouldGenerateInDimension(int id, int[] dims) {
		int[] var2 = dims;
		int var3 = dims.length;
		for (int var4 = 0; var4 < var3; ++var4) {
			int dim = var2[var4];
			if (dim == id) {
				return true;
			}
		}
		return false;
	}

 	public static boolean matchBiome(Biome biome, String[] biomesList) {
    	
    	//String regName = biome.getRegistryName().toString();
    	
        String[] var2 = biomesList;
        int var3 = biomesList.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String checkBiome = var2[var4];
            if (!checkBiome.contains(":")) {
            	//System.err.println("modid test: " + biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":") - 1));
	            if (checkBiome.equalsIgnoreCase(
	            	biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":"))
	            	)) {
	                return true;
	            }
        	}
        	if (checkBiome.equalsIgnoreCase(biome.getRegistryName().toString())) {
                return true;
            }
        }

        return false;
    }

    public boolean canSurviveAt(World worldIn, BlockPos pos) {
			
		int distH = (int) LepidodendronConfigPlants.waterPrototaxitesHorizontal;
		if (distH < 1) distH = 1;
		if (distH > 16) distH = 16;
		int distV = (int) LepidodendronConfigPlants.waterPrototaxitesVertical;
		if (distV < 1) distV = 1;
		if (distV > 6) distV = 6;
		boolean waterCriteria = false;
		int xct = -distH;
		int yct;
		int zct;
		while ((xct <= distH) && (!waterCriteria)) {
			yct = -distV;
			while ((yct <= 1) && (!waterCriteria)) {
				zct = -distH;
				while ((zct <= distH) && (!waterCriteria)) {
					if ((Math.pow((int) Math.abs(xct),2) + Math.pow((int) Math.abs(zct),2) <= Math.pow((int) distH,2)) && ((worldIn.getBlockState(new BlockPos(pos.getX() + xct, pos.getY() + yct, pos.getZ() + zct))).getMaterial() == Material.WATER)) {
						waterCriteria = true;
					}
					zct = zct + 1;
				}
				yct = yct + 1;
			}
			xct = xct + 1;
		}

    	if (!waterCriteria) return false;
    	
    	if ((worldIn.getBlockState(pos.down()).getMaterial() != Material.SAND) 
    		&& (worldIn.getBlockState(pos.down()).getBlock() != Blocks.MYCELIUM))
    	{
    		return false;
    	}

    	return true;
    	 
    }
	
}
