package deerangle.magicmod.network;

import deerangle.magicmod.particle.AltarParticleData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnMagicMessage {

    private double x, y, z, velX, velY, velZ, targetX, targetY, targetZ;

    public SpawnMagicMessage() {
    }

    public SpawnMagicMessage(double x, double y, double z, double velX, double velY, double velZ, double targetX, double targetY, double targetZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velX = velX;
        this.velY = velY;
        this.velZ = velZ;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
    }

    public static void serialize(SpawnMagicMessage msg, PacketBuffer data) {
        data.writeDouble(msg.x);
        data.writeDouble(msg.y);
        data.writeDouble(msg.z);
        data.writeDouble(msg.velX);
        data.writeDouble(msg.velY);
        data.writeDouble(msg.velZ);
        data.writeDouble(msg.targetX);
        data.writeDouble(msg.targetY);
        data.writeDouble(msg.targetZ);
    }

    public static SpawnMagicMessage deserialize(PacketBuffer data) {
        return new SpawnMagicMessage(data.readDouble(), data.readDouble(), data.readDouble(), data.readDouble(),
                data.readDouble(), data.readDouble(), data.readDouble(), data.readDouble(), data.readDouble());
    }

    public static void handle(SpawnMagicMessage msg, Supplier<NetworkEvent.Context> ctx) {
        Minecraft.getInstance().enqueue(() -> Minecraft.getInstance().world
                .addParticle(new AltarParticleData(msg.targetX, msg.targetY, msg.targetZ), msg.x, msg.y, msg.z,
                        msg.velX, msg.velY, msg.velZ));
        ctx.get().setPacketHandled(true);
    }

}
