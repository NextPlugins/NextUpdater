package com.nextplugins.nextupdater;

import com.henryfabio.minecraft.githubupdater.bukkit.BukkitGithubUpdater;
import com.nextplugins.nextupdater.api.PluginUpdater;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NextUpdater extends JavaPlugin {

    private List<BukkitGithubUpdater> updaters;

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            this.updaters = new ArrayList<>();

            PluginUpdater updater = new PluginUpdater(this, "NextUpdater");

            updaters.add(updater.init());
            registerDefaultPlugins();

            new Metrics(this, 10773);
        });
    }

    @Override
    public void onDisable() {
        updaters.forEach(BukkitGithubUpdater::stop);
    }

    private void registerDefaultPlugins() {
        for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
            if (!plugin.getName().startsWith("Next")) continue;
            if (plugin.getName().equals("NextUpdater")) continue;

            PluginUpdater updater = new PluginUpdater(plugin, plugin.getName());
            updaters.add(updater.init());
        }
    }

}
