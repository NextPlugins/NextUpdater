package com.nextplugins.nextupdater.api;


import com.henryfabio.minecraft.githubupdater.api.configuration.UpdaterConfiguration;
import com.henryfabio.minecraft.githubupdater.bukkit.BukkitGithubUpdater;
import com.henryfabio.minecraft.githubupdater.bukkit.plugin.BukkitUpdatablePlugin;
import lombok.Data;
import org.bukkit.plugin.Plugin;

@Data
public class PluginUpdater {

    private final Plugin plugin;
    private final String repository;
    private final String owner = "NextPlugins";

    public BukkitGithubUpdater init() {
        final BukkitGithubUpdater githubUpdater = new BukkitGithubUpdater(plugin, UpdaterConfiguration.DEFAULT, null);

        githubUpdater.registerUpdatablePlugin(new BukkitUpdatablePlugin(plugin, owner + "/" + repository));

        return githubUpdater;
    }

}
