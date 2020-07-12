package deerangle.magicmod.block.entity;

import deerangle.magicmod.block.BlockRegistry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = Bus.MOD)
public class TileEntityRegistry {

    public static TileEntityType<PedestalTileEntity> PEDESTAL;
    public static TileEntityType<WandTableTileEntity> WAND_TABLE;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        WAND_TABLE = register(TileEntityType.Builder.create(WandTableTileEntity::new, BlockRegistry.WAND_TABLE),
                "wand_table");
        PEDESTAL = register(TileEntityType.Builder.create(PedestalTileEntity::new, BlockRegistry.PEDESTAL), "pedestal");
        event.getRegistry().registerAll(WAND_TABLE, PEDESTAL);
    }

    private static <T extends TileEntity> TileEntityType<T> register(TileEntityType.Builder<T> builder, String name) {
        TileEntityType<T> tet = builder.build(null);
        tet.setRegistryName(name);
        return tet;
    }

}