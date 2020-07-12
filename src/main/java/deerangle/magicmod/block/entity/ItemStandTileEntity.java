package deerangle.magicmod.block.entity;

import deerangle.magicmod.network.PacketHandler;
import deerangle.magicmod.network.RqUpdateItemStandPacket;
import deerangle.magicmod.network.UpdateItemStandPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public abstract class ItemStandTileEntity extends TileEntity implements ITickableTileEntity {

    private int prevRotation = 0;
    private int rotation = 1;

    public ItemStandTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void onLoad() {
        if (world.isRemote) {
            PacketHandler.INSTANCE.send(PacketDistributor.SERVER.noArg(), new RqUpdateItemStandPacket(getPos()));
        }
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

    public abstract ItemStack getStackToDisplay();

    public abstract void setStackToDisplay(ItemStack stack);

    public void updateDisplayNear() {
        if (!world.isRemote) {
            BlockPos pos = this.getPos();
            Supplier<PacketDistributor.TargetPoint> target = PacketDistributor.TargetPoint
                    .p(pos.getX(), pos.getY(), pos.getZ(), 64, this.getWorld().func_234923_W_());
            PacketHandler.INSTANCE.send(PacketDistributor.NEAR.with(target),
                    new UpdateItemStandPacket(getPos(), getStackToDisplay()));
        }
    }
}
