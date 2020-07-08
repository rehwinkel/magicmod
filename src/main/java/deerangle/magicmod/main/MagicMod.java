package deerangle.magicmod.main;

import deerangle.magicmod.item.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod("magicmod")
public class MagicMod {
    
    public static final ItemGroup tab = new ItemGroup("magicmod") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.MASTER_WAND);
        }
        
    };
    
    public MagicMod() {

    }

}
