package deerangle.magicmod.block.entity;

import deerangle.magicmod.container.WandTableContainer;
import deerangle.magicmod.network.PacketHandler;
import deerangle.magicmod.network.UpdateItemStandPacket;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WandTableTileEntity extends ItemStandTileEntity implements INamedContainerProvider {

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            WandTableTileEntity.super.updateDisplayNear();
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    });

    public WandTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public WandTableTileEntity() {
        this(TileEntityRegistry.WAND_TABLE);
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("block.magicmod.wand_table");
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new WandTableContainer(windowId, playerInventory, inventory.orElse(null));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inventory", inventory.orElse(null).serializeNBT());
        return super.write(compound);
    }

    @Override
    public void func_230337_a_(BlockState state, CompoundNBT compound) {
        inventory.orElse(null).deserializeNBT(compound.getCompound("inventory"));
        super.func_230337_a_(state, compound);
    }

}
