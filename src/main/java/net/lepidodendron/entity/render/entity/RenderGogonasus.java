package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraGogonasus;
import net.lepidodendron.entity.model.entity.ModelGogonasus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGogonasus extends RenderLiving<EntityPrehistoricFloraGogonasus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gogonasus.png");
    public static float getScaler() {
        return 0.7F * 0.295F;
    }
    public RenderGogonasus(RenderManager mgr) {
        super(mgr, new ModelGogonasus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraGogonasus entity) {
        return RenderGogonasus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraGogonasus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraGogonasus entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}