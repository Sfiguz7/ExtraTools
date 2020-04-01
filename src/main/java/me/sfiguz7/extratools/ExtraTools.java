package me.sfiguz7.extratools;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.Freezer;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.sfiguz7.extratools.implementation.machines.GoldTransmuter;
import me.sfiguz7.extratools.implementation.tools.Hammer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
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

		Category extra_tools = new Category(new NamespacedKey(this, "extra_tools"), new CustomItem(Material.DIAMOND_AXE, "&4Extra Tools"));



		SlimefunItemStack HAMMER = new SlimefunItemStack("HAMMER", Material.IRON_PICKAXE, "&cHammer", "", "&9Pulverizes blocks");

		ItemStack[] recipe = {
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT),
				null, new ItemStack(Material.STICK), null
		};

		new Hammer(extra_tools, HAMMER, RecipeType.MAGIC_WORKBENCH, recipe).register(this);

		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Hammer"), researchId, "Hammer", 3), HAMMER);



		SlimefunItemStack GOLD_TRANSMUTER = new SlimefunItemStack("GOLD_TRANSMUTER", Material.YELLOW_TERRACOTTA, "&6Gold Transmuter", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(18));

		new GoldTransmuter(extra_tools, GOLD_TRANSMUTER, RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, SlimefunItems.SILVER_INGOT, null, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.GOLD_24K_BLOCK, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.GOLDEN_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.GOLDEN_PICKAXE)}) {

			@Override
			public int getEnergyConsumption() {
				return 9;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.register(this);

		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Gold_Transmuter"), researchId, "Gold_Transmuter", 12), GOLD_TRANSMUTER);




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
