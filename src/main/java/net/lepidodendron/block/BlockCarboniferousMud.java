
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMisc;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockCarboniferousMud extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:carboniferous_mud")
	public static final Block block = null;
	public BlockCarboniferousMud(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.carboniferous_mud);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("carboniferous_mud"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:carboniferous_mud", "inventory"));
	}
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.GROUND);
			setTranslationKey("pf_carboniferous_mud");
			setSoundType(SoundType.SLIME);
			setHarvestLevel("shovel", 0);
			setHardness(0.5F);
			setResistance(0.5F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(TabLepidodendronMisc.tab);
			setDefaultSlipperiness(0.8f);
		}

		@Override
		public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
			net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
			if (plantable == Blocks.MELON_STEM || plantable == Blocks.PUMPKIN_STEM)
			{
				return true;
			}
			switch (plantType)
			{
				case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
				case Plains: return true;
				case Beach:
					boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.south()).getMaterial() == Material.WATER);
					return hasWater;
			}

			return false;
		}

		@Override
		public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
		{
			if ((entityIn instanceof EntityPlayer || entityIn instanceof EntityVillager) && Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking())
			{
				double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
				entityIn.motionX *= d0;
				entityIn.motionZ *= d0;
			}

			super.onEntityWalk(worldIn, pos, entityIn);
		}
	}
}
