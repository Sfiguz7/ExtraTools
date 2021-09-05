package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
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

public class Vaporizer extends AContainer implements RecipeDisplayItem {

    public Vaporizer() {
        super(ETItems.extra_tools, ETItems.VAPORIZER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {new ItemStack(Material.MAGMA_BLOCK), SlimefunItems.ELECTRIC_MOTOR,
                new ItemStack(Material.MAGMA_BLOCK),
                SlimefunItems.HEATING_COIL, SlimefunItems.FLUID_PUMP, SlimefunItems.HEATING_COIL,
                new ItemStack(Material.MAGMA_BLOCK), SlimefunItems.MEDIUM_CAPACITOR,
                new ItemStack(Material.MAGMA_BLOCK)});

        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(8, new ItemStack[] {new ItemStack(Material.WATER_BUCKET)},
            new ItemStack[] {new ItemStack(Material.BUCKET),
                new CustomItemStack(SlimefunItems.SALT, 4)
            });
        registerRecipe(8, new ItemStack[] {new ItemStack(Material.LAVA_BUCKET)},
            new ItemStack[] {new ItemStack(Material.BUCKET),
                new CustomItemStack(SlimefunItems.SULFATE, 16)
            });
        registerRecipe(3, new ItemStack[] {new ItemStack(Material.MAGMA_BLOCK)},
            new ItemStack[] {SlimefunItems.SULFATE});

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
        return "VAPORIZER";
    }

    @Override
    public int getCapacity() {
        return 256;
    }

    @Override
    public int getEnergyConsumption() {
        return 16;
    }

    @Override
    public int getSpeed() {
        return 1;
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

