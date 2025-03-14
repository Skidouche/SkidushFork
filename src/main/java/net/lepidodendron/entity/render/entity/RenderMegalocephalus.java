package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraMegalocephalus;
import net.lepidodendron.entity.model.entity.ModelMegalocephalus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMegalocephalus extends RenderLiving<EntityPrehistoricFloraMegalocephalus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/megalocephalus.png");
    public static float getScaler() {return 0.25f;}

    public RenderMegalocephalus(RenderManager mgr) {
        super(mgr, new ModelMegalocephalus(), 0.26f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraMegalocephalus entity) {
        return RenderMegalocephalus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraMegalocephalus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraMegalocephalus entity, float f) {
        float scale = entity.getAgeScale()*getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}