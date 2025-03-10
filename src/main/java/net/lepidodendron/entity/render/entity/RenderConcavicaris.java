package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraConcavicaris;
import net.lepidodendron.entity.model.entity.ModelConcavicaris;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderConcavicaris extends RenderLiving<EntityPrehistoricFloraConcavicaris> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/concavicaris.png");
    public static float getScaler() {
        return 0.7F * 0.3F;
    }
    public RenderConcavicaris(RenderManager mgr) {
        super(mgr, new ModelConcavicaris(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraConcavicaris entity) {
        return RenderConcavicaris.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraConcavicaris entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraConcavicaris entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}