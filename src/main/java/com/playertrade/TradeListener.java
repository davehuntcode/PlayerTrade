package com.playertrade;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.PluginManager;

public class TradeListener implements Listener {

    public TradePlugin plugin;

    public TradeListener(TradePlugin plugin) {
	this.plugin = plugin;
    }

    public void register() {
	PluginManager manager;
	manager = plugin.getServer().getPluginManager();
	manager.registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

	Player requester = event.getPlayer();
	Entity requested = event.getRightClicked();

	

	

    }

}
