package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCaelestiventus;
import net.lepidodendron.entity.model.entity.ModelCaelestiventus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCaelestiventus extends RenderLiving<EntityPrehistoricFloraCaelestiventus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/caelestiventus.png");

    public RenderCaelestiventus(RenderManager mgr) {
        super(mgr, new ModelCaelestiventus(), 0.15f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCaelestiventus entity) {
        return RenderCaelestiventus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCaelestiventus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraCaelestiventus entity, float f) {
        float scale = entity.getAgeScale();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}





