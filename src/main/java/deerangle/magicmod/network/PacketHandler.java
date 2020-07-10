package deerangle.magicmod.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry
            .newSimpleChannel(new ResourceLocation("magicmod", "main"), () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void registerPackets() {
        INSTANCE.registerMessage(0, UpdateWandTablePacket.class, UpdateWandTablePacket::serialize,
                UpdateWandTablePacket::deserialize, UpdateWandTablePacket::handle);
        INSTANCE.registerMessage(1, RqUpdateWandTablePacket.class, RqUpdateWandTablePacket::serialize,
                RqUpdateWandTablePacket::deserialize, RqUpdateWandTablePacket::handle);
    }

}
