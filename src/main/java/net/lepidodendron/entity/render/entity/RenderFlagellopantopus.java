package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraFlagellopantopus;
import net.lepidodendron.entity.model.entity.ModelFlagellopantopus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFlagellopantopus extends RenderLiving<EntityPrehistoricFloraFlagellopantopus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/flagellopantopus.png");

    public RenderFlagellopantopus(RenderManager mgr) {
        super(mgr, new ModelFlagellopantopus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraFlagellopantopus entity) {
        return RenderFlagellopantopus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraFlagellopantopus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraFlagellopantopus entity, float f) {
        float scale = 0.3F;
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.15F;
    }

}