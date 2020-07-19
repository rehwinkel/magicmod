package deerangle.magicmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.List;

public class AltarRitualRecipe implements IRecipe<IInventory> {

    public static final IRecipeSerializer<AltarRitualRecipe> SERIALIZER = new Serializer();

    private final ResourceLocation id;
    private final Activation activation;
    private final Ingredient centerInput;
    private final List<Ingredient> otherInputs;
    private final ItemStack output;

    public AltarRitualRecipe(ResourceLocation id, Activation activation, Ingredient centerInput, List<Ingredient> otherInputs, ItemStack output) {
        this.id = id;
        this.activation = activation;
        this.centerInput = centerInput;
        assert otherInputs.size() == 4;
        this.otherInputs = otherInputs;
        this.output = output;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        boolean centerOk = centerInput.test(inv.getStackInSlot(0));
        boolean otherOk1 = otherInputs.stream().anyMatch(x -> x.test(inv.getStackInSlot(1)));
        boolean otherOk2 = otherInputs.stream().anyMatch(x -> x.test(inv.getStackInSlot(2)));
        boolean otherOk3 = otherInputs.stream().anyMatch(x -> x.test(inv.getStackInSlot(3)));
        boolean otherOk4 = otherInputs.stream().anyMatch(x -> x.test(inv.getStackInSlot(4)));
        return centerOk && otherOk1 && otherOk2 && otherOk3 && otherOk4;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.output.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeRegistry.ALTAR_RITUAL_RECIPE;
    }

    private enum Activation {
        CLICK("click"),
        ;
        String name;

        Activation(String name) {
            this.name = name;
        }

        @Nullable
        public static Activation from(String name) {
            for (Activation a : Activation.values()) {
                if (a.name.equals(name)) {
                    return a;
                }
            }
            return null;
        }
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(centerInput);
        ingredients.addAll(otherInputs);
        return ingredients;
    }

    private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AltarRitualRecipe> {

        Serializer() {
            this.setRegistryName(new ResourceLocation("magicmod", "altar_ritual"));
        }

        @Override
        public AltarRitualRecipe read(ResourceLocation recipeId, JsonObject json) {
            NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
            Activation act = Activation.from(JSONUtils.getString(json, "activation"));
            Ingredient center = Ingredient.deserialize(json.get("center"));
            assert !center.hasNoMatchingItems();
            if (ingredients.size() != 4) {
                throw new JsonParseException("Invalid ingredient count for recipe (must be exactly 4)");
            }
            ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            return new AltarRitualRecipe(recipeId, act, center, ingredients, result);
        }

        private NonNullList<Ingredient> readIngredients(JsonArray ingredients) {
            NonNullList<Ingredient> result = NonNullList.create();
            for (JsonElement ingredientJson : ingredients) {
                Ingredient ingredient = Ingredient.deserialize(ingredientJson);
                if (!ingredient.hasNoMatchingItems()) {
                    result.add(ingredient);
                }
            }
            return result;
        }

        @Nullable
        @Override
        public AltarRitualRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            ItemStack output = buffer.readItemStack();
            Activation act = buffer.readEnumValue(Activation.class);
            Ingredient center = Ingredient.read(buffer);
            int otherLength = buffer.readInt();
            NonNullList<Ingredient> other = NonNullList.create();
            for (int i = 0; i < otherLength; i++) {
                other.add(Ingredient.read(buffer));
            }
            return new AltarRitualRecipe(recipeId, act, center, other, output);
        }

        @Override
        public void write(PacketBuffer buffer, AltarRitualRecipe recipe) {
            buffer.writeItemStack(recipe.output);
            buffer.writeEnumValue(recipe.activation);
            recipe.centerInput.write(buffer);
            buffer.writeInt(recipe.otherInputs.size());
            for (Ingredient ingredient : recipe.otherInputs) {
                ingredient.write(buffer);
            }
        }

    }
}
