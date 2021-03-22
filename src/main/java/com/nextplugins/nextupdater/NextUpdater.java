package com.nextplugins.nextupdater;

import com.nextplugins.nextupdater.api.PluginUpdater;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextUpdater extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            PluginUpdater updater = new PluginUpdater(this, "NextUpdater");

            updater.init();
            registerDefaultPlugins();

            new Metrics(this, 10773);
        });
    }

    private void registerDefaultPlugins() {
        for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
            if (!plugin.getName().startsWith("Next")) continue;

            PluginUpdater updater = new PluginUpdater(plugin, plugin.getName());
            updater.init();
        }
    }

}
