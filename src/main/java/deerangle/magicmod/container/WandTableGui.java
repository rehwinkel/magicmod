package deerangle.magicmod.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class WandTableGui extends ContainerScreen<WandTableContainer> {

    private static final ResourceLocation WAND_TABLE_TEXTURE = new ResourceLocation("magicmod",
            "textures/gui/container/wand_table.png");

    public WandTableGui(WandTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        ySize = 204;
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int mouseX, int mouseY, float p_230430_4_) {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, p_230430_4_);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }

    @Override
    public void func_230450_a_(MatrixStack matrixStack, float p_230450_2_, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_230706_i_.getTextureManager().bindTexture(WAND_TABLE_TEXTURE);
        int i = this.guiLeft;
        int j = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    // TODO: fix name locations
    @Override
    public void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, (float) this.field_238742_p_,
                (float) this.field_238743_q_, 4210752);
        this.field_230712_o_
                .func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), (float) this.field_238744_r_,
                        (float) this.field_238745_s_, 4210752);
    }

}
