package me.sfiguz7.extratools.implementation.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.sfiguz7.extratools.implementation.interfaces.ETInventoryBlock;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class CobblestoneGenerator extends SimpleSlimefunItem<BlockTicker> implements ETInventoryBlock,
    EnergyNetComponent {

    private static final int ENERGY_CONSUMPTION = 32;
    private final int[] border = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 18, 19, 20, 21, 22, 27, 28, 29, 30,
        31, 36, 37, 38, 39, 40, 41, 42, 43, 44, 22};
    private final int[] inputBorder = {};
    private final int[] outputBorder = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    private int decrement = 2;

    public CobblestoneGenerator() {
        super(ETItems.extra_tools, ETItems.COBBLESTONE_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.MAGNESIUM_INGOT,
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER,
                new ItemStack(Material.WATER_BUCKET), SlimefunItems.BLISTERING_INGOT_3,
                new ItemStack(Material.LAVA_BUCKET),
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.BIG_CAPACITOR,
                SlimefunItems.PROGRAMMABLE_ANDROID_MINER});

        createPreset(this, this::constructMenu);

        addItemHandler(onBreak());
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i : border) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : inputBorder) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.CYAN_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }
        for (int i : outputBorder) {
            preset.addItem(i, new CustomItem(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE), " "),
                ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor,
                                       ClickAction action) {
                    return cursor == null || cursor.getType() == Material.AIR;
                }
            });
        }
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {24, 25};
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 512;
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

    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {

            @Override
            // Fires first!! The method tick() fires after this
            public void uniqueTick() {
                // Needed to keep track of all cobble gens at once,
                // All it does is set back to max (for now 2, will be customizable)
                // when it reaches the lowest possible (AKA 1)
                if (decrement == 1) {
                    decrement = 2;
                    return;
                }
                decrement--;

            }

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                // We only act once per decrement cycle, when decrement got to
                // lowest and has been reset
                if (decrement != 2) {
                    return;
                }

                ItemStack output = new ItemStack(Material.COBBLESTONE);

                if (getCharge(b.getLocation()) >= ENERGY_CONSUMPTION) {
                    BlockMenu menu = BlockStorage.getInventory(b);

                    if (!menu.fits(output, getOutputSlots())) {
                        return;
                    }

                    removeCharge(b.getLocation(), ENERGY_CONSUMPTION);
                    menu.pushItem(output, getOutputSlots());
                }
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        };
    }

}

