package deerangle.magicmod.container.slot;

import deerangle.magicmod.item.WandItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class WandSlot extends SlotItemHandler {

    public WandSlot(ItemStackHandler inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem() instanceof WandItem;
    }
}
