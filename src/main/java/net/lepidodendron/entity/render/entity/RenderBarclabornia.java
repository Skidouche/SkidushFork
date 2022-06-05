package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBarbclabornia;
import net.lepidodendron.entity.model.entity.ModelTitanicthys;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBarclabornia extends RenderLiving<EntityPrehistoricFloraBarbclabornia> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/barbclabornia.png");

    public RenderBarclabornia(RenderManager mgr) {
        super(mgr, new ModelTitanicthys(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBarbclabornia entity) {
        float scale = entity.getAgeScale();
        //System.err.println("AgeScale: " + scale);
        return RenderBarclabornia.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBarbclabornia entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraBarbclabornia entity, float f) {
        float scale = entity.getAgeScale();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}