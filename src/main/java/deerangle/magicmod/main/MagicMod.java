package deerangle.magicmod.main;

import deerangle.magicmod.item.ItemRegistry;
import deerangle.magicmod.proxy.ClientProxy;
import deerangle.magicmod.proxy.ServerProxy;
import deerangle.magicmod.proxy.Proxy;
import deerangle.magicmod.world.WorldGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("magicmod")
public class MagicMod {

    public static final ItemGroup tab = new ItemGroup("magicmod") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.MASTER_WAND);
        }

    };

    public static Proxy proxy = (Proxy) DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public MagicMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        proxy.init();
    }

    private void setup(FMLCommonSetupEvent event) {
        for (Biome b : Biome.BIOMES) {
            WorldGen.addAmethystOre(b);
            WorldGen.addSiltStone(b);
        }
        proxy.setup();
    }

    private void clientSetup(FMLClientSetupEvent event) {
        proxy.clientSetup();
    }

}
