package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCoelurosauravus;
import net.lepidodendron.entity.model.entity.ModelWeigeltisaurid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class RenderCoelurosauravus extends RenderLiving<EntityPrehistoricFloraCoelurosauravus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/coelurosauravus.png");
    private static final ResourceLocation TEXTURE_REX = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/rex.png");

    public RenderCoelurosauravus(RenderManager mgr) {
        super(mgr, new ModelWeigeltisaurid(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCoelurosauravus entity) {
        String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName());
        if (s != null && ("Rex".equalsIgnoreCase(s)))
        {
            return RenderCoelurosauravus.TEXTURE_REX;
        }
        return RenderCoelurosauravus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCoelurosauravus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        switch (entityLiving.getClimbFacing()) {
            case DOWN:
            default:
                break;
            case EAST:
                GlStateManager.translate(0.0F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case WEST:
                GlStateManager.translate(0.0F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 0.05F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 0.5F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }

    }

}