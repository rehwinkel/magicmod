package deerangle.magicmod.proxy;

import deerangle.magicmod.block.entity.TileEntityRegistry;
import deerangle.magicmod.block.entity.render.ItemStandTileEntityRenderer;
import deerangle.magicmod.container.ContainerRegistry;
import deerangle.magicmod.container.WandTableGui;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy implements Proxy {

    @Override
    public void init() {

    }

    @Override
    public void setup() {

    }

    @Override
    public void clientSetup() {
        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.WAND_TABLE,
                dispatcher -> new ItemStandTileEntityRenderer(dispatcher, 27.0 / 32.0, 0.7F, true));
        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.PEDESTAL,
                dispatcher -> new ItemStandTileEntityRenderer(dispatcher, 15.0 / 16.0, 0.5F, false));
        ScreenManager.registerFactory(ContainerRegistry.WAND_TABLE, WandTableGui::new);
    }

}