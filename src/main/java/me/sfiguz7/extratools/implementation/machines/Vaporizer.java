package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Vaporizer extends AContainer implements RecipeDisplayItem {

    public Vaporizer(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(8, new ItemStack[]{new ItemStack(Material.WATER_BUCKET)},
                new ItemStack[]{new CustomItem(SlimefunItems.SALT, 4)});
        registerRecipe(8, new ItemStack[]{new ItemStack(Material.LAVA_BUCKET)},
                new ItemStack[]{new CustomItem(SlimefunItems.SULFATE, 16)});
        registerRecipe(3, new ItemStack[]{new ItemStack(Material.MAGMA_BLOCK)},
                new ItemStack[]{SlimefunItems.SULFATE});

    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[recipe.getOutput().length - 1]);
        }

        return displayRecipes;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_HOE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cVaporizer";
    }

    @Override
    public String getMachineIdentifier() {
        return "Vaporizer";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

}

