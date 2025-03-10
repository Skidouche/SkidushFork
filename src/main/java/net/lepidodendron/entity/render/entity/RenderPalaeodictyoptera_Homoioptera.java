package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockGlassJar;
import net.lepidodendron.entity.EntityPrehistoricFloraPalaeodictyoptera_Homoioptera;
import net.lepidodendron.entity.model.entity.ModelPalaeodictyoptera;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPalaeodictyoptera_Homoioptera extends RenderLiving<EntityPrehistoricFloraPalaeodictyoptera_Homoioptera> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/homoioptera.png");

    public RenderPalaeodictyoptera_Homoioptera(RenderManager mgr) {
        super(mgr, new ModelPalaeodictyoptera(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPalaeodictyoptera_Homoioptera entity) {
        return RenderPalaeodictyoptera_Homoioptera.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPalaeodictyoptera_Homoioptera entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        switch (entityLiving.getAttachmentFacing()) {
            case DOWN:
            default:
                break;
            case EAST:
                GlStateManager.translate(0.25F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case WEST:
                GlStateManager.translate(-0.25F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 0.05F, -0.25F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 0.05F, 0.25F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 0.5F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPalaeodictyoptera_Homoioptera entity, float f) {
        float scale = 1.0F;
        if (entity.world.getBlockState(entity.getPosition()).getBlock() == BlockGlassJar.block) {
            scale = 0.6F;
        }
        GlStateManager.scale(scale, scale, scale);
    }

}