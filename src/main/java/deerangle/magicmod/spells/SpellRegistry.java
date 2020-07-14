package deerangle.magicmod.spells;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpellRegistry {

    public static Spell ELECTRO;

    @SubscribeEvent
    public static void registerSpells(RegistryEvent.Register<Spell> event) {
        ELECTRO = new ElectroSpell(20).setRegistryName("electro");
        event.getRegistry().registerAll(ELECTRO);
    }

}
