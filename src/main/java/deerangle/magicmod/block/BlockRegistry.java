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
    public static Block AMETHYST_ORE;
    public static Block AMETHYST_SILT_ORE;
    public static Block SILT_STONE;
    public static Block SILT_STONE_BRICKS;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        WAND_TABLE = new WandTableBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE))
                .setRegistryName("wand_table");
        AMETHYST_ORE = new Block(Block.Properties.create(Material.ROCK)).setRegistryName("amethyst_ore");
        AMETHYST_SILT_ORE = new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA))
                .setRegistryName("amethyst_silt_ore");
        SILT_STONE = new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA))
                .setRegistryName("silt_stone");
        SILT_STONE_BRICKS = new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA))
                .setRegistryName("silt_stone_bricks");
        event.getRegistry().registerAll(WAND_TABLE, AMETHYST_ORE, AMETHYST_SILT_ORE, SILT_STONE, SILT_STONE_BRICKS);
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockItem WAND_TABLE_ITEM = new BlockItem(WAND_TABLE, new Item.Properties().group(MagicMod.tab));
        WAND_TABLE_ITEM.setRegistryName(WAND_TABLE.getRegistryName());
        BlockItem AMETHYST_ORE_ITEM = new BlockItem(AMETHYST_ORE, new Item.Properties().group(MagicMod.tab));
        AMETHYST_ORE_ITEM.setRegistryName(AMETHYST_ORE.getRegistryName());
        BlockItem AMETHYST_SILT_ORE_ITEM = new BlockItem(AMETHYST_SILT_ORE, new Item.Properties().group(MagicMod.tab));
        AMETHYST_SILT_ORE_ITEM.setRegistryName(AMETHYST_SILT_ORE.getRegistryName());
        BlockItem SILT_STONE_ITEM = new BlockItem(SILT_STONE, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_ITEM.setRegistryName(SILT_STONE.getRegistryName());
        BlockItem SILT_STONE_BRICKS_ITEM = new BlockItem(SILT_STONE_BRICKS, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_BRICKS_ITEM.setRegistryName(SILT_STONE_BRICKS.getRegistryName());
        event.getRegistry().registerAll(WAND_TABLE_ITEM, AMETHYST_SILT_ORE_ITEM, AMETHYST_ORE_ITEM, SILT_STONE_ITEM,
                SILT_STONE_BRICKS_ITEM);
    }

}