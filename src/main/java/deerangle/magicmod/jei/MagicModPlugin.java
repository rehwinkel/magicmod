package deerangle.magicmod.jei;

import deerangle.magicmod.block.BlockRegistry;
import deerangle.magicmod.recipe.RecipeRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@JeiPlugin
public class MagicModPlugin implements IModPlugin {

    private AltarRitualRecipeCategory ritual;
    public static final ResourceLocation ritualUid = new ResourceLocation("magicmod", "altar_ritual");

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockRegistry.ALTAR), ritualUid);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        ritual = new AltarRitualRecipeCategory(registration.getJeiHelpers().getGuiHelper());
        registration.addRecipeCategories(ritual);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        World world = Minecraft.getInstance().world;
        registration.addRecipes(world.getRecipeManager().func_241447_a_(RecipeRegistry.ALTAR_RITUAL_RECIPE), ritualUid);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation("magicmod", "jei");
    }

}
