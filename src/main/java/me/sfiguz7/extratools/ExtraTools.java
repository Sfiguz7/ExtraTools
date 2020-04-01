package me.sfiguz7.extratools;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.sfiguz7.extratools.implementation.machines.CobblestoneGenerator;
import me.sfiguz7.extratools.implementation.machines.ElectricComposter;
import me.sfiguz7.extratools.implementation.machines.GoldTransmuter;
import me.sfiguz7.extratools.implementation.tools.Hammer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

public class ExtraTools extends JavaPlugin implements SlimefunAddon {

    private int researchId = 4100;

    @Override
    public void onEnable() {

		/*
		Config cfg = new Config(this);

		if (cfg.getBoolean("options.auto-update")) {
		}
		*/

        int bStatsId = 6945;
        new Metrics(this, bStatsId);

        Category extra_tools = new Category(new NamespacedKey(this,
                "extra_tools"),
                new CustomItem(Material.DIAMOND_AXE, "&4Extra Tools")
        );


        SlimefunItemStack hammer = new SlimefunItemStack("HAMMER",
                Material.IRON_PICKAXE,
                "&cHammer",
                "",
                "&9Pulverizes blocks"
        );

        new Hammer(extra_tools, hammer, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                        new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT),
                        null, new ItemStack(Material.STICK), null}
        ).register(this);

        Slimefun.registerResearch(new Research(new NamespacedKey(this, "hammer"),
                        ++researchId,
                        "Hammer",
                        3),
                hammer
        );


        SlimefunItemStack gold_transmuter = new SlimefunItemStack("GOLD_TRANSMUTER",
                Material.YELLOW_TERRACOTTA,
                "&6Gold Transmuter",
                "",
                LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
                LoreBuilder.powerBuffer(256),
                LoreBuilder.powerPerSecond(18)
        );

        new GoldTransmuter(extra_tools, gold_transmuter, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{null, SlimefunItems.SILVER_INGOT, null,
                        SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.ELECTRIC_MOTOR,
                        new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE)}
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

        Slimefun.registerResearch(new Research(new NamespacedKey(this, "gold_transmuter"),
                        ++researchId,
                        "Gold_Transmuter",
                        12),
                gold_transmuter
        );


        SlimefunItemStack electric_composter = new SlimefunItemStack("ELECTRIC_COMPOSTER",
                Material.MAGENTA_TERRACOTTA,
                "&cElectric Composter",
                "",
                LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
                "&8\u21E8 &7Speed: 1x",
                LoreBuilder.powerBuffer(256),
                LoreBuilder.powerPerSecond(18)
        );
        SlimefunItemStack electric_composter_2 = new SlimefunItemStack("ELECTRIC_COMPOSTER_2",
                Material.MAGENTA_TERRACOTTA,
                "&cElectric Composter &7(&eII&7)",
                "",
                LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
                "&8\u21E8 &7Speed: 4x",
                LoreBuilder.powerBuffer(256),
                LoreBuilder.powerPerSecond(50)
        );

        new ElectricComposter(extra_tools, electric_composter, RecipeType.ENHANCED_CRAFTING_TABLE,
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
        new ElectricComposter(extra_tools, electric_composter_2, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.HARDENED_METAL_INGOT,
                        SlimefunItems.ELECTRIC_MOTOR, electric_composter, SlimefunItems.ELECTRIC_MOTOR,
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

        Slimefun.registerResearch(new Research(new NamespacedKey(this, "electric_composter"),
                        ++researchId,
                        "Electric_Composter",
                        18),
                electric_composter
        );
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "electric_composter_2"),
                        ++researchId,
                        "Electric_Composter_2",
                        18),
                electric_composter_2
        );



        SlimefunItemStack cobblestoneGenerator = new SlimefunItemStack("COBBLESTONE_GENERATOR",
                Material.POLISHED_ANDESITE,
                "&cCobblestone Generator",
                "",
                LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE),
                LoreBuilder.powerBuffer(256),
                LoreBuilder.powerPerSecond(36)
        );

        new CobblestoneGenerator(extra_tools, cobblestoneGenerator, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.PROGRAMMABLE_ANDROID_MINER,
                        new ItemStack(Material.WATER_BUCKET), SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.LAVA_BUCKET),
                        SlimefunItems.PROGRAMMABLE_ANDROID_MINER, SlimefunItems.BIG_CAPACITOR, SlimefunItems.PROGRAMMABLE_ANDROID_MINER,}
        ).register(this);

        Slimefun.registerResearch(new Research(new NamespacedKey(this, "cobblestone_generator"),
                        ++researchId,
                        "Cobblestone_Generator",
                        40),
                cobblestoneGenerator
        );


    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
