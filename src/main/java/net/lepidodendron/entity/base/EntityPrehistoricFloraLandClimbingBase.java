package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.block.BlockCageSmall;
import net.lepidodendron.block.BlockGlassJar;
import net.lepidodendron.util.MaterialResin;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class EntityPrehistoricFloraLandClimbingBase extends EntityPrehistoricFloraLandBase {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private static final DataParameter<Boolean> CLIMBING = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HEADCOLLIDED = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> STARTINGTOCLIMB = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<EnumFacing> CLIMBINGFACING = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.FACING);
    private static final DataParameter<Integer> CLIMBINGCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> HEADBLOCKCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricFloraLandClimbingBase.class, DataSerializers.VARINT);
    private int inPFLove;

    public EntityPrehistoricFloraLandClimbingBase(World world) {
        super(world);
        //this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.selectNavigator();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setIsClimbing(false);
        this.setHeadCollided(false);
        this.setStartingToClimb(false);
        this.setClimbFacing(EnumFacing.DOWN);
        this.setClimbingCooldown(0);
        this.setHeadBlockCooldown(0);
        return livingdata;
    }

    public boolean isBlockClimbable(World world, BlockPos pos, EnumFacing facing) {
        if (this.world.getBlockState(this.getPosition()).getBlock() == BlockGlassJar.block
            ||  this.world.getBlockState(this.getPosition()).getBlock() == BlockCageSmall.block) {
            return false;
        }
        IBlockState state = world.getBlockState(pos);
        if (state.getMaterial() == Material.WATER
            || state.getMaterial() == Material.LAVA
            || state.getMaterial() == Material.AIR
            || state.getMaterial() == Material.GLASS
            || state.getMaterial() == Material.ICE
            || state.getMaterial() == Material.PACKED_ICE
            || state.getMaterial() == Material.BARRIER) {
            return false;
        }
        if (
            state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID
            || state.getBlock().isFullCube(state))
        {
            return true;
        }
        return false;
    }

    public boolean canSwim() {
        return true;
    }

    @Override
    public float getEyeHeight() {
        return this.height/2.0F;
    }

    public float getSwimHeight() {
        return getEyeHeight();
    }

    public boolean homesToNest() {
        return false;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CLIMBING, false);
        this.dataManager.register(STARTINGTOCLIMB, false);
        this.dataManager.register(HEADCOLLIDED, false);
        this.dataManager.register(CLIMBINGFACING, EnumFacing.DOWN);
        this.dataManager.register(CLIMBINGCOOLDOWN, 0);
        this.dataManager.register(HEADBLOCKCOOLDOWN, 0);
    }

    public boolean getIsClimbing()
    {
        return this.dataManager.get(CLIMBING);
    }

    public void setIsClimbing(boolean climbing)
    {
        this.dataManager.set(CLIMBING, climbing);
    }

    public boolean getStartingToClimb()
    {
        return this.dataManager.get(STARTINGTOCLIMB);
    }

    public void setStartingToClimb(boolean startclimbing)
    {
        this.dataManager.set(STARTINGTOCLIMB, startclimbing);
    }

    public boolean getHeadCollided()
    {
        return this.dataManager.get(HEADCOLLIDED);
    }

    public void setHeadCollided(boolean collided)
    {
        this.dataManager.set(HEADCOLLIDED, collided);
    }

    public int getClimbingCooldown()
    {
        return this.dataManager.get(CLIMBINGCOOLDOWN);
    }

    public void setClimbingCooldown(int cooldown)
    {
        this.dataManager.set(CLIMBINGCOOLDOWN, cooldown);
    }

    public int getHeadBlockCooldown()
    {
        return this.dataManager.get(HEADBLOCKCOOLDOWN);
    }

    public void setHeadBlockCooldown(int cooldown)
    {
        this.dataManager.set(HEADBLOCKCOOLDOWN, cooldown);
    }

    public EnumFacing getClimbFacing() { return this.dataManager.get(CLIMBINGFACING); }

    public void setClimbFacing(EnumFacing climbFace)
    {
        this.dataManager.set(CLIMBINGFACING, climbFace);
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setBoolean("climbing", this.getIsClimbing());
        compound.setBoolean("startingtoclimb", this.getStartingToClimb());
        compound.setBoolean("headcollided", this.getHeadCollided());
        compound.setInteger("climbingcooldwn", this.getClimbingCooldown());
        compound.setInteger("headblockcooldwn", this.getHeadBlockCooldown());
        compound.setByte("climbface", (byte) this.dataManager.get(CLIMBINGFACING).getIndex());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setIsClimbing(compound.getBoolean("climbing"));
        this.setStartingToClimb(compound.getBoolean("startingtoclimb"));
        this.setHeadCollided(compound.getBoolean("headcollided"));
        this.setClimbingCooldown(compound.getInteger("climbingcooldwn"));
        this.setHeadBlockCooldown(compound.getInteger("headblockcooldwn"));
        this.dataManager.set(CLIMBINGFACING, EnumFacing.byIndex(compound.getByte("climbface")));
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    { //Rewmove the dust particles when it falls
        if (!this.isInWater())
        {
            this.handleWaterMovement();
        }

        if (!this.world.isRemote && this.fallDistance > 3.0F && onGroundIn)
        {
            float f = (float)MathHelper.ceil(this.fallDistance - 3.0F);
        }

        if (onGroundIn)
        {
            if (this.fallDistance > 0.0F)
            {
                state.getBlock().onFallenUpon(this.world, pos, this, this.fallDistance);
            }

            this.fallDistance = 0.0F;
        }
        else if (y < 0.0D)
        {
            this.fallDistance = (float)((double)this.fallDistance - y);
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float i) {
        if (ds == DamageSource.FALL) { //Immune to fall damage
            return false;
        }
        this.setIsClimbing(false);
        this.setStartingToClimb(false);
        this.setClimbFacing(EnumFacing.DOWN);
        this.setClimbingCooldown(0);
        //this.setHeadBlockCooldown(0);
        //this.setHeadCollided(false);
        //System.err.println("DamageSource: " + ds.getDamageType());
        return super.attackEntityFrom(ds, i);
    }

    protected abstract float getAISpeedLand();

    @Override
    public boolean isOnLadder() {
        return false;
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    public int getClimbCooldown() {
        return 200 + rand.nextInt(400);
    }
    
    @Override
    public void onLivingUpdate() {

        if (!world.isRemote) {
            if (this.getClimbingCooldown() > 0) {
                this.setClimbingCooldown(this.getClimbingCooldown() - 1);
            }
            if (this.getHeadBlockCooldown() > 0) {
                this.setHeadBlockCooldown(this.getHeadBlockCooldown() - rand.nextInt(20));
            }

            IBlockState state = this.world.getBlockState(new BlockPos(this.posX, Math.floor(this.posY), this.posZ));
            if ((!(this.getClimbingCooldown() > 0)) && (!(this.getHeadBlockCooldown() > 0))
                && state.getMaterial() != Material.WATER
                && state.getMaterial() != Material.LAVA
                && state.getMaterial() != MaterialResin.RESIN
                && (!(state.getBlock() instanceof BlockFluidBase))
                && (!(state.getBlock() instanceof BlockLiquid))
            )
            {
                //Randomise the order of priority in checking, to deal with climbing at corners:
                int ii = rand.nextInt(4);
                switch (ii) {
                    case 0:
                    default: {
                        if (this.motionZ <=0 && !this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).north(), EnumFacing.SOUTH)) {
                            this.motionZ = -0.2;
                            this.setStartingToClimb(true);
                        }
                        else {
                            this.setStartingToClimb(false);
                        }
                        if (this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).north(), EnumFacing.SOUTH) && !this.getIsClimbing()) {
                            this.setIsClimbing(true);
                            this.setClimbFacing(EnumFacing.SOUTH);
                            this.setStartingToClimb(false);
                        }
                        break;
                    }

                    case 1: {
                        if (this.motionZ >=0 && !this.collidedHorizontally && this.isBlockClimbable(world,new BlockPos(this.posX, Math.floor(this.posY), this.posZ).south(), EnumFacing.NORTH)) {
                            this.motionZ = 0.2;
                            this.setStartingToClimb(true);
                        }
                        else {
                            this.setStartingToClimb(false);
                        }
                        if (this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).south(), EnumFacing.NORTH) && !this.getIsClimbing()) {
                            this.setIsClimbing(true);
                            this.setClimbFacing(EnumFacing.NORTH);
                            this.setStartingToClimb(false);
                        }
                        break;
                    }

                    case 2: {
                        if (this.motionX >=0 && !this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).east(), EnumFacing.WEST)) {
                            this.motionX = 0.2;
                            this.setStartingToClimb(true);
                        }
                        else {
                            this.setStartingToClimb(false);
                        }
                        if (this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).east(), EnumFacing.WEST) && !this.getIsClimbing()) {
                            this.setIsClimbing(true);
                            this.setClimbFacing(EnumFacing.WEST);
                            this.setStartingToClimb(false);
                        }
                        break;
                    }

                    case 3: {
                        if (this.motionX <=0 && !this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).west(), EnumFacing.EAST)) {
                            this.motionX = -0.2;
                            this.setStartingToClimb(true);
                        }
                        else {
                            this.setStartingToClimb(false);
                        }
                        if (this.collidedHorizontally && this.isBlockClimbable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).west(), EnumFacing.EAST) && !this.getIsClimbing()) {
                            this.setIsClimbing(true);
                            this.setClimbFacing(EnumFacing.EAST);
                            this.setStartingToClimb(false);
                        }
                        break;
                    }

                }

            }
            else {
                this.setStartingToClimb(false);
            }

            //Reset to not climbing if needed:
            if (this.getIsClimbing()) {
                this.setStartingToClimb(false);
                EnumFacing facing = this.getClimbFacing();

                if ((!world.isAirBlock(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()))
                    && !world.getBlockState(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()).getBlock().isPassable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up())
                    && !this.getHeadCollided()) {
                    //System.err.println("Is collided at head");
                    this.setHeadBlockCooldown(2000);
                    this.setHeadCollided(true);
                }
                else if ((!world.isAirBlock(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()))
                    && !world.getBlockState(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()).getBlock().isPassable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up())
                    && (!(this.getHeadBlockCooldown() > 0))) {
                    setIsClimbing(false);
                    this.setStartingToClimb(false);
                    setClimbFacing(EnumFacing.DOWN);
                    this.getNavigator().clearPath();
                    this.setClimbingCooldown(this.getClimbCooldown());
                    this.setHeadCollided(false);
                    //System.err.println("Uncollide at head 1");
                }
                if (!(state.getMaterial() != Material.WATER
                    && state.getMaterial() != Material.LAVA
                    && state.getMaterial() != MaterialResin.RESIN
                    && (!(state.getBlock() instanceof BlockFluidBase))
                    && (!(state.getBlock() instanceof BlockLiquid)))) {
                    setIsClimbing(false);
                    this.setStartingToClimb(false);
                    setClimbFacing(EnumFacing.DOWN);
                    this.getNavigator().clearPath();
                    this.setClimbingCooldown(this.getClimbCooldown());
                    this.setHeadCollided(false);
                    this.setHeadBlockCooldown(0);
                    //System.err.println("Uncollide at head 2");
                }
                if (facing == EnumFacing.DOWN || facing == EnumFacing.UP) {
                    setIsClimbing(false);
                    this.setStartingToClimb(false);
                    setClimbFacing(EnumFacing.DOWN);
                    this.getNavigator().clearPath();
                    this.setClimbingCooldown(this.getClimbCooldown());
                    this.setHeadCollided(false);
                    this.setHeadBlockCooldown(0);
                    //System.err.println("Uncollide at head 3");
                }
                if (!(this.isBlockClimbable(this.world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).offset(facing.getOpposite()), facing))) {
                    setIsClimbing(false);
                    this.setStartingToClimb(false);
                    setClimbFacing(EnumFacing.DOWN);
                    this.getNavigator().clearPath();
                    this.setClimbingCooldown(this.getClimbCooldown());
                    this.setHeadCollided(false);
                    this.setHeadBlockCooldown(0);
                    //System.err.println("Uncollide at head 4");
                    if (facing == EnumFacing.NORTH) {
                        this.motionZ = this.getAIMoveSpeed();
                    }
                    if (facing == EnumFacing.SOUTH) {
                        this.motionZ = -this.getAIMoveSpeed();
                    }
                    if (facing == EnumFacing.WEST) {
                        this.motionX = this.getAIMoveSpeed();
                    }
                    if (facing == EnumFacing.EAST) {
                        this.motionX = -this.getAIMoveSpeed();
                    }

                }
            }

            //System.err.println("Head cooldown: " + this.getHeadBlockCooldown());
            if (((world.isAirBlock(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()))
                    || world.getBlockState(new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()).getBlock().isPassable(world, new BlockPos(this.posX, Math.floor(this.posY), this.posZ).up()))
                    && this.getHeadCollided()) {
                this.setHeadBlockCooldown(0);
                this.setHeadCollided(false);
                //System.err.println("Uncollide at head 5");
            }
            if (this.getHeadCollided() && !(this.getHeadBlockCooldown() > 0)) {
                this.setHeadCollided(false);
                //System.err.println("Uncollide at head 0");
            }
        }

        if (this.getIsClimbing()) {
            switch (getClimbFacing()) {
                case NORTH:
                default:
                    this.renderYawOffset = 0;
                    this.prevRenderYawOffset = 0;
                    this.rotationYaw = 0;
                    this.prevRotationYaw = 0;
                    this.rotationYawHead = 0;
                    this.prevRotationYawHead = 0;
                    this.motionX = 0.0D;
                    if (!this.collidedHorizontally) {
                        this.motionZ = this.getAIMoveSpeed();
                    }
                    break;

                case SOUTH:
                    this.renderYawOffset = 180.0F;
                    this.prevRenderYawOffset = 180.0F;
                    this.rotationYaw = 180.0F;
                    this.prevRotationYaw = 180.0F;
                    this.rotationYawHead = 180.0F;
                    this.prevRotationYawHead = 180.0F;
                    this.motionX = 0.0D;
                    if (!this.collidedHorizontally) {
                        this.motionZ = -this.getAIMoveSpeed();
                    }
                    break;

                case WEST:
                    this.renderYawOffset = 270.0F;
                    this.prevRenderYawOffset = 270.0F;
                    this.rotationYaw = 270.0F;
                    this.prevRotationYaw = 270.0F;
                    this.rotationYawHead = 270.0F;
                    this.prevRotationYawHead = 270.0F;
                    if (!this.collidedHorizontally) {
                        this.motionX = this.getAIMoveSpeed();
                    }
                    this.motionZ = 0.0D;
                    break;

                case EAST:
                    this.renderYawOffset = 90.0F;
                    this.prevRenderYawOffset = 90.0F;
                    this.rotationYaw = 90.0F;
                    this.prevRotationYaw = 90.0F;
                    this.rotationYawHead = 90.0F;
                    this.prevRotationYawHead = 90.0F;
                    if (!this.collidedHorizontally) {
                        this.motionX = -this.getAIMoveSpeed();
                    }
                    this.motionZ = 0.0D;
                    break;
            }
        }
        else {
            this.renderYawOffset = this.rotationYaw;
        }

        super.onLivingUpdate();


    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        float f4;
        if (this.isServerWorld()) {

            double yy = this.posY + Math.max((this.getSwimHeight() - 0.2), 0.1);
            BlockPos posEyes = new BlockPos(this.posX, yy, this.posZ);

            if (this.isReallyInWater() &&
                (world.getBlockState(posEyes).getMaterial() == Material.WATER
                || world.getBlockState(posEyes).getMaterial() == Material.LAVA
                || world.getBlockState(posEyes).getMaterial() == MaterialResin.RESIN)
            ) {
                this.motionY = 0.2D;
            }


            if (this.isReallyInWater()) { //Is in water
                //System.err.println("Is in water");
                this.moveRelative(strafe, vertical, forward, 0.1F);
                f4 = 0.8F;
                float speedModifier = (float) EnchantmentHelper.getDepthStriderModifier(this);
                if (speedModifier > 3.0F) {
                    speedModifier = 3.0F;
                }

                BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

                if (!this.onGround) {
                    speedModifier *= 0.5F;
                }
                if (speedModifier > 0.0F) {
                    f4 += (0.54600006F - f4) * speedModifier / 3.0F;
                }
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                if (this.motionX != 0 || this.motionZ != 0) {
                    this.setIsMoving(true);
                }
                else {
                    this.setIsMoving(false);
                }

                this.motionX *= f4;
                this.motionX *= 0.9;
                this.motionY *= 0.9;
                this.motionY *= f4;
                this.motionZ *= 0.9;
                this.motionZ *= f4;
            }
            else { //is not in water:
                if (!this.isInLava())
                {
                    float f6 = 0.91F;
                    BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

                    if (this.onGround)
                    {
                        IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos);
                        f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                    }

                    float f7 = 0.16277136F / (f6 * f6 * f6);
                    float f8;

                    if (this.onGround)
                    {
                        f8 = this.getAIMoveSpeed() * f7;
                    }
                    else
                    {
                        f8 = this.jumpMovementFactor;
                    }

                    this.moveRelative(strafe, vertical, forward, f8);
                    f6 = 0.91F;

                    if (this.onGround)
                    {
                        IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos.setPos(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ));
                        f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                    }

                    if (this.isOnLadder())
                    {
                        float f9 = 0.15F;
                        this.motionX = MathHelper.clamp(this.motionX, -0.15000000596046448D, 0.15000000596046448D);
                        this.motionZ = MathHelper.clamp(this.motionZ, -0.15000000596046448D, 0.15000000596046448D);
                        this.fallDistance = 0.0F;

                        if (this.motionY < -0.15D)
                        {
                            this.motionY = -0.15D;
                        }

                    }

                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                    if (this.motionX != 0 || this.motionZ != 0) {
                        this.setIsMoving(true);
                    }
                    else {
                        this.setIsMoving(false);
                    }

                    if (this.getIsClimbing() && (!(this.getHeadCollided())))
                    {
                        this.motionY = 0.2D;
                    }

                    if (this.isPotionActive(MobEffects.LEVITATION) && (!(this.getHeadCollided())))
                    {
                        this.motionY += (0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY) * 0.2D;
                    }
                    else
                    {
                        blockpos$pooledmutableblockpos.setPos(this.posX, 0.0D, this.posZ);

                        if (!this.world.isRemote || this.world.isBlockLoaded(blockpos$pooledmutableblockpos) && this.world.getChunk(blockpos$pooledmutableblockpos).isLoaded())
                        {
                            if (!this.hasNoGravity() && (!(this.getHeadCollided())))
                            {
                                this.motionY -= 0.08D;
                            }
                            if (this.getHeadCollided()) {
                                this.motionY = 0.0D;
                            }
                        }
                        else if (this.posY > 0.0D && (!(this.getHeadCollided())))
                        {
                            this.motionY = -0.1D;
                        }
                        else
                        {
                            this.motionY = 0.0D;
                        }
                    }

                    //System.err.println("Ypos: " + this.posY + " Ymotion: " + this.motionY);

                    this.motionY *= 0.9800000190734863D;
                    this.motionX *= (double)f6;
                    this.motionZ *= (double)f6;
                    blockpos$pooledmutableblockpos.release();

                }
                else //is in Lava
                {
                    double d4 = this.posY;
                    this.moveRelative(strafe, vertical, forward, 0.02F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.02D;
                    }

                    if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
                    {
                        this.motionY = 0.30000001192092896D;
                    }
                }
            }
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double deltaX = this.posX - this.prevPosX;
        double deltaZ = this.posZ - this.prevPosZ;
        double deltaY = this.posY - this.prevPosY;
        float delta = MathHelper.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 4.0F;
        if (delta > 1.0F) {
            delta = 1.0F;
        }
        this.limbSwingAmount += (delta - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

}