package com.playertrade;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginManager;

public class TradePlayerListener implements Listener {

    public TradePlugin plugin;

    public TradePlayerListener(TradePlugin plugin) {
	this.plugin = plugin;
    }
    
    public void register() {
	PluginManager manager;
	manager = plugin.getServer().getPluginManager();
	manager.registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

	Player reqr = event.getPlayer();
	Entity reqd = event.getRightClicked();

	if (reqd instanceof Player && reqr != null && reqd != null) {
	    TradePlayer requester = new TradePlayer(plugin, reqr);
	    TradePlayer requested = new TradePlayer(plugin, (Player) reqd);

	    if (requester.hasPermission("tradeplugin.perm.request")
		    && requested.hasPermission("tradeplugin.perm.accept")) {

		TradeProcessor proc = new TradeProcessor(plugin, requester, requested);
		proc.initiateTrade();
	    }

	}

    }

}
