package deerangle.magicmod.container;

import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.container.slot.StoneTabletSlot;
import deerangle.magicmod.container.slot.WandSlot;
import deerangle.magicmod.item.ItemRegistry;
import deerangle.magicmod.item.WandItem;
import deerangle.magicmod.spells.Spell;
import deerangle.magicmod.spells.SpellRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class WandTableContainer extends Container {

    public WandTableContainer(int windowId, PlayerInventory inventory) {
        this(windowId, inventory, new ItemStackHandler(2));
    }

    public WandTableContainer(int windowId, PlayerInventory playerInventory, ItemStackHandler inventory) {
        super(ContainerRegistry.WAND_TABLE, windowId);

        this.addSlot(new WandSlot(inventory, 0, 26, 24));
        this.addSlot(new StoneTabletSlot(inventory, 1, 26, 46));

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        for (int i = 0; i < 27; i++) {
            this.addSlot(new Slot(playerInventory, 9 + i, 8 + (i % 9) * 18, 84 + 18 * (i / 9)));
        }
    }

    public boolean applySpell(int buttonID) {
        ItemStack wand = this.getSlot(0).getStack();
        Spell spell = this.getTabletSpell();
        if (spell != null) {
            if (wand.getItem() == ItemRegistry.BASIC_WAND && buttonID == 2) {
                this.getSlot(1).putStack(new ItemStack(BlockRegistry.STONE_TABLET));
                ((WandItem) wand.getItem()).putSpell(wand, 0, 1, spell);
                return true;
            } else if (wand.getItem() == ItemRegistry.ADVANCED_WAND && buttonID >= 1 && buttonID <= 3) {
                this.getSlot(1).putStack(new ItemStack(BlockRegistry.STONE_TABLET));
                ((WandItem) wand.getItem()).putSpell(wand, buttonID - 1, 3, spell);
                return true;
            } else if (wand.getItem() == ItemRegistry.MASTER_WAND) {
                this.getSlot(1).putStack(new ItemStack(BlockRegistry.STONE_TABLET));
                ((WandItem) wand.getItem()).putSpell(wand, buttonID, 5, spell);
                return true;
            }
        }
        return false;
    }

    @Nullable
    public Spell getTabletSpell() {
        ItemStack tablet = this.getSlot(1).getStack();
        if (!tablet.isEmpty() && tablet.getItem() == new ItemStack(BlockRegistry.ENCHANTED_STONE_TABLET).getItem()) {
            return SpellRegistry.ELECTRO; //TODO: fix
        }
        return null;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }


    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        int invSize = 2;
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < invSize) {
                if (!this.mergeItemStack(itemstack1, invSize, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, invSize, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

}
