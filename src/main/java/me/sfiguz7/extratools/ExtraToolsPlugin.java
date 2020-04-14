package me.sfiguz7.extratools;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.sfiguz7.extratools.implementation.machines.CobblestoneGenerator;
import me.sfiguz7.extratools.implementation.machines.ConcreteFactory;
import me.sfiguz7.extratools.implementation.machines.ElectricComposter;
import me.sfiguz7.extratools.implementation.machines.GoldTransmuter;
import me.sfiguz7.extratools.implementation.machines.Vaporizer;
import me.sfiguz7.extratools.implementation.tools.Hammer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static me.sfiguz7.extratools.lists.ExtraToolsItems.COBBLESTONE_GENERATOR;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.CONCRETE_FACTORY;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.ELECTRIC_COMPOSTER;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.ELECTRIC_COMPOSTER_2;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.GOLD_TRANSMUTER;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.HAMMER;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.VAPORIZER;
import static me.sfiguz7.extratools.lists.ExtraToolsItems.extra_tools;

public class ExtraToolsPlugin extends JavaPlugin implements SlimefunAddon {

    public static ExtraToolsPlugin instance;
    private int researchId = 4100;

    @Override
    public void onEnable() {

        instance = this;

		/*
		Config cfg = new Config(this);

		if (cfg.getBoolean("options.auto-update")) {
		}
		*/

        int bStatsId = 6945;
        new Metrics(this, bStatsId);


        new Hammer(extra_tools, HAMMER, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                        new ItemStack(Material.IRON_INGOT),
                        new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT),
                        null, new ItemStack(Material.STICK), null}
        ).register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "HAMMER"),
                        ++researchId,
                        "Hammer",
                        3),
                HAMMER
        );

        new GoldTransmuter(extra_tools, GOLD_TRANSMUTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{null, SlimefunItems.SILVER_INGOT, null,
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.ELECTRIC_MOTOR,
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR,
                        new ItemStack(Material.GOLDEN_PICKAXE)}
        ) {

            @Override
            public int getEnergyConsumption() {
                return 9;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "GOLD_TRANSMUTER"),
                        ++researchId,
                        "Gold Transmuter",
                        12),
                GOLD_TRANSMUTER
        );

        new ElectricComposter(extra_tools, ELECTRIC_COMPOSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.GILDED_IRON, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.GILDED_IRON,
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COMPOSTER, SlimefunItems.ELECTRIC_MOTOR,
                        new ItemStack(Material.IRON_HOE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_HOE)}
        ) {

            @Override
            public int getEnergyConsumption() {
                return 9;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        new ElectricComposter(extra_tools, ELECTRIC_COMPOSTER_2, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.BLISTERING_INGOT_3,
                        SlimefunItems.HARDENED_METAL_INGOT,
                        SlimefunItems.ELECTRIC_MOTOR, ELECTRIC_COMPOSTER, SlimefunItems.ELECTRIC_MOTOR,
                        new ItemStack(Material.DIAMOND_HOE), SlimefunItems.LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_HOE)}
        ) {

            @Override
            public int getEnergyConsumption() {
                return 25;
            }

            @Override
            public int getSpeed() {
                return 4;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER"),
                        ++researchId,
                        "Electric Composter",
                        18),
                ELECTRIC_COMPOSTER
        );
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER_2"),
                        ++researchId,
                        "Electric Composter II",
                        18),
                ELECTRIC_COMPOSTER_2
        );

        new CobblestoneGenerator(extra_tools, COBBLESTONE_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.MAGNESIUM_INGOT,
                        SlimefunItems.PROGRAMMABLE_ANDROID_MINER,
                        new ItemStack(Material.WATER_BUCKET), SlimefunItems.BLISTERING_INGOT_3,
                        new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.BIG_CAPACITOR,
                        SlimefunItems.PROGRAMMABLE_ANDROID_MINER}
        ).register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "cobblestone_generator"),
                        ++researchId,
                        "Cobblestone Generator",
                        40),
                COBBLESTONE_GENERATOR
        );

        new Vaporizer(extra_tools, VAPORIZER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.MAGMA_BLOCK), SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.MAGMA_BLOCK),
                        SlimefunItems.HEATING_COIL, SlimefunItems.FLUID_PUMP, SlimefunItems.HEATING_COIL,
                        new ItemStack(Material.MAGMA_BLOCK), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.MAGMA_BLOCK)}
        ) {

            @Override
            public int getEnergyConsumption() {
                return 16;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "VAPORIZER"),
                        ++researchId,
                        "Vaporizer",
                        18),
                VAPORIZER
        );

        new ConcreteFactory(extra_tools, CONCRETE_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{new ItemStack(Material.WATER_BUCKET), SlimefunItems.GILDED_IRON, new ItemStack(Material.WATER_BUCKET),
                        SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                        new ItemStack(Material.WATER_BUCKET), SlimefunItems.SMALL_CAPACITOR, new ItemStack(Material.WATER_BUCKET)}
        ) {

            @Override
            public int getEnergyConsumption() {
                return 8;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "CONCRETE_FACTORY"),
                        ++researchId,
                        "Concrete Factory",
                        12),
                CONCRETE_FACTORY
        );


    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Sfiguz7/ExtraTools/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static ExtraToolsPlugin getInstance() {
        return instance;
    }

}
