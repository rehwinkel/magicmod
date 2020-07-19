package deerangle.magicmod.spells;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nullable;

public class SelfSpellTarget implements ISpellTarget {

    private final Entity self;

    public SelfSpellTarget(PlayerEntity playerIn) {
        this.self = playerIn;
    }

    @Nullable
    @Override
    public Entity getHitEntity() {
        return self;
    }

    @Override
    public Vector3d getVector() {
        return self.getPositionVec();
    }

    @Override
    public boolean hit() {
        return true;
    }
}
