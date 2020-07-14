package deerangle.magicmod.item;

import deerangle.magicmod.spells.Spell;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WandItem extends Item {

    public WandItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Spell spell = this.getCurrentSpell(playerIn.getHeldItem(handIn));
        if (spell != null && spell.cast(playerIn)) {
            playerIn.getCooldownTracker().setCooldown(this, spell.getCooldown());
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        } else {
            return ActionResult.resultPass(playerIn.getHeldItem(handIn));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return !this.readSpells(stack).isEmpty();
    }

    @Nullable
    private Spell getCurrentSpell(ItemStack stack) {
        List<Spell> spells = this.readSpells(stack);
        int index = this.readCurrentSpell(stack);
        if (spells.size() > index) {
            return spells.get(index);
        }
        return null;
    }

    private int readCurrentSpell(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("CurrentSpell")) {
            return stack.getTag().getByte("CurrentSpell");
        } else {
            return 0;
        }
    }

    public List<Spell> readSpells(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Spells")) {
            return stack.getTag().getList("Spells", 8).stream().map(str -> Spell.read(str))
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private void writeSpells(ItemStack stack, List<Spell> spells) {
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        ListNBT list = new ListNBT();
        for (Spell s : spells) {
            String loc = s == null ? "" : s.getRegistryName().toString();
            list.add(StringNBT.valueOf(loc));
        }
        stack.getTag().put("Spells", list);
    }

    private List<Spell> makeSpellList(int length) {
        List<Spell> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(null);
        }
        return list;
    }

    public void putSpell(ItemStack stack, int place, int length, Spell spell) {
        List<Spell> spells = readSpells(stack);
        if (spells.isEmpty()) {
            spells = makeSpellList(length);
        }
        spells.set(place, spell);
        writeSpells(stack, spells);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        List<Spell> spells = this.readSpells(stack);
        for (int i = 0; i < spells.size(); i++) {
            Spell s = spells.get(i);
            tooltip.add(new StringTextComponent((i + 1) + ": ").func_230529_a_(
                    s == null ? new TranslationTextComponent("info.magicmod.no_spell") : s.getTextComponent())
                    .func_240699_a_(TextFormatting.GRAY));
        }
    }
}
