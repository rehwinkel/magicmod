package deerangle.magicmod.item;

import deerangle.magicmod.spells.Spell;
import deerangle.magicmod.spells.SpellRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WandItem extends Item {

    public WandItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Spell spell = SpellRegistry.ELEKTRO;//this.getCurrentSpell(playerIn.getHeldItem(handIn));
        System.out.println("cast" + spell);
        if (spell != null && spell.cast(playerIn)) {
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        } else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return !this.getSpells(stack).isEmpty();
    }

    @Nullable
    private Spell getCurrentSpell(ItemStack stack) {
        List<Spell> spells = this.getSpells(stack);
        int index = this.getCurrentSpellIndex(stack);
        if (spells.size() > index) {
            return spells.get(index);
        }
        return null;
    }

    private int getCurrentSpellIndex(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("CurrentSpell")) {
            return stack.getTag().getByte("CurrentSpell");
        } else {
            return 0;
        }
    }

    private List<Spell> getSpells(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Spells")) {
            return stack.getTag().getList("Spells", 8).stream().map(str -> Spell.read(str))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
