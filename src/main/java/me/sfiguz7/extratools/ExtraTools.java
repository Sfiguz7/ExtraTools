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
import me.sfiguz7.extratools.implementation.machines.ElectricComposter;
import me.sfiguz7.extratools.implementation.machines.ElectricCrucible;
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



		SlimefunItemStack ELECTRIC_COMPOSTER = new SlimefunItemStack("ELECTRIC_COMPOSTER", Material.MAGENTA_TERRACOTTA, "&cElectric Composter", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(18));
		SlimefunItemStack ELECTRIC_COMPOSTER_2 = new SlimefunItemStack("ELECTRIC_COMPOSTER_2", Material.MAGENTA_TERRACOTTA, "&cElectric Composter &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 4x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(50));


		new ElectricComposter(extra_tools, ELECTRIC_COMPOSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.GILDED_IRON, SlimefunItems.MAGNESIUM_INGOT, SlimefunItems.GILDED_IRON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.COMPOSTER, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.IRON_HOE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_HOE)}) {

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
				new ItemStack[] {SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.ELECTRIC_MOTOR, ELECTRIC_COMPOSTER, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.DIAMOND_HOE), SlimefunItems.LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_HOE)}) {

			@Override
			public int getEnergyConsumption() {
				return 25;
			}

			@Override
			public int getSpeed() {
				return 4;
			}

		}.register(this);

		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Electric_Composter"), researchId, "Electric_Composter", 18), ELECTRIC_COMPOSTER);
		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Electric_Composter_2"), researchId, "Electric_Composter_2", 18), ELECTRIC_COMPOSTER_2);



		SlimefunItemStack ELECTRIC_CRUCIBLE = new SlimefunItemStack("ELECTRIC_CRUCIBLE", Material.BLACK_TERRACOTTA, "&cElectric Crucible", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 1x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(18));
		SlimefunItemStack ELECTRIC_CRUCIBLE_2 = new SlimefunItemStack("ELECTRIC_CRUCIBLE_2", Material.BLACK_TERRACOTTA, "&cElectric Crucible &7(&eII&7)", "", LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE), "&8\u21E8 &7Speed: 4x", LoreBuilder.powerBuffer(256), LoreBuilder.powerPerSecond(50));


		new ElectricCrucible(extra_tools, ELECTRIC_CRUCIBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.LAVA_BUCKET), SlimefunItems.CORINTHIAN_BRONZE_INGOT, new ItemStack(Material.LAVA_BUCKET), SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CRUCIBLE, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.IRON_PICKAXE), SlimefunItems.MEDIUM_CAPACITOR, new ItemStack(Material.IRON_PICKAXE)}) {

			@Override
			public int getEnergyConsumption() {
				return 9;
			}

			@Override
			public int getSpeed() {
				return 1;
			}

		}.register(this);
		new ElectricCrucible(extra_tools, ELECTRIC_CRUCIBLE_2, RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.ELECTRIC_MOTOR, ELECTRIC_CRUCIBLE, SlimefunItems.ELECTRIC_MOTOR, new ItemStack(Material.DIAMOND_PICKAXE), SlimefunItems.LARGE_CAPACITOR, new ItemStack(Material.DIAMOND_PICKAXE)}) {

			@Override
			public int getEnergyConsumption() {
				return 25;
			}

			@Override
			public int getSpeed() {
				return 4;
			}

		}.register(this);

		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Electric_Crucible"), researchId, "Electric_Crucible", 16), ELECTRIC_CRUCIBLE);
		researchId++;
		Slimefun.registerResearch(new Research(new NamespacedKey(this, "Electric_Crucible_2"), researchId, "Electric_Crucible_2", 28), ELECTRIC_CRUCIBLE_2);





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
