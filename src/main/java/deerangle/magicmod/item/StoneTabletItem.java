package deerangle.magicmod.item;

import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.spells.Spell;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class StoneTabletItem extends BlockItem {

    public StoneTabletItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        Spell spell = this.readSpell(stack);
        tooltip.add((spell == null ? new TranslationTextComponent("info.magicmod.no_spell") : spell.getTextComponent())
                .func_240699_a_(TextFormatting.GRAY));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (this.isInGroup(group)) {
            for (ResourceLocation loc : GameRegistry.findRegistry(Spell.class).getKeys()) {
                CompoundNBT tag = new CompoundNBT();
                tag.putString("Spell", loc.toString());
                ItemStack stack = new ItemStack(BlockRegistry.ENCHANTED_STONE_TABLET);
                stack.setTag(tag);
                items.add(stack);
            }
        }
    }

    public Spell readSpell(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("Spell")) {
            return Spell.read(stack.getTag().getString("Spell"));
        } else {
            return null;
        }
    }

}
