package deerangle.magicmod.recipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistry {

    public static final IRecipeType<AltarRitualRecipe> ALTAR_RITUAL_RECIPE = new IRecipeType<AltarRitualRecipe>() {
        @Override
        public String toString() {
            return new ResourceLocation("magicmod", "altar_ritual").toString();
        }
    };

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(ALTAR_RITUAL_RECIPE.toString()),
                ALTAR_RITUAL_RECIPE);
        event.getRegistry().registerAll(AltarRitualRecipe.SERIALIZER);
    }

}
