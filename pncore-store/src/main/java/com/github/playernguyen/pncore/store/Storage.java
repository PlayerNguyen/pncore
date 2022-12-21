package com.github.playernguyen.pncore.store;

import org.bukkit.plugin.Plugin;

public abstract class Storage {

    private final String fileName;
    private final Plugin plugin;

    public Storage(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void load() {
        String pluginName = this.plugin.getName();
        this.plugin.getLogger().info(String.format("[::%s] Loading I/O file data " +
                "of %s", pluginName, fileName));
    }

}
