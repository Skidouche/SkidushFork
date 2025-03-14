
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.entity.EntityPrehistoricFloraDvinosaurus;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockAmphibianSpawnDvinosaurusPlaceable extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:amphibian_spawn_dvinosaurus")
	public static final Block block = null;
	public BlockAmphibianSpawnDvinosaurusPlaceable(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.amphibian_spawn_dvinosaurus_worldgen);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("amphibian_spawn_dvinosaurus"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:amphibian_spawn_dvinosaurus", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockAmphibianSpawnDvinosaurusPlaceable.LEVEL).build());
	}
	
	public static class BlockCustom extends BlockMobSpawn  {
		public BlockCustom() {
			setTranslationKey("pf_amphibian_spawn_dvinosaurus");
			this.setTickRandomly(true);
			setCreativeTab(null);
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return new ItemStack(Items.SLIME_BALL, (int) (1)).getItem();
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockAmphibianSpawnDvinosaurusPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
		{
			super.updateTick(worldIn, pos, state, rand);
			if (!(worldIn.isRemote)) {
				EntityPrehistoricFloraAgeableBase.summon(worldIn, EntityList.getKey(EntityPrehistoricFloraDvinosaurus.class).toString(), "{AgeTicks:0}", (double)pos.getX() , (double)pos.getY(), (double)pos.getZ());
			}
			worldIn.destroyBlock(pos, false);
		}

		/*@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: " + EntityPrehistoricFloraDvinosaurus.getHabitat() + " Temnospondyl");
				tooltip.add("Periods: " + EntityPrehistoricFloraDvinosaurus.getPeriod());
				super.addInformation(stack, player, tooltip, advanced);
			}
		}*/
	}
}