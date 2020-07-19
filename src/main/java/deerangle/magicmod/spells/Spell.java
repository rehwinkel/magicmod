package deerangle.magicmod.spells;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Spell extends ForgeRegistryEntry<Spell> {

    private final int cooldown;

    public Spell(int cooldown) {
        this.cooldown = cooldown;
    }

    public static Spell read(String path) {
        if (path.isEmpty()) {
            return null;
        }
        return GameRegistry.findRegistry(Spell.class).getValue(new ResourceLocation(path));
    }

    public int getXOffset() {
        return 0;
    }

    public int getYOffset() {
        return 0;
    }

    public boolean cast(World world, PlayerEntity caster, ISpellTarget target) {
        return false;
    }

    public int getCooldown() {
        return cooldown;
    }

    public INBT write() {
        return StringNBT.valueOf(this.getRegistryName().toString());
    }

    public IFormattableTextComponent getTextComponent() {
        return new TranslationTextComponent(
                "spell." + this.getRegistryName().getNamespace() + "." + this.getRegistryName().getPath());
    }

    public boolean prepare_and_cast(World worldIn, PlayerEntity playerIn) {
        return this.cast(worldIn, playerIn, this.getTarget(worldIn, playerIn));
    }

    protected ISpellTarget getTarget(World worldIn, PlayerEntity playerIn) {
        return new SelfSpellTarget(playerIn);
    }
}
