package deerangle.magicmod.main;

import deerangle.magicmod.item.ItemRegistry;
import deerangle.magicmod.world.WorldGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
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

    public MagicMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        for (Biome b : Biome.BIOMES) {
            WorldGen.addAmethystOre(b);
            WorldGen.addSiltStone(b);
        }
    }

}
