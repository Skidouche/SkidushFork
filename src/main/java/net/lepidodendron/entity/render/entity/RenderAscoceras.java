package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAscoceras;
import net.lepidodendron.entity.model.entity.ModelAscoceras;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAscoceras extends RenderLiving<EntityPrehistoricFloraAscoceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ascoceras_adult.png");
    private static final ResourceLocation TEXTURE_B = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ascoceras_adult_rare.png");

    public RenderAscoceras(RenderManager mgr) {
        super(mgr, new ModelAscoceras(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAscoceras entity) {
        if (entity.getVariant() <= 1) {
            return RenderAscoceras.TEXTURE_B;
        }
        return RenderAscoceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAscoceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraAscoceras entity, float f) {
        float scale = 0.85F;
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}

