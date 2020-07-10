package deerangle.magicmod.block.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import deerangle.magicmod.block.entity.WandTableTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class WandTableTileEntityRenderer extends TileEntityRenderer<WandTableTileEntity> {

    public WandTableTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    // TODO: add sync packets
    @Override
    public void render(WandTableTileEntity tileEntity, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        double yOff = (1.0 / 16D) * MathHelper.sin((float) MathHelper
                .lerp(partialTicks, tileEntity.getPrevRotation(), tileEntity.getRotation()) * 0.1F);
        matrixStackIn.translate(0.5D, 27.0 / 32D + yOff, 0.5D);
        matrixStackIn.scale(0.7F, 0.7F, 0.7F);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(
                (float) MathHelper.lerp(partialTicks, tileEntity.getPrevRotation(), tileEntity.getRotation()) * 8.0F));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));

        IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty()) {
            Minecraft.getInstance().getItemRenderer()
                    .renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn,
                            matrixStackIn, bufferIn);
        }

        matrixStackIn.pop();
    }

}