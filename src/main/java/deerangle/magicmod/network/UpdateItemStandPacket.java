package deerangle.magicmod.network;

import deerangle.magicmod.block.entity.ItemStandTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateItemStandPacket {

    private BlockPos pos;
    private ItemStack stack;

    public UpdateItemStandPacket() {
    }

    public UpdateItemStandPacket(BlockPos pos, ItemStack stackInSlot) {
        this.pos = pos;
        this.stack = stackInSlot;
    }

    public static void serialize(UpdateItemStandPacket msg, PacketBuffer data) {
        data.writeBlockPos(msg.pos);
        data.writeItemStack(msg.stack);
    }

    public static UpdateItemStandPacket deserialize(PacketBuffer data) {
        return new UpdateItemStandPacket(data.readBlockPos(), data.readItemStack());
    }

    public static void handle(UpdateItemStandPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ItemStandTileEntity clientTable = (ItemStandTileEntity) Minecraft.getInstance().world.getTileEntity(msg.pos);
        Minecraft.getInstance().enqueue(() -> clientTable.setStackToDisplay(msg.stack));
        ctx.get().setPacketHandled(true);
    }

}
