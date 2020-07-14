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
        public static Item MAGIC_STICK;
        public static Item BASIC_MAGIC_CRYSTAL;
        public static Item ADVANCED_MAGIC_CRYSTAL;
        public static Item MASTER_MAGIC_CRYSTAL;
        public static Item ELECTRO_CRYSTAL;
        public static Item NATURE_CRYSTAL;
        public static Item FIRE_CRYSTAL;
        public static Item WATER_CRYSTAL;
        public static Item WIND_CRYSTAL;
        public static Item EARTH_CRYSTAL;

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
                BASIC_WAND = new WandItem(new Item.Properties().maxStackSize(1).group(MagicMod.tab))
                                .setRegistryName("basic_wand");
                ADVANCED_WAND = new WandItem(
                                new Item.Properties().maxStackSize(1).group(MagicMod.tab).rarity(Rarity.RARE))
                                                .setRegistryName("advanced_wand");
                MASTER_WAND = new WandItem(
                                new Item.Properties().maxStackSize(1).group(MagicMod.tab).rarity(Rarity.EPIC))
                                                .setRegistryName("master_wand");
                AMETHYST = new Item(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("amethyst");
                MAGIC_STICK = new Item(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("magic_stick");
                BASIC_MAGIC_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("basic_magic_crystal");
                ADVANCED_MAGIC_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("advanced_magic_crystal");
                MASTER_MAGIC_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("master_magic_crystal");
                ELECTRO_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("electro_crystal");
                NATURE_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("nature_crystal");
                FIRE_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("fire_crystal");
                WATER_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("water_crystal");
                WIND_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("wind_crystal");
                EARTH_CRYSTAL = new WandItem(new Item.Properties().maxStackSize(64).group(MagicMod.tab))
                                .setRegistryName("earth_crystal");
                event.getRegistry().registerAll(BASIC_WAND, ADVANCED_WAND, MASTER_WAND, AMETHYST, MAGIC_STICK,
                                BASIC_MAGIC_CRYSTAL, ADVANCED_MAGIC_CRYSTAL, MASTER_MAGIC_CRYSTAL, ELECTRO_CRYSTAL,
                                NATURE_CRYSTAL, FIRE_CRYSTAL, WATER_CRYSTAL, WIND_CRYSTAL, WIND_CRYSTAL, EARTH_CRYSTAL);
        }

}
