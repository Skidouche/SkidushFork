package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBarameda;
import net.lepidodendron.entity.model.entity.ModelBarameda;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBarameda extends RenderLiving<EntityPrehistoricFloraBarameda> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/barameda.png");

    public RenderBarameda(RenderManager mgr) {
        super(mgr, new ModelBarameda(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBarameda entity) {
        return RenderBarameda.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBarameda entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraBarameda entity, float f) {
        float scale = entity.getAgeScale()*0.8F;
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}