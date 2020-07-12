package deerangle.magicmod.spells;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpellRegistry {

    public static Spell ELEKTRO;

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> event) {
        ELEKTRO = new ElektroSpell(1, 20).setRegistryName("electro");
        event.getRegistry().registerAll(ELEKTRO);
    }

}
