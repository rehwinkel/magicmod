package deerangle.magicmod.network;

import deerangle.magicmod.block.entity.WandTableTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.function.Supplier;

public class RqUpdateWandTablePacket {

    private BlockPos pos;

    public RqUpdateWandTablePacket() {
    }

    public RqUpdateWandTablePacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void serialize(RqUpdateWandTablePacket msg, PacketBuffer data) {
        data.writeBlockPos(msg.pos);
    }

    public static RqUpdateWandTablePacket deserialize(PacketBuffer data) {
        return new RqUpdateWandTablePacket(data.readBlockPos());
    }

    public static void handle(RqUpdateWandTablePacket msg, Supplier<NetworkEvent.Context> ctx) {
        World world = ctx.get().getSender().getServerWorld();
        WandTableTileEntity tableTileEntity = (WandTableTileEntity) world.getTileEntity(msg.pos);
        ItemStackHandler inventory = (ItemStackHandler) tableTileEntity
                .getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
        ItemStack itemStack = inventory.getStackInSlot(0);
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ctx.get().getSender()),
                new UpdateWandTablePacket(msg.pos, itemStack));
        ctx.get().setPacketHandled(true);
    }

}
