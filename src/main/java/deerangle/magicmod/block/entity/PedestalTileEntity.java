package deerangle.magicmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class PedestalTileEntity extends ItemStandTileEntity {

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            PedestalTileEntity.super.updateDisplayNear();
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    });

    public PedestalTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public PedestalTileEntity() {
        super(TileEntityRegistry.PEDESTAL);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public ItemStack getStackToDisplay() {
        IItemHandler itemHandler = this.inventory.orElse(null);
        return itemHandler.getStackInSlot(0);
    }

    @Override
    public void setStackToDisplay(ItemStack stack) {
        ItemStackHandler itemHandler = this.inventory.orElse(null);
        itemHandler.setStackInSlot(0, stack);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.orElse(null).serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        inventory.orElse(null).deserializeNBT(compound.getCompound("inventory"));
        super.read(state, compound);
    }

}
