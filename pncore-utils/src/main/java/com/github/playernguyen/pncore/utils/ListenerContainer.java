package com.github.playernguyen.pncore.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class ListenerContainer implements Container<Listener> {

    private final Set<Listener> registeredListeners = new HashSet<>();
    private final Plugin plugin;

    public ListenerContainer(Plugin plugin, boolean verbose) {
        this.plugin = plugin;
    }

    public Set<Listener> getRegisteredListeners() {
        return registeredListeners;
    }

    @Override
    public void load() {
        // Register each listener using Bukkit API
        for (Listener listener : registeredListeners) {
            Bukkit.getPluginManager().registerEvents(listener, this.plugin);
        }
    }

    @Override
    public void unload() {
        for (Listener listener : registeredListeners) {
            HandlerList.unregisterAll(listener);
        }
    }

    @Override
    public void put(Listener element) {
        registeredListeners.add(element);
    }

    @Override
    public void remove(Listener element) {
        registeredListeners.remove(element);
    }

    @Override
    public boolean has(Listener element) {
        return registeredListeners.contains(element);
    }
}
