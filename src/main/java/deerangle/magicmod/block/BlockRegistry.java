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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        WAND_TABLE = new WandTableBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLUE))
                .setRegistryName("wand_table");
        event.getRegistry().registerAll(WAND_TABLE);
    }

    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        BlockItem WAND_TABLE_ITEM = new BlockItem(WAND_TABLE, new Item.Properties().group(MagicMod.tab));
        WAND_TABLE_ITEM.setRegistryName(WAND_TABLE.getRegistryName());
        event.getRegistry().registerAll(WAND_TABLE_ITEM);
    }

}