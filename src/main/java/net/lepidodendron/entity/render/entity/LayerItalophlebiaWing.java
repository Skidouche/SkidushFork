package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraItalophlebia;
import net.lepidodendron.entity.model.entity.ModelProtozygoptera;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerItalophlebiaWing implements LayerRenderer<EntityPrehistoricFloraItalophlebia>
{
    private final RenderItalophlebia ItalophlebiaRenderer;
    private final ModelBase ItalophlebiaModel = new ModelProtozygoptera();
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/Italophlebia_wing.png");

    public LayerItalophlebiaWing(RenderItalophlebia ItalophlebiaRendererIn)
    {
        this.ItalophlebiaRenderer = ItalophlebiaRendererIn;
    }

    @Override
    public void doRenderLayer(EntityPrehistoricFloraItalophlebia entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        if (!entitylivingbaseIn.isInvisible())
        {
            this.ItalophlebiaRenderer.bindTexture(TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.ItalophlebiaModel.setModelAttributes(this.ItalophlebiaRenderer.getMainModel());
            this.ItalophlebiaModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
            this.ItalophlebiaModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
