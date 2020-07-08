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
    public static Block SILT_STONE;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        WAND_TABLE = new WandTableBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE))
                .setRegistryName("wand_table");
        SILT_STONE = new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA))
                .setRegistryName("silt_stone");
        event.getRegistry().registerAll(WAND_TABLE, SILT_STONE);
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockItem WAND_TABLE_ITEM = new BlockItem(WAND_TABLE, new Item.Properties().group(MagicMod.tab));
        WAND_TABLE_ITEM.setRegistryName(WAND_TABLE.getRegistryName());
        BlockItem SILT_STONE_ITEM = new BlockItem(SILT_STONE, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_ITEM.setRegistryName(SILT_STONE.getRegistryName());
        event.getRegistry().registerAll(WAND_TABLE_ITEM, SILT_STONE_ITEM);
    }

}