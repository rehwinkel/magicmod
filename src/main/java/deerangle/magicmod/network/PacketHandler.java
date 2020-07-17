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
        INSTANCE.registerMessage(0, UpdateItemStandPacket.class, UpdateItemStandPacket::serialize,
                UpdateItemStandPacket::deserialize, UpdateItemStandPacket::handle);
        INSTANCE.registerMessage(1, RqUpdateItemStandPacket.class, RqUpdateItemStandPacket::serialize,
                RqUpdateItemStandPacket::deserialize, RqUpdateItemStandPacket::handle);
        INSTANCE.registerMessage(2, ApplySpellMessage.class, ApplySpellMessage::serialize,
                ApplySpellMessage::deserialize, ApplySpellMessage::handle);
        INSTANCE.registerMessage(3, SpawnMagicMessage.class, SpawnMagicMessage::serialize,
                SpawnMagicMessage::deserialize, SpawnMagicMessage::handle);
    }

}
