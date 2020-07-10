package deerangle.magicmod.block.entity;

import deerangle.magicmod.container.WandTableContainer;
import deerangle.magicmod.network.PacketHandler;
import deerangle.magicmod.network.RqUpdateWandTablePacket;
import deerangle.magicmod.network.UpdateWandTablePacket;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WandTableTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private int prevRotation = 0;
    private int rotation = 1;

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            if (!world.isRemote) {
                BlockPos pos = WandTableTileEntity.this.getPos();
                PacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(PacketDistributor.TargetPoint
                                .p(pos.getX(), pos.getY(), pos.getZ(), 64,
                                        WandTableTileEntity.this.getWorld().func_234923_W_())),
                        new UpdateWandTablePacket(getPos(), this.getStackInSlot(0)));
            }
        }
    });

    public WandTableTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public WandTableTileEntity() {
        this(TileEntityRegistry.WAND_TABLE);
    }

    @Override
    public void onLoad() {
        if (world.isRemote) {
            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new RqUpdateWandTablePacket(getPos()));
        }
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
