package deerangle.magicmod.particle;

import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {

    public static ParticleType<AltarParticleData> MAGIC;

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        MAGIC = register(false, AltarParticleData.DESERIALIZER, (inst) -> AltarParticleData.CODEC, "magic");
        event.getRegistry().registerAll(MAGIC);
    }

    private static <T extends IParticleData> ParticleType<T> register(boolean alwaysShow, IParticleData.IDeserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codecSupplier, String name) {
        ParticleType<T> type = new ParticleType<T>(alwaysShow, deserializer) {
            @Override
            public Codec<T> func_230522_e_() {
                return codecSupplier.apply(this);
            }
        };
        type.setRegistryName(name);
        return type;
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(MAGIC, AltarParticle.Factory::new);
    }

}
