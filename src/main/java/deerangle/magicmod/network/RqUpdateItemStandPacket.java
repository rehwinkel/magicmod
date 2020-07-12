package deerangle.magicmod.network;

import deerangle.magicmod.block.entity.ItemStandTileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class RqUpdateItemStandPacket {

    private BlockPos pos;

    public RqUpdateItemStandPacket() {
    }

    public RqUpdateItemStandPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void serialize(RqUpdateItemStandPacket msg, PacketBuffer data) {
        data.writeBlockPos(msg.pos);
    }

    public static RqUpdateItemStandPacket deserialize(PacketBuffer data) {
        return new RqUpdateItemStandPacket(data.readBlockPos());
    }

    public static void handle(RqUpdateItemStandPacket msg, Supplier<NetworkEvent.Context> ctx) {
        World world = ctx.get().getSender().getServerWorld();
        ItemStandTileEntity stand = (ItemStandTileEntity) world.getTileEntity(msg.pos);
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ctx.get().getSender()),
                new UpdateItemStandPacket(msg.pos, stand.getStackToDisplay()));
        ctx.get().setPacketHandled(true);
    }

}
