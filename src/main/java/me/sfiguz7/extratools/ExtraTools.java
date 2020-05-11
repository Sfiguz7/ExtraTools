package me.sfiguz7.extratools;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import me.sfiguz7.extratools.implementation.machines.CobblestoneGenerator;
import me.sfiguz7.extratools.implementation.machines.ConcreteFactory;
import me.sfiguz7.extratools.implementation.machines.ElectricComposter;
import me.sfiguz7.extratools.implementation.machines.GoldTransmuter;
import me.sfiguz7.extratools.implementation.machines.Pulverizer;
import me.sfiguz7.extratools.implementation.machines.Vaporizer;
import me.sfiguz7.extratools.implementation.tools.Hammer;
import me.sfiguz7.extratools.lists.ETItems;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class ExtraTools extends JavaPlugin implements SlimefunAddon {

    public static ExtraTools instance;
    private int researchId = 4100;

    @Override
    public void onEnable() {

        instance = this;

        if (getConfig().getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "Sfiguz7/ExtraTools/master").start();
        }

        int bStatsId = 6945;
        new Metrics(this, bStatsId);


        new Hammer().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "HAMMER"),
                        ++researchId, "Hammer", 3),
                ETItems.HAMMER
        );

        new GoldTransmuter().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "GOLD_TRANSMUTER"),
                        ++researchId, "Gold Transmuter", 12),
                ETItems.GOLD_TRANSMUTER
        );

        new ElectricComposter(ElectricComposter.Tier.ONE){

            @Override
            public int getEnergyConsumption() {
                return 9;
            }

            @Override
            public int getSpeed() {
                return 1;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER"),
                        ++researchId, "Electric Composter", 18),
                ETItems.ELECTRIC_COMPOSTER
        );

        new ElectricComposter(ElectricComposter.Tier.TWO){

            @Override
            public int getEnergyConsumption() {
                return 25;
            }

            @Override
            public int getSpeed() {
                return 4;
            }

        }.register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "ELECTRIC_COMPOSTER_2"),
                        ++researchId, "Electric Composter II", 18),
                ETItems.ELECTRIC_COMPOSTER_2
        );

        new CobblestoneGenerator().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "COBBLESTONE_GENERATOR"),
                        ++researchId, "Cobblestone Generator", 40),
                ETItems.COBBLESTONE_GENERATOR
        );

        new Vaporizer().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "VAPORIZER"),
                        ++researchId, "Vaporizer", 18),
                ETItems.VAPORIZER
        );

        new ConcreteFactory().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "CONCRETE_FACTORY"),
                        ++researchId, "Concrete Factory", 12),
                ETItems.CONCRETE_FACTORY
        );

        new Pulverizer().register(this);
        Slimefun.registerResearch(new Research(new NamespacedKey(this, "PULVERIZER"),
                        ++researchId, "Pulverizer", 18),
                ETItems.PULVERIZER
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

    public static ExtraTools getInstance() {
        return instance;
    }

}
