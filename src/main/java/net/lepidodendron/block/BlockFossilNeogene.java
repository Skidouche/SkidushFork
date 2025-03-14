
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.item.ItemFossilNeogene;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockFossilNeogene extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:fossil_neogene")
	public static final Block block = null;

	public BlockFossilNeogene(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.fossil_neogene);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("fossil_neogene"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:fossil_neogene", "inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		if ((dimID == 0 || dimID == LepidodendronConfig.dimPleistocene) && LepidodendronConfig.genFossil)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;

		int blockCount = 8;

		int dimDepth = 12;
		if (dimID == LepidodendronConfig.dimPleistocene) {
			dimDepth = dimDepth - 4;
		}
		int yy = Math.max(world.getSeaLevel() - dimDepth, 1);

		for (int i = 0; i < 48; i++) {
			int x = chunkX + random.nextInt(16);// ffs they built in the offset to the vanilla WorldGenMineable! + 8;
			int y = yy; //Anywhere in the layer which is 4 thick (0-3) //NO th evanilal generator already does this!
			int z = chunkZ + random.nextInt(16);// ffs they built in the offset to the vanilla WorldGenMineable! + 8;
			if (random.nextInt(dimDepth) == 0) {
				(new WorldGenMinable(block.getDefaultState(), blockCount, new com.google.common.base.Predicate<IBlockState>() {
					public boolean apply(IBlockState blockAt) {
						boolean blockCriteria = false;
						IBlockState require;
						if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
							blockCriteria = true;
						return blockCriteria;
					}
				})).generate(world, random, new BlockPos(x, y, z));
			}
		}

	}

	public static class BlockCustom extends BlockFossil {
		public BlockCustom() {
			setTranslationKey("pf_fossil_neogene");
		}

		@Override
		public ItemStack getFossilDrop() {
			return new ItemStack(ItemFossilNeogene.block, 1);
		}
	}
}
