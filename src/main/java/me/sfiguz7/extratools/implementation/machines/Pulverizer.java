package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Pulverizer extends AContainer implements RecipeDisplayItem {

    public Pulverizer(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(8, new ItemStack[] {new ItemStack(Material.STONE, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.GRANITE, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.DIORITE, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.ANDESITE, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.COBBLESTONE, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});

        registerRecipe(8, new ItemStack[] {new ItemStack(Material.GRAVEL, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.GRASS_BLOCK, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.DIRT, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.COARSE_DIRT, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.PODZOL, 4)},
                new ItemStack[] {new ItemStack(Material.SAND)});

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
        return new ItemStack(Material.IRON_PICKAXE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cPulverizer";
    }

    @Override
    public String getMachineIdentifier() {
        return "PULVERIZER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

}
