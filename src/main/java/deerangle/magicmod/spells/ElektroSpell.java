package deerangle.magicmod.spells;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ElektroSpell extends Spell {

    public ElektroSpell(int level, int cooldown) {
        super(level, cooldown);
    }

    @Override
    public boolean cast(PlayerEntity player) {
        System.out.println("NICE");
        World world = player.getEntityWorld();
        LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        lightning.func_233576_c_(player.getPositionVec());
        world.addEntity(lightning);
        return true;
    }
}
