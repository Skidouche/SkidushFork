
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.EntityMateAISlitheringWaterBase;
import net.lepidodendron.entity.ai.SlitheringWanderBottom;
import net.lepidodendron.entity.base.EntityPrehistoricFloraSlitheringWaterBase;
import net.lepidodendron.item.ItemFishFood;
import net.lepidodendron.item.entities.ItemEchinodermEggsArchaeocidaris;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraArchaeocidaris extends EntityPrehistoricFloraSlitheringWaterBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int animationTick;
	private Animation animation = NO_ANIMATION;

	public EntityPrehistoricFloraArchaeocidaris(World world) {
		super(world, 0);//Does not slither
		setSize(0.26F, 0.2F);
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Devonian - Carboniferous - Permian";}

	//public static String getHabitat() {return "Aquatic";}

	@Override
	public boolean dropsEggs() {
		return false;
	}

	protected float getAISpeedSlithering() {
		return 0.0275f;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAISlitheringWaterBase(this, 1));
		tasks.addTask(1, new SlitheringWanderBottom(this, NO_ANIMATION));
		tasks.addTask(2, new EntityAILookIdle(this));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return (stack.getItem() == new ItemStack(ItemFishFood.block, (int) (1)).getItem());
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.ARCHAEOCIDARIS_LOOT;
	}

	@Override
	public ItemStack getPropagule() {
		return new ItemStack(ItemEchinodermEggsArchaeocidaris.block, 1);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (player.getHeldItem(hand).isEmpty()) {
			player.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
		return super.processInteract(player, hand);
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if (entityIn instanceof EntityPlayer) {
			entityIn.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
	}

}