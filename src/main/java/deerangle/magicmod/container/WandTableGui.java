package deerangle.magicmod.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.item.ItemRegistry;
import deerangle.magicmod.item.WandItem;
import deerangle.magicmod.network.ApplySpellMessage;
import deerangle.magicmod.network.PacketHandler;
import deerangle.magicmod.spells.Spell;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.List;

public class WandTableGui extends ContainerScreen<WandTableContainer> {

    private static final ResourceLocation WAND_TABLE_TEXTURE = new ResourceLocation("magicmod",
            "textures/gui/container/wand_table.png");
    private static final ResourceLocation SPELL_ICONS_TEXTURE = new ResourceLocation("magicmod",
            "textures/spells/spell_icons.png");

    private Button button0, button1, button2, button3, button4;

    public WandTableGui(WandTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    protected void func_231160_c_() {
        super.func_231160_c_();
        this.button0 = this
                .func_230480_a_(new Button(guiLeft + 56, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(0);
                }));
        this.button1 = this
                .func_230480_a_(new Button(guiLeft + 76, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(1);
                }));
        this.button2 = this
                .func_230480_a_(new Button(guiLeft + 96, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(2);
                }));
        this.button3 = this.func_230480_a_(
                new Button(guiLeft + 116, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(3);
                }));
        this.button4 = this.func_230480_a_(
                new Button(guiLeft + 136, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(4);
                }));
        this.button0.field_230693_o_ = false;
        this.button1.field_230693_o_ = false;
        this.button2.field_230693_o_ = false;
        this.button3.field_230693_o_ = false;
        this.button4.field_230693_o_ = false;
    }

    public void func_231023_e_() {
        super.func_231023_e_();
        ItemStack wand = this.container.getSlot(0).getStack();
        ItemStack tablet = this.container.getSlot(1).getStack();
        if (!tablet.isEmpty() && tablet.getItem() == new ItemStack(BlockRegistry.ENCHANTED_STONE_TABLET).getItem()) {
            if (wand.getItem() == ItemRegistry.BASIC_WAND) {
                this.button0.field_230693_o_ = false;
                this.button1.field_230693_o_ = false;
                this.button2.field_230693_o_ = true;
                this.button3.field_230693_o_ = false;
                this.button4.field_230693_o_ = false;
            } else if (wand.getItem() == ItemRegistry.ADVANCED_WAND) {
                this.button0.field_230693_o_ = false;
                this.button1.field_230693_o_ = true;
                this.button2.field_230693_o_ = true;
                this.button3.field_230693_o_ = true;
                this.button4.field_230693_o_ = false;
            } else if (wand.getItem() == ItemRegistry.MASTER_WAND) {
                this.button0.field_230693_o_ = true;
                this.button1.field_230693_o_ = true;
                this.button2.field_230693_o_ = true;
                this.button3.field_230693_o_ = true;
                this.button4.field_230693_o_ = true;
            } else {
                this.button0.field_230693_o_ = false;
                this.button1.field_230693_o_ = false;
                this.button2.field_230693_o_ = false;
                this.button3.field_230693_o_ = false;
                this.button4.field_230693_o_ = false;
            }
        } else {
            this.button0.field_230693_o_ = false;
            this.button1.field_230693_o_ = false;
            this.button2.field_230693_o_ = false;
            this.button3.field_230693_o_ = false;
            this.button4.field_230693_o_ = false;
        }
    }

    private void buttonPressed(int buttonId) {
        if (this.container.applySpell(buttonId)) {
            PacketHandler.INSTANCE
                    .send(PacketDistributor.SERVER.noArg(), new ApplySpellMessage(this.container.windowId, buttonId));
        }
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

        ItemStack wand = this.container.getSlot(0).getStack();
        if (!wand.isEmpty() && wand.getItem() instanceof WandItem) {
            List<Spell> spells = ((WandItem) wand.getItem()).readSpells(wand);

            int startX = 0;
            if (wand.getItem() == ItemRegistry.BASIC_WAND) {
                startX = 40;
            } else if (wand.getItem() == ItemRegistry.ADVANCED_WAND) {
                startX = 20;
            }
            for (Spell spell : spells) {
                if (spell != null) {
                    drawSpellIcon(matrixStack, guiLeft + 58 + startX, guiTop + 35, spell);
                }
                startX += 20;
            }
        }
    }

    public void drawSpellIcon(MatrixStack matrixStack, int x, int y, Spell spell) {
        this.field_230706_i_.getTextureManager().bindTexture(SPELL_ICONS_TEXTURE);
        func_238464_a_(matrixStack, x, y, 3, (float) spell.getXOffset(), (float) spell.getYOffset(), 16, 16, 16, 16);
    }

    @Override
    public void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.field_230712_o_.func_238422_b_(matrixStack, this.field_230704_d_, (float) this.field_238742_p_,
                (float) this.field_238743_q_, 4210752);
        this.field_230712_o_
                .func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), (float) this.field_238744_r_,
                        (float) this.field_238745_s_, 4210752);
    }

}
