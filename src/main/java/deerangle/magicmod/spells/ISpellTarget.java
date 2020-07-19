package deerangle.magicmod.spells;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public interface ISpellTarget {

    @Nullable
    Entity getHitEntity();

    Vector3d getVector();

    boolean hit();

}
