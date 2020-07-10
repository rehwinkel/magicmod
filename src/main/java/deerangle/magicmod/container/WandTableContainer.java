package deerangle.magicmod.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.DispenserContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class WandTableContainer extends Container {

    public WandTableContainer(int windowId, PlayerInventory inventory) {
        this(windowId, inventory, new ItemStackHandler(2));
    }

    public WandTableContainer(int windowId, PlayerInventory playerInventory, ItemStackHandler inventory) {
        super(ContainerRegistry.WAND_TABLE, windowId);
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 180));
        }
        for (int i = 0; i < 27; i++) {
            this.addSlot(new Slot(playerInventory, 9 + i, 8 + (i % 9) * 18, 122 + 18 * (i / 9)));
        }

        this.addSlot(new SlotItemHandler(inventory, 0, 17, 63));
        this.addSlot(new SlotItemHandler(inventory, 1, 17, 85));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }


    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 2) {
                if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

}
