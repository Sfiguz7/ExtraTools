package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GoldTransmuter extends AContainer implements RecipeDisplayItem {

    public GoldTransmuter() {
        super(ETItems.extra_tools, ETItems.GOLD_TRANSMUTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{null, SlimefunItems.SILVER_INGOT, null,
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.ELECTRIC_MOTOR,
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR,
                        new ItemStack(Material.GOLDEN_PICKAXE)});
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(7, new ItemStack[]{SlimefunItems.GOLD_24K_BLOCK},
                new ItemStack[]{new ItemStack(Material.GOLD_BLOCK)});
        registerRecipe(2, new ItemStack[]{SlimefunItems.GOLD_4K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 4)});
        registerRecipe(2, new ItemStack[]{SlimefunItems.GOLD_6K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 9)});
        registerRecipe(3, new ItemStack[]{SlimefunItems.GOLD_8K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 13)});
        registerRecipe(3, new ItemStack[]{SlimefunItems.GOLD_10K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 18)});
        registerRecipe(4, new ItemStack[]{SlimefunItems.GOLD_12K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 22)});
        registerRecipe(4, new ItemStack[]{SlimefunItems.GOLD_14K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 27)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.GOLD_16K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 31)});
        registerRecipe(5, new ItemStack[]{SlimefunItems.GOLD_18K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 36)});
        registerRecipe(6, new ItemStack[]{SlimefunItems.GOLD_20K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 40)});
        registerRecipe(6, new ItemStack[]{SlimefunItems.GOLD_22K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 45)});
        registerRecipe(7, new ItemStack[]{SlimefunItems.GOLD_24K},
                new ItemStack[]{new ItemStack(Material.GOLD_NUGGET, 49)});
        registerRecipe(2, new ItemStack[]{new ItemStack(Material.GOLD_INGOT)},
                new ItemStack[]{SlimefunItems.GOLD_DUST});
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
        return new ItemStack(Material.GOLDEN_PICKAXE);
    }

    @Override
    public String getInventoryTitle() {
        return "&6Gold Transmuter";
    }

    @Override
    public String getMachineIdentifier() {
        return "GOLD_TRANSMUTER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 9;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

}
