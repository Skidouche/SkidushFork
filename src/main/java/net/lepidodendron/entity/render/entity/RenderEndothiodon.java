package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraEndothiodon;
import net.lepidodendron.entity.model.entity.ModelEndothiodon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEndothiodon extends RenderLiving<EntityPrehistoricFloraEndothiodon> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/endothiodon.png");

    public RenderEndothiodon(RenderManager mgr) {
        super(mgr, new ModelEndothiodon(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraEndothiodon entity) {
        return RenderEndothiodon.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraEndothiodon entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}


