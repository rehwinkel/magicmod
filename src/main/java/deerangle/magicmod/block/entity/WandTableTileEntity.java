package deerangle.magicmod.block.entity;

import deerangle.magicmod.container.WandTableContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WandTableTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int prevRotation = 0;
    private int rotation = 1;

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(2));

    public WandTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public WandTableTileEntity() {
        this(TileEntityRegistry.WAND_TABLE);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.cast();
        }
        return super.getCapability(cap);
    }

    public double getPrevRotation() {
        return prevRotation;
    }

    public double getRotation() {
        return rotation;
    }

    @Override
    public void tick() {
        prevRotation = rotation;
        rotation += 1;
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

    // TODO: add nbt storage and loading
    /*
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return super.write(compound);
    }

    @Override
    public void func_230337_a_(BlockState state, CompoundNBT compound) {
        super.func_230337_a_(state, compound);
    }
    */
}
