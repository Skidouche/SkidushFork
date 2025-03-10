package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraOrnithoprion;
import net.lepidodendron.entity.model.entity.ModelOrnithoprion;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderOrnithoprion extends RenderLiving<EntityPrehistoricFloraOrnithoprion> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ornithoprion.png");

    public RenderOrnithoprion(RenderManager mgr) {
        super(mgr, new ModelOrnithoprion(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraOrnithoprion entity) {
        return RenderOrnithoprion.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraOrnithoprion entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraOrnithoprion entity, float f) {
        float scale = entity.getAgeScale() * 0.5F * 1.2F;
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}




