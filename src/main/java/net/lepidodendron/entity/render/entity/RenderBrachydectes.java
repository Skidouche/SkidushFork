package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBrachydectes;
import net.lepidodendron.entity.model.entity.ModelBrachydectes;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBrachydectes extends RenderLiving<EntityPrehistoricFloraBrachydectes> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/brachydectes.png");

    public RenderBrachydectes(RenderManager mgr) {
        super(mgr, new ModelBrachydectes(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBrachydectes entity) {
        return RenderBrachydectes.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBrachydectes entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}


