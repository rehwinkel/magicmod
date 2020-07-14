package deerangle.magicmod.container.slot;

import deerangle.magicmod.block.BlockRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class StoneTabletSlot extends SlotItemHandler {

    public StoneTabletSlot(ItemStackHandler inventory, int id, int x, int y) {
        super(inventory, id, x, y);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem() == new ItemStack(BlockRegistry.ENCHANTED_STONE_TABLET).getItem();
    }
}
