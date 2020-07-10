package deerangle.magicmod.proxy;

import deerangle.magicmod.block.entity.TileEntityRegistry;
import deerangle.magicmod.block.entity.render.WandTableTileEntityRenderer;
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
        ClientRegistry.bindTileEntityRenderer(TileEntityRegistry.WAND_TABLE, WandTableTileEntityRenderer::new);
        ScreenManager.registerFactory(ContainerRegistry.WAND_TABLE, WandTableGui::new);
    }

}