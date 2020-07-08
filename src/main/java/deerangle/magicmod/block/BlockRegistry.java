package deerangle.magicmod.block;

import deerangle.magicmod.main.MagicMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD)
public class BlockRegistry {

    public static Block WAND_TABLE;
    public static Block AMETHYST_ORE_BLOCK;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        WAND_TABLE = new WandTableBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE))
                .setRegistryName("wand_table");
        AMETHYST_ORE_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE))
                .setRegistryName("amethyst_ore");
        event.getRegistry().registerAll(WAND_TABLE, AMETHYST_ORE_BLOCK);
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockItem WAND_TABLE_ITEM = new BlockItem(WAND_TABLE, new Item.Properties().group(MagicMod.tab));
        WAND_TABLE_ITEM.setRegistryName(WAND_TABLE.getRegistryName());
        BlockItem AMETHYST_ORE_ITEM = new BlockItem(AMETHYST_ORE_BLOCK, new Item.Properties().group(MagicMod.tab));
        AMETHYST_ORE_ITEM.setRegistryName(AMETHYST_ORE_BLOCK.getRegistryName());
        event.getRegistry().registerAll(WAND_TABLE_ITEM, AMETHYST_ORE_ITEM);
    }

}