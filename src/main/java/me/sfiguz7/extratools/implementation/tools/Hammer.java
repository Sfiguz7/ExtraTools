package me.sfiguz7.extratools.implementation.tools;

import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Hammer extends SimpleSlimefunItem<ToolUseHandler> {

    public Hammer() {
        super(ETItems.extra_tools, ETItems.HAMMER, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT),
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT),
                null, new ItemStack(Material.STICK), null});
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (Slimefun.hasPermission(e.getPlayer(), Hammer.this, true)) {

                Block b = e.getBlock();
                ItemStack drop = getDrop(b);

                if (drop != null) {
                    //Can't throw NPEs now
                    b.getLocation().getWorld().dropItemNaturally(b.getLocation(), drop);
                    e.setDropItems(false);
                }
            }
        };
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
            case NETHERRACK: {
                return new ItemStack(Material.SOUL_SAND);
            }
        }

        return null;
    }
}