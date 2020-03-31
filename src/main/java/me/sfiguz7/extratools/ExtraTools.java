package me.sfiguz7.extratools;

import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.Slimefun;
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
	
	@Override
	public void onEnable() {

		/*
		Config cfg = new Config(this);

		if (cfg.getBoolean("options.auto-update")) {
		}
		*/

		int bStatsId = 6945;
		new Metrics(this, bStatsId);


		SlimefunItemStack HAMMER = new SlimefunItemStack("HAMMER", Material.IRON_PICKAXE, "&cHammer", "", "&9Pulverizes blocks");


		ItemStack[] recipe = {
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
				new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT),
				null, new ItemStack(Material.STICK), null
		};

		Hammer hammer = new Hammer(Categories.TOOLS, HAMMER, RecipeType.MAGIC_WORKBENCH, recipe);
		hammer.register(this);
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
