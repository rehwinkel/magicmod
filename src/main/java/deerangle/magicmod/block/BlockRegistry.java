package deerangle.magicmod.block;

import deerangle.magicmod.item.StoneTabletItem;
import deerangle.magicmod.main.MagicMod;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
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
    public static Block AMETHYST_BLOCK;
    public static Block AMETHYST_ORE;
    public static Block AMETHYST_SILT_ORE;
    public static Block SILT_STONE;
    public static Block SILT_STONE_BRICKS;
    public static Block SILT_STONE_SLAB;
    public static Block SILT_STONE_BRICK_SLAB;
    public static Block SILT_STONE_STAIRS;
    public static Block SILT_STONE_BRICK_STAIRS;
    public static Block SILT_STONE_WALL;
    public static Block SILT_STONE_BRICK_WALL;
    public static Block STONE_TABLET;
    public static Block ENCHANTED_STONE_TABLET;
    public static Block PEDESTAL;
    public static Block ALTAR;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        Block.Properties baseSiltProperties = Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA);
        WAND_TABLE = new WandTableBlock(
                Block.Properties.create(Material.ROCK, MaterialColor.BLUE).hardnessAndResistance(5.0F, 1200.0F))
                .setRegistryName("wand_table");
        AMETHYST_ORE = new Block(
                Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2))
                .setRegistryName("amethyst_ore");
        AMETHYST_BLOCK = new Block(Block.Properties.create(Material.IRON, MaterialColor.PURPLE_TERRACOTTA)
                .hardnessAndResistance(5.0F, 6.0F)).setRegistryName("amethyst_block");
        AMETHYST_SILT_ORE = new Block(baseSiltProperties.hardnessAndResistance(3.0F, 3.0F).harvestLevel(2))
                .setRegistryName("amethyst_silt_ore");
        SILT_STONE = new Block(baseSiltProperties.hardnessAndResistance(1.5F, 6.0F)).setRegistryName("silt_stone");
        PEDESTAL = new PedestalBlock(Block.Properties.from(SILT_STONE)).setRegistryName("pedestal");
        ALTAR = new AltarBlock(Block.Properties.from(SILT_STONE)).setRegistryName("altar");
        SILT_STONE_BRICKS = new Block(Block.Properties.from(SILT_STONE)).setRegistryName("silt_stone_bricks");
        SILT_STONE_SLAB = new SlabBlock(Block.Properties.from(SILT_STONE)).setRegistryName("silt_stone_slab");
        SILT_STONE_BRICK_SLAB = new SlabBlock(Block.Properties.from(SILT_STONE))
                .setRegistryName("silt_stone_brick_slab");
        SILT_STONE_STAIRS = new StairsBlock(() -> SILT_STONE.getDefaultState(), Block.Properties.from(SILT_STONE))
                .setRegistryName("silt_stone_stairs");
        SILT_STONE_BRICK_STAIRS = new StairsBlock(() -> SILT_STONE_BRICKS.getDefaultState(),
                Block.Properties.from(SILT_STONE)).setRegistryName("silt_stone_brick_stairs");
        SILT_STONE_WALL = new WallBlock(Block.Properties.from(SILT_STONE)).setRegistryName("silt_stone_wall");
        SILT_STONE_BRICK_WALL = new WallBlock(Block.Properties.from(SILT_STONE))
                .setRegistryName("silt_stone_brick_wall");
        STONE_TABLET = new StoneTabletBlock(Block.Properties.from(SILT_STONE), false).setRegistryName("stone_tablet");
        ENCHANTED_STONE_TABLET = new StoneTabletBlock(Block.Properties.from(SILT_STONE), true)
                .setRegistryName("enchanted_stone_tablet");
        event.getRegistry().registerAll(WAND_TABLE, AMETHYST_ORE, AMETHYST_SILT_ORE, SILT_STONE, SILT_STONE_BRICKS,
                SILT_STONE_SLAB, SILT_STONE_STAIRS, SILT_STONE_WALL, SILT_STONE_BRICK_SLAB, SILT_STONE_BRICK_STAIRS,
                SILT_STONE_BRICK_WALL, AMETHYST_BLOCK, STONE_TABLET, ENCHANTED_STONE_TABLET, PEDESTAL, ALTAR);
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockItem WAND_TABLE_ITEM = new BlockItem(WAND_TABLE, new Item.Properties().group(MagicMod.tab));
        WAND_TABLE_ITEM.setRegistryName(WAND_TABLE.getRegistryName());
        BlockItem AMETHYST_ORE_ITEM = new BlockItem(AMETHYST_ORE, new Item.Properties().group(MagicMod.tab));
        AMETHYST_ORE_ITEM.setRegistryName(AMETHYST_ORE.getRegistryName());
        BlockItem PEDESTAL_ITEM = new BlockItem(PEDESTAL, new Item.Properties().group(MagicMod.tab));
        PEDESTAL_ITEM.setRegistryName(PEDESTAL.getRegistryName());
        BlockItem ALTAR_ITEM = new BlockItem(ALTAR, new Item.Properties().group(MagicMod.tab));
        ALTAR_ITEM.setRegistryName(ALTAR.getRegistryName());
        BlockItem AMETHYST_BLOCK_ITEM = new BlockItem(AMETHYST_BLOCK, new Item.Properties().group(MagicMod.tab));
        AMETHYST_BLOCK_ITEM.setRegistryName(AMETHYST_BLOCK.getRegistryName());
        BlockItem AMETHYST_SILT_ORE_ITEM = new BlockItem(AMETHYST_SILT_ORE, new Item.Properties().group(MagicMod.tab));
        AMETHYST_SILT_ORE_ITEM.setRegistryName(AMETHYST_SILT_ORE.getRegistryName());
        BlockItem SILT_STONE_ITEM = new BlockItem(SILT_STONE, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_ITEM.setRegistryName(SILT_STONE.getRegistryName());
        BlockItem SILT_STONE_BRICKS_ITEM = new BlockItem(SILT_STONE_BRICKS, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_BRICKS_ITEM.setRegistryName(SILT_STONE_BRICKS.getRegistryName());
        BlockItem SILT_STONE_SLAB_ITEM = new BlockItem(SILT_STONE_SLAB, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_SLAB_ITEM.setRegistryName(SILT_STONE_SLAB.getRegistryName());
        BlockItem SILT_STONE_BRICK_SLAB_ITEM = new BlockItem(SILT_STONE_BRICK_SLAB,
                new Item.Properties().group(MagicMod.tab));
        SILT_STONE_BRICK_SLAB_ITEM.setRegistryName(SILT_STONE_BRICK_SLAB.getRegistryName());
        BlockItem SILT_STONE_STAIRS_ITEM = new BlockItem(SILT_STONE_STAIRS, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_STAIRS_ITEM.setRegistryName(SILT_STONE_STAIRS.getRegistryName());
        BlockItem SILT_STONE_BRICK_STAIRS_ITEM = new BlockItem(SILT_STONE_BRICK_STAIRS,
                new Item.Properties().group(MagicMod.tab));
        SILT_STONE_BRICK_STAIRS_ITEM.setRegistryName(SILT_STONE_BRICK_STAIRS.getRegistryName());
        BlockItem SILT_STONE_WALL_ITEM = new BlockItem(SILT_STONE_WALL, new Item.Properties().group(MagicMod.tab));
        SILT_STONE_WALL_ITEM.setRegistryName(SILT_STONE_WALL.getRegistryName());
        BlockItem SILT_STONE_BRICK_WALL_ITEM = new BlockItem(SILT_STONE_BRICK_WALL,
                new Item.Properties().group(MagicMod.tab));
        SILT_STONE_BRICK_WALL_ITEM.setRegistryName(SILT_STONE_BRICK_WALL.getRegistryName());
        BlockItem STONE_TABLET_ITEM = new BlockItem(STONE_TABLET, new Item.Properties().group(MagicMod.tab));
        STONE_TABLET_ITEM.setRegistryName(STONE_TABLET.getRegistryName());
        BlockItem ENCHANTED_STONE_TABLET_ITEM = new StoneTabletItem(ENCHANTED_STONE_TABLET,
                new Item.Properties().group(MagicMod.tab).maxStackSize(1));
        ENCHANTED_STONE_TABLET_ITEM.setRegistryName(ENCHANTED_STONE_TABLET.getRegistryName());
        event.getRegistry().registerAll(WAND_TABLE_ITEM, AMETHYST_SILT_ORE_ITEM, AMETHYST_ORE_ITEM, SILT_STONE_ITEM,
                SILT_STONE_BRICKS_ITEM, SILT_STONE_SLAB_ITEM, SILT_STONE_BRICK_SLAB_ITEM, SILT_STONE_STAIRS_ITEM,
                SILT_STONE_BRICK_STAIRS_ITEM, SILT_STONE_WALL_ITEM, SILT_STONE_BRICK_WALL_ITEM, AMETHYST_BLOCK_ITEM,
                STONE_TABLET_ITEM, ENCHANTED_STONE_TABLET_ITEM, PEDESTAL_ITEM, ALTAR_ITEM);
    }

}