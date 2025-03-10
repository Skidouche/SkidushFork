package net.lepidodendron.entity.ai;

import net.lepidodendron.entity.base.EntityPrehistoricFloraFishBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.List;
import java.util.Random;

public class EntityMateAIFishBase extends EntityAIBase
{
    private final EntityPrehistoricFloraFishBase animal;
    private final Class <? extends EntityPrehistoricFloraFishBase > mateClass;
    World world;
    private EntityPrehistoricFloraFishBase targetMate;
    int spawnBabyDelay;
    double moveSpeed;

    public EntityMateAIFishBase(EntityPrehistoricFloraFishBase animal, double speedIn)
    {
        this(animal, speedIn, animal.getClass());
    }

    public EntityMateAIFishBase(EntityPrehistoricFloraFishBase animal, double p_i47306_2_, Class <? extends EntityPrehistoricFloraFishBase > p_i47306_4_)
    {
        this.animal = animal;
        this.world = animal.world;
        this.mateClass = p_i47306_4_;
        this.moveSpeed = p_i47306_2_;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        if (!this.animal.isInLove())
        {
            return false;
        }
        else
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    public boolean shouldContinueExecuting()
    {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    public void updateTask()
    {
        this.animal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.animal.getVerticalFaceSpeed());
        this.animal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.animal.getDistanceSq(this.targetMate) < 9.0D)
        {
            this.spawnBaby();
        }
    }

    private EntityPrehistoricFloraFishBase getNearbyMate()
    {
        List<EntityPrehistoricFloraFishBase> list = this.world.<EntityPrehistoricFloraFishBase>getEntitiesWithinAABB(this.mateClass, this.animal.getEntityBoundingBox().grow(8.0D));
        double d0 = Double.MAX_VALUE;
        EntityPrehistoricFloraFishBase EntityPrehistoricFloraFishBase = null;

        for (EntityPrehistoricFloraFishBase EntityPrehistoricFloraFishBase1 : list)
        {
            if (this.animal.canMateWith(EntityPrehistoricFloraFishBase1) && this.animal.getDistanceSq(EntityPrehistoricFloraFishBase1) < d0)
            {
                EntityPrehistoricFloraFishBase = EntityPrehistoricFloraFishBase1;
                d0 = this.animal.getDistanceSq(EntityPrehistoricFloraFishBase1);
            }
        }

        return EntityPrehistoricFloraFishBase;
    }

    public void spawnBaby()
    {
        ItemStack itemstack = this.animal.getPropagule();
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        Random random = this.animal.getRNG();
        String stringEgg = EntityRegistry.getEntry(this.animal.getClass()).getRegistryName().toString();
        itemstack.getTagCompound().setString("creature", stringEgg);
        EntityItem entityToSpawn = new EntityItem(world, this.animal.getPosition().getX(), this.animal.getPosition().getY(), this.animal.getPosition().getZ(), itemstack);
        entityToSpawn.setPickupDelay(10);
        this.animal.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        world.spawnEntity(entityToSpawn);
        this.animal.resetInLove();
        this.targetMate.resetInLove();
        this.animal.setTicks(0);
        this.animal.setNotMateable();
        this.targetMate.setNotMateable();

        for (int i = 0; i < 7; ++i)
        {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextDouble() * (double)this.animal.width * 2.0D - (double)this.animal.width;
            double d4 = 0.5D + random.nextDouble() * (double)this.animal.height;
            double d5 = random.nextDouble() * (double)this.animal.width * 2.0D - (double)this.animal.width;
            this.world.spawnParticle(EnumParticleTypes.HEART, this.animal.posX + d3, this.animal.posY + d4, this.animal.posZ + d5, d0, d1, d2);
        }

        if (this.world.getGameRules().getBoolean("doMobLoot"))
        {
            this.world.spawnEntity(new EntityXPOrb(this.world, this.animal.posX, this.animal.posY, this.animal.posZ, random.nextInt(7) + 1));
        }
    }
}
