
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.AgeableFishWanderBottomDweller;
import net.lepidodendron.entity.ai.EatFishFoodAIAgeable;
import net.lepidodendron.entity.ai.EntityMateAI;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableFishBase;
import net.lepidodendron.entity.render.entity.RenderGemuendina;
import net.lepidodendron.entity.render.entity.RenderMussaurus;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.lepidodendron.item.ItemFishFood;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class EntityPrehistoricFloraGemuendina extends EntityPrehistoricFloraAgeableFishBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int animationTick;
	private Animation animation = NO_ANIMATION;

	private static final DataParameter<Integer> BURYTICK = EntityDataManager.createKey(EntityPrehistoricFloraGemuendina.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> BURIED = EntityDataManager.createKey(EntityPrehistoricFloraGemuendina.class, DataSerializers.BOOLEAN);

	private static final DataParameter<Integer> BURYCOUNT = EntityDataManager.createKey(EntityPrehistoricFloraGemuendina.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SWIMCOUNT = EntityDataManager.createKey(EntityPrehistoricFloraGemuendina.class, DataSerializers.VARINT);

	public EntityPrehistoricFloraGemuendina(World world) {
		super(world);
		setSize(0.47F, 0.3F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minWidth = 0.2F;
		maxWidth = 0.47F;
		maxHeight = 0.3F;
		maxHealthAgeable = 4.0D;
	}

	@Override
	public EntityPrehistoricFloraAgeableBase createPFChild(EntityPrehistoricFloraAgeableBase entity) {
		return new EntityPrehistoricFloraGemuendina(this.world);
	}

	@Override
	public int getAdultAge() {
		return 32000;
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Devonian";}

	//public static String getHabitat() {return "Aquatic";}

	@Override
	public boolean dropsEggs() {
		return false;
	}

	@Override
	public boolean laysEggs() {
		return false;
	}

	@Override
	protected float getAISpeedFish() {
		if ((getBuriedTick() > 0) || getBuried()) {return 0.00f;}
		return 0.092f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(BURYTICK, 0);
		this.dataManager.register(BURIED, false);
		this.dataManager.register(BURYCOUNT, 0);
		this.dataManager.register(SWIMCOUNT, 0);
	}

	public int getBuriedTick() {
		return (this.dataManager.get(BURYTICK));
	}

	public int getBuryCount() {
		return (this.dataManager.get(BURYCOUNT));
	}

	public int getSwimCount() {
		return (this.dataManager.get(SWIMCOUNT));
	}

	public boolean getBuried() {
		return (this.dataManager.get(BURIED));
	}

	public void setBuriedTick(int buryTick) {
		//Set at the bottom of the blockpos:
		if (!this.world.isRemote) {
			if (buryTick > 0) {
				this.motionY = this.motionY -= 0.08D;
			}
			this.dataManager.set(BURYTICK, buryTick);
		}
	}

	public void setBuryCount(int buryTick) {
		if (!this.world.isRemote) {
			this.dataManager.set(BURYCOUNT, buryTick);
		}
	}

	public void setSwimCount(int swimTick) {
		if (!this.world.isRemote) {
			this.dataManager.set(SWIMCOUNT, swimTick);
		}
	}

	public void setBuried(boolean buried) {
		//Set at the bottom of the blockpos:
		if (!this.world.isRemote) {
			if (buried) {
				this.motionY = this.motionY -= 0.08D;
				this.setSize(0, 0.3F);
			}
			else {
				this.setSize(0.47F, 0.3F);
			}
			this.dataManager.set(BURIED, buried);
		}
	}

	@Override
	protected boolean isSlowAtBottom() {
		return false;
	}

	@Override
	public int getAnimationTick() {
		return getAnimationTick();
	}

	@Override
	public void setAnimationTick(int tick) {
		animationTick = tick;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}

	@Override
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	@Override
	public Animation[] getAnimations() {
		return null;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAI(this, 1));
		tasks.addTask(1, new AgeableFishWanderBottomDweller(this, NO_ANIMATION));
		this.targetTasks.addTask(0, new EatFishFoodAIAgeable(this));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return (stack.getItem() == ItemFishFood.block);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		setBuried(false);
		setBuryCount(0);
		if (source != DamageSource.DROWN) {
			return super.attackEntityFrom(source, (amount * 0.5F));
		}
		return super.attackEntityFrom(source, amount);
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
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("isBuried", this.getBuried());
		compound.setInteger("buryTicks", this.getBuriedTick());
		compound.setInteger("buryCount", this.getBuryCount());
		compound.setInteger("swimCount", this.getSwimCount());
	}

	//@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setBuried(compound.getBoolean("isBuried"));
		this.setBuriedTick(compound.getInteger("buryTicks"));
		this.setBuryCount(compound.getInteger("buryCount"));
		this.setSwimCount(compound.getInteger("swimCount"));
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

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	public boolean isInBuryPosition() {
		return
				super.isInWater()
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().north())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().south())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().east())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().west())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().north().east())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().north().west())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().south().east())))
						&& (isBuryBlock(this.world.getBlockState(this.getPosition().down().south().west())));
	}

	public void onEntityUpdate() {
		//AnimationHandler.INSTANCE.updateAnimations(this);
		//System.err.println("onEntityUpdate Pos: " + this.getPosition().getX() + " " + this.getPosition().getY() + " "  + this.getPosition().getZ());

		super.onEntityUpdate();

		if (!this.world.isRemote) {

			if (this.isInLove()) {
				setBuried(false);
				setBuryCount(0);
			}
			if (getBuried()) {
				setBuryCount(getBuryCount() + rand.nextInt(3));
			}
			if (getBuryCount() > 2000) {
				setBuryCount(0);
			}

			if (!getBuried()) {
				setSwimCount(getSwimCount() + rand.nextInt(3));
			}
			if (getSwimCount() > 6000) {
				setSwimCount(0);
			}

			setBuriedTick(getBuryTick());
			//System.err.println("getBuried = " + getBuried());
			//System.err.println("getBuriedTick = " + getBuriedTick());

		}
	}

	public int getBuryTick() {
		if (!this.world.isRemote) {
			if (getBuryCount() < 0) {
				return 61 + getBuryCount();
			}
			if (getSwimCount() < 0) {
				return -getSwimCount();
			}
			return 0;
		}
		return 0;
	}

	public boolean isBlockEmpty(World world, BlockPos pos) {
		List<EntityLiving> Entities = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(pos.getX() + 0.25, pos.getY(), pos.getZ() + 0.25, pos.getX() + 0.75, pos.getY() + 0.5,pos.getZ() + 0.75));
		for (EntityLiving currentEntity : Entities) {
			if (currentEntity != this) {
				return false;
			}
		}
		return true;
	}

	public boolean isBuryBlock(IBlockState state) {
		return (state.getMaterial() == Material.SAND || state.getMaterial() == Material.GROUND || state.getMaterial() == Material.CLAY);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!getBuried()) {this.renderYawOffset = this.rotationYaw;}

		if (!this.world.isRemote) {

			if (this.isInLove()) {
				setBuried(false);
				setBuryCount(0);
			}
			if (!(isBuryBlock(this.world.getBlockState(this.getPosition().down())))) {
				setBuried(false);
				setBuryCount(0);
			}
			else {

				if (!isInBuryPosition()) {
					if (getBuried()) {
						setBuried(false);
						setBuryCount(0);
						setSwimCount(-60);
						setBuriedTick(getBuryTick());
					}
				} else { //is in bury position
					if ((!getBuried()) && getSwimCount() > 800 && isBlockEmpty(this.world, this.getPosition())) { //Has swum long enough
						setBuried(true);
						setBuryCount(-60);
						setSwimCount(0);
						setBuriedTick(getBuryTick());
					} else {
						if (getBuryCount() > 1600) {
							if (getBuried()) {
								setBuried(false);
								setBuryCount(0);
								setSwimCount(-60);
								setBuriedTick(getBuryTick());
							}
						}
					}
				}
			}
		}

		if (getBuryTick() > 0) {
			BlockPos posIn = this.getPosition();
			World worldIn = this.world;
			//worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
			if (worldIn instanceof WorldServer) {
				if (Math.random() > 0.85) {
					for (int k = 0; k < 2; ++k) {
						((WorldServer) worldIn).spawnParticle(EnumParticleTypes.WATER_BUBBLE, posIn.getX(), posIn.getY(), posIn.getZ(), (int) 1, 0.5, 0, 0.5, 0.06, new int[0]);
					}
				}
				if (Math.random() > 0.85) {
					for (int k = 0; k < 2; ++k) {
						((WorldServer) worldIn).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posIn.getX(), posIn.getY(), posIn.getZ(), (int) 1, 0.5, 0, 0.5, 0.02, new int[0]);
					}
				}
			}
		}

	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.GEMUENDINA_LOOT;
	}

	//Rendering taxidermy:
	//--------------------
	public static double offsetWall() {
		return -0.03;
	}
	public static double upperfrontverticallinedepth() {
		return 0.8;
	}
	public static double upperbackverticallinedepth() {
		return 0.8;
	}
	public static double upperfrontlineoffset() {
		return 0.2;
	}
	public static double upperfrontlineoffsetperpendiular() {
		return -0.04F;
	}
	public static double upperbacklineoffset() {
		return 0.2;
	}
	public static double upperbacklineoffsetperpendiular() {
		return -0.04F;
	}
	public static double lowerfrontverticallinedepth() {
		return 0;
	}
	public static double lowerbackverticallinedepth() {
		return 0.02;
	}
	public static double lowerfrontlineoffset() {
		return 0;
	}
	public static double lowerfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double lowerbacklineoffset() {
		return -0.14;
	}
	public static double lowerbacklineoffsetperpendiular() {
		return 0.08F;
	}
	@SideOnly(Side.CLIENT)
	public static ResourceLocation textureDisplay() {
		return RenderDisplays.TEXTURE_GEMUENDINA;
	}
	@SideOnly(Side.CLIENT)
	public static ModelBase modelDisplay() {
		return RenderDisplays.modelGemuendina;
	}
	public static float getScaler() {
		return RenderGemuendina.getScaler();
	}
}

