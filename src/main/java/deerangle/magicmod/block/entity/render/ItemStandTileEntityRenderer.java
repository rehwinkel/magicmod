package deerangle.magicmod.block.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import deerangle.magicmod.block.entity.ItemStandTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class ItemStandTileEntityRenderer extends TileEntityRenderer<ItemStandTileEntity> {

    private final double height;
    private final float scale;
    private final boolean isFlat;

    public ItemStandTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn, double height, float scale, boolean isFlat) {
        super(rendererDispatcherIn);
        this.height = height;
        this.scale = scale;
        this.isFlat = isFlat;
    }

    @Override
    public void render(ItemStandTileEntity tileEntity, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        double yOff = (1.0 / 16D) * MathHelper.sin((float) MathHelper
                .lerp(partialTicks, tileEntity.getPrevRotation(), tileEntity.getRotation()) * 0.1F);
        matrixStackIn.translate(0.5D, height + yOff, 0.5D);
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(
                (float) MathHelper.lerp(partialTicks, tileEntity.getPrevRotation(), tileEntity.getRotation()) * 8.0F));
        if (isFlat) {
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
        }

        ItemStack stack = tileEntity.getStackToDisplay();
        if (!stack.isEmpty()) {
            Minecraft.getInstance().getItemRenderer()
                    .renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn,
                            matrixStackIn, bufferIn);
        }

        matrixStackIn.pop();
    }

}