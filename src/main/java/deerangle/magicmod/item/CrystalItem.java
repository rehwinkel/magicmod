package deerangle.magicmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrystalItem extends Item {

    public CrystalItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

}
