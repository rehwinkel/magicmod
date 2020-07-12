package deerangle.magicmod.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Spell extends ForgeRegistryEntry<Spell> {

    private final int level;
    private final int cooldown;

    public Spell(int level, int cooldown) {
        this.level = level;
        this.cooldown = cooldown;
    }

    public ResourceLocation getIconLocation() {
        ResourceLocation base = this.getRegistryName();
        return new ResourceLocation(base.getNamespace(), "textures/spells/" + base.getPath() + ".png");
    }

    public boolean cast(PlayerEntity player) {
        return false;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getLevel() {
        return level;
    }

    public INBT write() {
        return StringNBT.valueOf(this.getRegistryName().toString());
    }

    public static Spell read(INBT nbt) {
        return GameRegistry.findRegistry(Spell.class).getValue(new ResourceLocation(nbt.getString()));
    }
}
