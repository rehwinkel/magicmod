package deerangle.magicmod.network;

import deerangle.magicmod.block.entity.WandTableTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Supplier;

public class UpdateWandTablePacket {

    private BlockPos pos;
    private ItemStack stack;

    public UpdateWandTablePacket() {
    }

    public UpdateWandTablePacket(BlockPos pos, ItemStack stackInSlot) {
        this.pos = pos;
        this.stack = stackInSlot;
    }

    public static void serialize(UpdateWandTablePacket msg, PacketBuffer data) {
        data.writeBlockPos(msg.pos);
        data.writeItemStack(msg.stack);
    }

    public static UpdateWandTablePacket deserialize(PacketBuffer data) {
        return new UpdateWandTablePacket(data.readBlockPos(), data.readItemStack());
    }

    public static void handle(UpdateWandTablePacket msg, Supplier<NetworkEvent.Context> ctx) {
        WandTableTileEntity clientTable = (WandTableTileEntity) Minecraft.getInstance().world.getTileEntity(msg.pos);
        ItemStackHandler clientInventory = (ItemStackHandler) clientTable
                .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        Minecraft.getInstance().enqueue(() -> {
            System.out.println("shutee" + msg.stack);
            clientInventory.setStackInSlot(0, msg.stack);
        });
        ctx.get().setPacketHandled(true);
    }

}
