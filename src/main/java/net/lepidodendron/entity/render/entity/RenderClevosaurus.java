package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraClevosaurus;
import net.lepidodendron.entity.model.entity.ModelClevosaurus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderClevosaurus extends RenderLiving<EntityPrehistoricFloraClevosaurus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/clevosaurus.png");

    public RenderClevosaurus(RenderManager mgr) {
        super(mgr, new ModelClevosaurus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraClevosaurus entity) {
        return RenderClevosaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraClevosaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}




















































