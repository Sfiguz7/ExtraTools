package me.sfiguz7.extratools.implementation.tools;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Hammer extends SimpleSlimefunItem<BlockBreakHandler> {

    public Hammer(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public BlockBreakHandler getItemHandler() {
        return new BlockBreakHandler() {

            @Override
            public boolean isPrivate() {
                return false;
            }

            @Override
            public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops) {
                if (isItem(item)) {
                    if (!Slimefun.hasUnlocked(e.getPlayer(), Hammer.this, true)) {
                        return true;
                    }

                    Block b = e.getBlock();
                    ItemStack drop = this.getDrop(b);

                    if (drop == null) {
                        return true;
                    }

                    b.getLocation().getWorld().dropItemNaturally(b.getLocation(), drop);
                    e.setDropItems(false);
                    return true;
                }
                return false;
            }


            public ItemStack getDrop(Block b) {

                Material m = b.getType();

                switch (m) {
                    case STONE:
                    case GRANITE:
                    case DIORITE:
                    case ANDESITE:
                    case COBBLESTONE: {
                        return new ItemStack(Material.GRAVEL);
                    }
                    case GRAVEL:
                    case GRASS_BLOCK:
                    case DIRT:
                    case COARSE_DIRT:
                    case PODZOL: {
                        return new ItemStack(Material.SAND);
                    }
                    case IRON_ORE: {
                        return SlimefunItems.IRON_DUST;
                    }
                    case GOLD_ORE: {
                        return SlimefunItems.GOLD_DUST;
                    }
                }

                return null;
            }


        };
    }

}