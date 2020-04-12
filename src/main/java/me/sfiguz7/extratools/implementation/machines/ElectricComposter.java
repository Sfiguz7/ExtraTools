package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.materials.MaterialCollections;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class ElectricComposter extends AContainer implements RecipeDisplayItem {

    public ElectricComposter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {

        for (Material leave : MaterialCollections.getAllLeaves()) {
            registerRecipe(8, new ItemStack[] {new ItemStack(leave, 8)},
                new ItemStack[] {new ItemStack(Material.DIRT)});
        }
        for (Material sapling : MaterialCollections.getAllSaplings()) {
            registerRecipe(8, new ItemStack[] {new ItemStack(sapling, 8)},
                new ItemStack[] {new ItemStack(Material.DIRT)});
        }
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.STONE, 4)},
            new ItemStack[] {new ItemStack(Material.NETHERRACK)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.SAND, 2)},
            new ItemStack[] {new ItemStack(Material.SOUL_SAND)});
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.WHEAT, 4)},
            new ItemStack[] {new ItemStack(Material.NETHER_WART)});

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
        return new ItemStack(Material.WOODEN_HOE);
    }

    @Override
    public String getInventoryTitle() {
        return "&cElectric Composter";
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_COMPOSTER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

}

