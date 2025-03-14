package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockPteridinium;
import net.lepidodendron.entity.model.tile.ModelPteridinium;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPteridinium extends TileEntitySpecialRenderer<BlockPteridinium.TileEntityCustom> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/Pteridinium.png");
    private final ModelPteridinium modelPteridinium;
    public static final PropertyDirection FACING = BlockDirectional.FACING;

    public RenderPteridinium() {
        this.modelPteridinium = new ModelPteridinium();
    }

    @Override
    public void render(BlockPteridinium.TileEntityCustom entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            facing = entity.getWorld().getBlockState(entity.getPos()).getValue(FACING);
        }
        this.bindTexture(TEXTURE);
        ModelPteridinium modelPteridinium = this.modelPteridinium;
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.pushMatrix();
        if ((!LepidodendronConfig.renderEdiacaranLighting) && entity.getWorld().provider.getDimension() == LepidodendronConfig.dimPrecambrian) {
            GlStateManager.disableLighting();
        }
        GlStateManager.translate(x + 0.5, y + 1.2, z + 0.5);
        GlStateManager.scale(0.5,0.5,0.5);
        GlStateManager.rotate(180, 0F, 0F, 1F);
        GlStateManager.rotate(facing.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
        modelPteridinium.renderAll(Minecraft.getMinecraft().player.ticksExisted);
        if ((!LepidodendronConfig.renderEdiacaranLighting) && entity.getWorld().provider.getDimension() == LepidodendronConfig.dimPrecambrian) {
            GlStateManager.enableLighting();
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}