package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraErythrosuchus;
import net.lepidodendron.entity.model.entity.ModelErythrosuchus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderErythrosuchus extends RenderLiving<EntityPrehistoricFloraErythrosuchus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/erythrosuchus.png");

    public RenderErythrosuchus(RenderManager mgr) {
        super(mgr, new ModelErythrosuchus(), 0.5f);
    }
    public static float getScaler() {
        return 0.785F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraErythrosuchus entity) {
        return RenderErythrosuchus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraErythrosuchus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraErythrosuchus entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}