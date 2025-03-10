package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraShonisaurus;
import net.lepidodendron.entity.model.entity.ModelShonisaurus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class RenderShonisaurus extends RenderLiving<EntityPrehistoricFloraShonisaurus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/shonisaurus.png");
    
    public RenderShonisaurus(RenderManager mgr) {
        super(mgr, new ModelShonisaurus(), 1.5f);
    }
    public static float getScaler() {return 1.24F; }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraShonisaurus entity) {
        return RenderShonisaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraShonisaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);

        if (entityLiving.deathTime > 0)
        {
            float f = ((float)entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
            f = MathHelper.sqrt(f);

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            GlStateManager.rotate(f * this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
        }
        else
        {
            String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());

            if (s != null && ("Shona".equalsIgnoreCase(s)))
            {
                GlStateManager.scale(2.0,2.0,2.0);
            }
        }
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraShonisaurus entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.65F;
    }

}