package deerangle.magicmod.jei;

import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.recipe.AltarRitualRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.block.Blocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class AltarRitualRecipeCategory implements IRecipeCategory<AltarRitualRecipe> {

    private final String localizedName;
    private final IDrawable background;
    private final IDrawable icon;

    public AltarRitualRecipeCategory(IGuiHelper guiHelper) {
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(BlockRegistry.ALTAR));
        this.background = guiHelper.createDrawable(new ResourceLocation("magicmod", "textures/gui/altar_jei.png"), 0, 0, 130, 68);
        this.localizedName = I18n.format("block.magicmod.altar");
    }

    @Override
    public ResourceLocation getUid() {
        return MagicModPlugin.ritualUid;
    }

    @Override
    public Class<? extends AltarRitualRecipe> getRecipeClass() {
        return AltarRitualRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(AltarRitualRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, AltarRitualRecipe altarRitualRecipe, IIngredients iIngredients) {
        IGuiItemStackGroup stacks = layout.getItemStacks();
        stacks.init(0, true, 25, 25);
        stacks.init(1, true, 48, 2);
        stacks.init(2, true, 48, 48);
        stacks.init(3, true, 2, 48);
        stacks.init(4, true, 2, 2);
        stacks.init(5, false, 108, 25);
        stacks.set(iIngredients);
    }

}
