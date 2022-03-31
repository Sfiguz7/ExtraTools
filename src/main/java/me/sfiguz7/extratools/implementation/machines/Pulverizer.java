package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pulverizer extends AContainer implements RecipeDisplayItem {

    public Pulverizer() {
        super(ETItems.extra_tools, ETItems.PULVERIZER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {SlimefunItems.SILICON, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.SILICON,
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_MOTOR,
                new ItemStack(Material.IRON_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR,
                new ItemStack(Material.IRON_PICKAXE)});

        addItemHandler(onBreak());
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

        registerRecipe(8, new ItemStack[] {new ItemStack(Material.NETHERRACK, 4)},
            new ItemStack[] {new ItemStack(Material.SOUL_SAND)});

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

    @Override
    public int getEnergyConsumption() {
        return 25;
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
            }
        };
    }

}
