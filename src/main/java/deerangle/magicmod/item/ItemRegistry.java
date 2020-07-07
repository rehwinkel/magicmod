package deerangle.magicmod.item;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD)
public class ItemRegistry {

    public static Item WOODEN_WAND;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        WOODEN_WAND = new Item(new Item.Properties().maxStackSize(1)).setRegistryName("wooden_wand");

        event.getRegistry().registerAll(WOODEN_WAND);
    }

}