package deerangle.magicmod.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoundRegistry {

    public static SoundEvent RITUAL;

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        RITUAL = new SoundEvent(new ResourceLocation("magicmod", "block.altar.ritual"))
                .setRegistryName("block.altar.ritual");
        event.getRegistry().registerAll(RITUAL);
    }

}
