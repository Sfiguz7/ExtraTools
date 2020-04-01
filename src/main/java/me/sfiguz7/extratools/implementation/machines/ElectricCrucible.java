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

public abstract class ElectricCrucible extends AContainer implements RecipeDisplayItem {

    public ElectricCrucible(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {

        for (Material leave : MaterialCollections.getAllLeaves()) {
            registerRecipe(20, new ItemStack[] { new ItemStack(leave, 16), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.WATER_BUCKET) });
        }
        for (Material terracotta : MaterialCollections.getAllTerracottaColors()) {
            registerRecipe(20, new ItemStack[] { new ItemStack(terracotta, 12), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
        }
        registerRecipe(20, new ItemStack[] { new ItemStack(Material.COBBLESTONE, 16), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
        registerRecipe(20, new ItemStack[] { new ItemStack(Material.NETHERRACK, 16), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
        registerRecipe(20, new ItemStack[] { new ItemStack(Material.STONE, 12), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });
        registerRecipe(20, new ItemStack[] { new ItemStack(Material.OBSIDIAN), new ItemStack(Material.BUCKET) }, new ItemStack[] { new ItemStack(Material.LAVA_BUCKET) });

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
        return new ItemStack(Material.WOODEN_SHOVEL);
    }

    @Override
    public String getInventoryTitle() {
        return "&cElectric Crucible";
    }

    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_CRUCIBLE";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

}


