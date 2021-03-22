package com.nextplugins.nextupdater;

import com.nextplugins.nextupdater.api.PluginUpdater;
import me.bristermitten.pdm.PluginDependencyManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class NextUpdater extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginDependencyManager.of(this).loadAllDependencies().thenRun(() -> {
            PluginUpdater updater = new PluginUpdater(this, "NextUpdater");
            updater.init();
        });
    }

}
