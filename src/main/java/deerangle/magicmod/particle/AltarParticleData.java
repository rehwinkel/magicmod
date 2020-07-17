package deerangle.magicmod.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

public class AltarParticleData implements IParticleData {

    public static final IParticleData.IDeserializer<AltarParticleData> DESERIALIZER = new IDeserializer<AltarParticleData>() {
        @Override
        public AltarParticleData deserialize(ParticleType<AltarParticleData> particleTypeIn, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            float x = (float) reader.readDouble();
            reader.expect(' ');
            float y = (float) reader.readDouble();
            reader.expect(' ');
            float z = (float) reader.readDouble();
            return new AltarParticleData(x, y, z);
        }

        @Override
        public AltarParticleData read(ParticleType<AltarParticleData> particleTypeIn, PacketBuffer buffer) {
            return new AltarParticleData(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
        }
    };
    public static final Codec<AltarParticleData> CODEC = RecordCodecBuilder.create(c -> c
            .group(Codec.DOUBLE.fieldOf("x").forGetter(data -> data.targetX),
                    Codec.DOUBLE.fieldOf("y").forGetter(data -> data.targetY),
                    Codec.DOUBLE.fieldOf("z").forGetter(data -> data.targetZ)).apply(c, AltarParticleData::new));
    private final double targetX, targetY, targetZ;

    public AltarParticleData(double targetX, double targetY, double targetZ) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
    }

    @Override
    public ParticleType<AltarParticleData> getType() {
        return ParticleRegistry.MAGIC;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeDouble(targetX);
        buffer.writeDouble(targetY);
        buffer.writeDouble(targetZ);
    }

    @Override
    public String getParameters() {
        return String
                .format(Locale.ROOT, "%s %.2f %.2f %.2f", Registry.PARTICLE_TYPE.getKey(this.getType()), this.targetX,
                        this.targetY, this.targetZ);
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public double getTargetZ() {
        return targetZ;
    }

}
