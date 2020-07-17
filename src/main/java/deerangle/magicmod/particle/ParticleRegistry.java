package deerangle.magicmod.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {

    public static BasicParticleType MAGIC;

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        MAGIC = register(new BasicParticleType(false), "magic");
        event.getRegistry().registerAll(MAGIC);
    }

    private static <T extends ParticleType<?>> T register(T type, String name) {
        type.setRegistryName(name);
        return type;
    }

    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(MAGIC, AltarParticle.Factory::new);
    }

}
