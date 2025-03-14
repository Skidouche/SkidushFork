package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCoelophysis;
import net.lepidodendron.entity.model.entity.ModelCoelophysis;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCoelophysis extends RenderLiving<EntityPrehistoricFloraCoelophysis> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/coelophysis.png");

    public RenderCoelophysis(RenderManager mgr) {
        super(mgr, new ModelCoelophysis(), 0.5f);
    }

    public static float getScaler() {return 0.495f; }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCoelophysis entity) {
        return RenderCoelophysis.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCoelophysis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraCoelophysis entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.38F;
    }

}