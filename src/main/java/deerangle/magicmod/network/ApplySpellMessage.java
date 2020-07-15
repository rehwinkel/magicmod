package deerangle.magicmod.network;

import deerangle.magicmod.container.WandTableContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ApplySpellMessage {

    public int windowId;
    public int buttonId;

    public ApplySpellMessage() {

    }

    public ApplySpellMessage(int windowId, int buttonId) {
        this.windowId = windowId;
        this.buttonId = buttonId;
    }

    public static void serialize(ApplySpellMessage msg, PacketBuffer data) {
        data.writeInt(msg.windowId);
        data.writeInt(msg.buttonId);
    }

    public static ApplySpellMessage deserialize(PacketBuffer data) {
        return new ApplySpellMessage(data.readInt(), data.readInt());
    }

    public static void handle(ApplySpellMessage msg, Supplier<NetworkEvent.Context> ctx) {
        Container container = ctx.get().getSender().openContainer;
        if (container.windowId == msg.windowId) {
            ((WandTableContainer) container).applySpell(msg.buttonId);
        }
        ctx.get().setPacketHandled(true);
    }

}
