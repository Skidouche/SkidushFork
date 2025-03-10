
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.AttackAI;
import net.lepidodendron.entity.ai.EatFishItemsAI;
import net.lepidodendron.entity.ai.EntityMateAIAgeableBase;
import net.lepidodendron.entity.ai.EurypteridWander;
import net.lepidodendron.entity.base.EntityPrehistoricFloraEurypteridBase;
import net.lepidodendron.item.ItemFishFood;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraAeger extends EntityPrehistoricFloraEurypteridBase {
	private static final DataParameter<Integer> VARIANT= EntityDataManager.createKey(EntityPrehistoricFloraAeger.class, DataSerializers.VARINT);
	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraAeger(World world) {
		super(world);
		setSize(0.2F, 0.2F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		//minSize = 0.2F;
		//maxSize = 1.0F;
		minWidth = 0.1F;
		maxWidth = 0.25F;
		maxHeight = 0.25F;
		maxHealthAgeable = 2.0D;
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Triassic - Jurassic - Cretaceous";}

	//public static String getHabitat() {return "Aquatic";}

	@Override
	public boolean dropsEggs() {
		return true;
	}
	
	@Override
	public boolean laysEggs() {
		return false;
	}

	@Override
	public int getAdultAge() {
		return 1;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAIAgeableBase(this, 1.0D));
		tasks.addTask(1, new AttackAI(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(2, new EurypteridWander(this, NO_ANIMATION));
		tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatFishItemsAI(this));
	}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(VARIANT, (rand.nextInt(10)));
		this.setScaleForAge(false);
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setVariant(rand.nextInt(10));
		return livingdata;
	}
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("variant", this.getVariant());
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setVariant(compound.getInteger("variant"));
	}

	// these two could be used for many different alts in the future
	public int getVariant() {

		return this.dataManager.get(VARIANT);
	}

	public void setVariant(int variant) {

		this.dataManager.set(VARIANT, variant);
	}
	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return (stack.getItem() == ItemFishFood.block);
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public String getTexture() {
		return this.getTexture();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		//this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected double getSwimSpeed() {
		return this.getSwimSpeed();
	}

	@Override
	protected float getAISpeedEurypterid() {
		if (!this.isAtBottom()) {
			return 0.1F;
		}
		return 0.202F;
	}

	@Override
	public boolean isInWater() {
		return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {

		return super.attackEntityFrom(source, (amount * 0.7F));

	}

	//@Override
	//public net.minecraft.util.SoundEvent getAmbientSound() {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_idle"));
	//}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}


	//@Override
	//public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_hurt"));
	//}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	}

	//@Override
	//public net.minecraft.util.SoundEvent getDeathSound() {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_death"));
	//}
	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < (double) this.world.getSeaLevel() && this.isInWater();
	}

	public boolean isNotColliding() {
		return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
	}

	@Override
	public int getTalkInterval() {
		return 120;
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return 1 + this.world.rand.nextInt(3);
	}

	@Override
	public boolean isOnLadder() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}

	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.AEGER_LOOT;
	}

	//Rendering taxidermy:
	//--------------------
//	public static double offsetCase() { return 0.19; }
//
//	public static double offsetWall() {
//		return 0.01;
//	}
//	public static double upperfrontverticallinedepth() {
//		return 1.4;
//	}
//	public static double upperbackverticallinedepth() {return 0.8;}
//	public static double upperfrontlineoffset() {
//		return 0.4;
//	}
//	public static double upperfrontlineoffsetperpendiular() {
//		return -0F;
//	}
//	public static double upperbacklineoffset() {
//		return 0.4;
//	}
//	public static double upperbacklineoffsetperpendiular() {
//		return -0.15F;
//	}
//	public static double lowerfrontverticallinedepth() {
//		return 0;
//	}
//	public static double lowerbackverticallinedepth() {
//		return 0.06;
//	}
//	public static double lowerfrontlineoffset() {
//		return 0;
//	}
//	public static double lowerfrontlineoffsetperpendiular() {
//		return -0F;
//	}
//	public static double lowerbacklineoffset() {
//		return 0;
//	}
//	public static double lowerbacklineoffsetperpendiular() {
//		return -0F;
//	}
//	@SideOnly(Side.CLIENT)
//	public static ResourceLocation textureDisplay() {
//		return RenderDisplays.TEXTURE_TYRANNOPHONTES;
//	}
//	@SideOnly(Side.CLIENT)
//	public static ModelBase modelDisplay() {
//		return RenderDisplays.modelTyrannophontes;
//	}
//	public static float getScaler() {
//		return RenderTyrannophontes.getScaler();
//	}

}