package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraMegazostrodon;
import net.lepidodendron.entity.model.entity.ModelMegazostrodon;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMegazostrodon extends RenderLiving<EntityPrehistoricFloraMegazostrodon> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/megazostrodon.png");

    public RenderMegazostrodon(RenderManager mgr) {
        super(mgr, new ModelMegazostrodon(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraMegazostrodon entity) {
        return RenderMegazostrodon.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraMegazostrodon entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}




































