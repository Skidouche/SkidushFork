package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraKujdanowiaspis;
import net.lepidodendron.entity.model.entity.ModelKujdanowiaspis;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKujdanowiaspis extends RenderLiving<EntityPrehistoricFloraKujdanowiaspis> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kujdanowiaspis.png");
    public static float getScaler() {
        return 0.7F * 0.395F;
    }
    public RenderKujdanowiaspis(RenderManager mgr) {
        super(mgr, new ModelKujdanowiaspis(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraKujdanowiaspis entity) {
        return RenderKujdanowiaspis.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraKujdanowiaspis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraKujdanowiaspis entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}