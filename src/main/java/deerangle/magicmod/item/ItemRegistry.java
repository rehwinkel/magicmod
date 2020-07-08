package deerangle.magicmod.item;

import deerangle.magicmod.main.MagicMod;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD)
public class ItemRegistry {

    public static Item BASIC_WAND;
    public static Item ADVANCED_WAND;
    public static Item MASTER_WAND;
    public static Item AMETHYST;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        BASIC_WAND = new Item(new Item.Properties().maxStackSize(1).group(MagicMod.tab)).setRegistryName("basic_wand");
        ADVANCED_WAND = new Item(new Item.Properties().maxStackSize(1).group(MagicMod.tab).rarity(Rarity.RARE)).setRegistryName("advanced_wand");
        MASTER_WAND = new Item(new Item.Properties().maxStackSize(1).group(MagicMod.tab).rarity(Rarity.EPIC)).setRegistryName("master_wand");
        AMETHYST = new Item(new Item.Properties().maxStackSize(64).group(MagicMod.tab)).setRegistryName("amethyst");
        event.getRegistry().registerAll(BASIC_WAND, ADVANCED_WAND, MASTER_WAND, AMETHYST);
    }

}
