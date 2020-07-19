package deerangle.magicmod.spells;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ElectroSpell extends Spell {

    public ElectroSpell(int cooldown) {
        super(cooldown);
    }

    public int getXOffset() {
        return 0;
    }

    public int getYOffset() {
        return 0;
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, ISpellTarget target) {
        if (target.hit()) {
            LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            lightning.func_233576_c_(target.getVector());
            world.addEntity(lightning);
            return true;
        }
        return false;
    }

    @Override
    protected ISpellTarget getTarget(World worldIn, PlayerEntity playerIn) {
        return new AimBlockSpellTarget(worldIn, playerIn, 20.0);
    }
}
