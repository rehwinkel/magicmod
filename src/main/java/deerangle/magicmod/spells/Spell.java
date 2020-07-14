package deerangle.magicmod.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Spell extends ForgeRegistryEntry<Spell> {

    private final int cooldown;

    public Spell(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getXOffset() {
        return 0;
    }

    public int getYOffset() {
        return 0;
    }

    public boolean cast(PlayerEntity player) {
        return false;
    }

    public int getCooldown() {
        return cooldown;
    }

    public INBT write() {
        return StringNBT.valueOf(this.getRegistryName().toString());
    }

    public static Spell read(INBT nbt) {
        String str = nbt.getString();
        if (str.isEmpty()) {
            return null;
        }
        return GameRegistry.findRegistry(Spell.class).getValue(new ResourceLocation(nbt.getString()));
    }

    public ITextComponent getTextComponent() {
        return new TranslationTextComponent(
                "spell." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath());
    }

}
