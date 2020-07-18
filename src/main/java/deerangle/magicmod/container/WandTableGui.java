package deerangle.magicmod.container;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
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

    protected void init() {
        super.init();
        this.button0 = this
                .addButton(new Button(guiLeft + 56, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(0);
                }));
        this.button1 = this
                .addButton(new Button(guiLeft + 76, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(1);
                }));
        this.button2 = this
                .addButton(new Button(guiLeft + 96, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(2);
                }));
        this.button3 = this
                .addButton(new Button(guiLeft + 116, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(3);
                }));
        this.button4 = this
                .addButton(new Button(guiLeft + 136, guiTop + 33, 20, 20, new StringTextComponent(""), (button) -> {
                    buttonPressed(4);
                }));
        this.button0.active = false;
        this.button1.active = false;
        this.button2.active = false;
        this.button3.active = false;
        this.button4.active = false;
    }

    public void tick() {
        super.tick();
        ItemStack wand = this.container.getSlot(0).getStack();
        Spell spell = this.container.getTabletSpell();
        if (spell != null) {
            if (wand.getItem() == ItemRegistry.BASIC_WAND) {
                this.button0.active = false;
                this.button1.active = false;
                this.button2.active = true;
                this.button3.active = false;
                this.button4.active = false;
            } else if (wand.getItem() == ItemRegistry.ADVANCED_WAND) {
                this.button0.active = false;
                this.button1.active = true;
                this.button2.active = true;
                this.button3.active = true;
                this.button4.active = false;
            } else if (wand.getItem() == ItemRegistry.MASTER_WAND) {
                this.button0.active = true;
                this.button1.active = true;
                this.button2.active = true;
                this.button3.active = true;
                this.button4.active = true;
            } else {
                this.button0.active = false;
                this.button1.active = false;
                this.button2.active = false;
                this.button3.active = false;
                this.button4.active = false;
            }
        } else {
            this.button0.active = false;
            this.button1.active = false;
            this.button2.active = false;
            this.button3.active = false;
            this.button4.active = false;
        }
    }

    private void buttonPressed(int buttonId) {
        if (this.container.applySpell(buttonId)) {
            PacketHandler.INSTANCE
                    .send(PacketDistributor.SERVER.noArg(), new ApplySpellMessage(this.container.windowId, buttonId));
        }
    }

    @Override
    // drawGui
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float p_230430_4_) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, p_230430_4_);
        this.func_230459_a_(matrixStack, mouseX, mouseY);

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
                int leftX = guiLeft + 56 + startX;
                if (mouseX >= leftX && mouseX <= leftX + 19 && mouseY >= guiTop + 33 && mouseY <= guiTop + 52) {
                    List<ITextComponent> tooltip = new ArrayList<>();
                    tooltip.add(spell == null ? new TranslationTextComponent("info.magicmod.no_spell") : spell
                            .getTextComponent());
                    this.renderTooltip(matrixStack, tooltip, mouseX, mouseY);
                }
                startX += 20;
            }
        }
    }

    @Override
    // drawGuiBackground
    public void func_230450_a_(MatrixStack matrixStack, float p_230450_2_, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(WAND_TABLE_TEXTURE);
        int i = this.guiLeft;
        int j = (this.height - this.ySize) / 2;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    public void drawSpellIcon(MatrixStack matrixStack, int x, int y, Spell spell) {
        this.minecraft.getTextureManager().bindTexture(SPELL_ICONS_TEXTURE);
        // drawRectangle
        blit(matrixStack, x, y, 3, (float) spell.getXOffset(), (float) spell.getYOffset(), 16, 16, 16, 16);
    }

    @Override
    // drawGuiForeground
    public void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.func_238422_b_(matrixStack, this.title, (float) this.field_238742_p_, (float) this.field_238743_q_,
                4210752);
        this.font.func_238422_b_(matrixStack, this.playerInventory.getDisplayName(), (float) this.field_238744_r_,
                (float) this.field_238745_s_, 4210752);

        Spell spelle = this.container.getTabletSpell();
        if (spelle != null) {
            ITextComponent text = spelle.getTextComponent();
            this.font.func_238422_b_(matrixStack, text, 106F - this.font.func_238414_a_(text) / 2F, 20F, 4210752);
        }

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
                    drawSpellIcon(matrixStack, 58 + startX, 35, spell);
                }
                startX += 20;
            }
        }
    }

}
