package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.graalvm.compiler.api.replacements.Snippet;

import java.util.ArrayList;
import java.util.List;

public abstract class ElectricComposter extends AContainer implements RecipeDisplayItem {

    private final Tier tier;

    public ElectricComposter(Tier tier) {
        super(ETItems.extra_tools, tier == Tier.ONE ? ETItems.ELECTRIC_COMPOSTER : ETItems.ELECTRIC_COMPOSTER_2,
            RecipeType.ENHANCED_CRAFTING_TABLE, tier.recipe);
        this.tier = tier;

        addItemHandler(onBreak());
    }

    @Override
    protected void registerDefaultRecipes() {

        for (Material leave : SlimefunTag.LEAVES.getValues()) {
            registerRecipe(8, new ItemStack[] { new ItemStack(leave, 8) },
                new ItemStack[] { new ItemStack(Material.DIRT) });
        }
        for (Material sapling : SlimefunTag.SAPLINGS.getValues()) {
            registerRecipe(8, new ItemStack[] { new ItemStack(sapling, 8) },
                new ItemStack[] { new ItemStack(Material.DIRT) });
        }
        registerRecipe(8, new ItemStack[] { new ItemStack(Material.STONE, 4) },
            new ItemStack[] { new ItemStack(Material.NETHERRACK) });
        registerRecipe(8, new ItemStack[] { new ItemStack(Material.SAND, 2) },
            new ItemStack[] { new ItemStack(Material.SOUL_SAND) });
        registerRecipe(8, new ItemStack[] { new ItemStack(Material.WHEAT, 4) },
            new ItemStack[] { new ItemStack(Material.NETHER_WART) });

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
        return "ELECTRIC_COMPOSTER_" + this.tier.name();
    }

    @Override
    public int getCapacity() {
        return 256;
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

    public enum Tier {
        ONE(new ItemStack[] {
            SlimefunItems.GILDED_IRON, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.GILDED_IRON,
            SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COMPOSTER, SlimefunItems.ELECTRIC_MOTOR,
            new ItemStack(Material.IRON_HOE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_HOE) }
        ),
        TWO(new ItemStack[] { SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.BLISTERING_INGOT_3,
            SlimefunItems.HARDENED_METAL_INGOT,
            SlimefunItems.ELECTRIC_MOTOR, ETItems.ELECTRIC_COMPOSTER, SlimefunItems.ELECTRIC_MOTOR,
            new ItemStack(Material.DIAMOND_HOE), SlimefunItems.LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_HOE) }
        );

        private final ItemStack[] recipe;

        Tier(ItemStack[] recipe) {
            this.recipe = recipe;
        }
    }

}

