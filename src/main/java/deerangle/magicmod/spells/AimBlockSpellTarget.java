package deerangle.magicmod.spells;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AimBlockSpellTarget implements ISpellTarget {

    private final BlockRayTraceResult result;

    public AimBlockSpellTarget(World world, Entity player, double range) {
        result = world.rayTraceBlocks(
                new RayTraceContext(player.getEyePosition(0), player.getEyePosition(0).add(player.getLookVec().mul(range, range, range)),
                        RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null));
    }

    @Nullable
    @Override
    public Entity getHitEntity() {
        return null;
    }

    @Override
    public Vector3d getVector() {
        return result.getHitVec();
    }

    @Override
    public boolean hit() {
        return result.getType() != RayTraceResult.Type.MISS;
    }
}
