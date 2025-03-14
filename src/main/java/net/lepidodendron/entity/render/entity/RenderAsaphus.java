package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAsaphus;
import net.lepidodendron.entity.EntityPrehistoricFloraWalliserops;
import net.lepidodendron.entity.model.entity.ModelAsaphus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAsaphus extends RenderLiving<EntityPrehistoricFloraAsaphus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/asaphus.png");
    public static float getScaler() {
        return  0.38F;
    }

    public RenderAsaphus(RenderManager mgr) {
        super(mgr, new ModelAsaphus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAsaphus entity) {
        return RenderAsaphus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAsaphus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraAsaphus entity, float f) {
        float scale = this.getScaler();
        GlStateManager.scale(scale, scale, scale);
    }

}