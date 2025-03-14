package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraLongisquama;
import net.lepidodendron.entity.model.entity.ModelLongisquama;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLongisquama extends RenderLiving<EntityPrehistoricFloraLongisquama> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/longisquama.png");

    public RenderLongisquama(RenderManager mgr) {
        super(mgr, new ModelLongisquama(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraLongisquama entity) {
        return RenderLongisquama.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraLongisquama entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}



