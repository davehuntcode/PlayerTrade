package com.playertrade;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;

public class TradeScreenListener implements Listener {
    public TradePlugin plugin;

    public TradeScreenListener(TradePlugin plugin) {
	this.plugin = plugin;
    }

    public void register() {
	PluginManager manager;
	manager = plugin.getServer().getPluginManager();
	manager.registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
	Player p = (Player) e.getWhoClicked();
	Inventory i = e.getInventory();
	TradePlayer tp = TradePlayer.getTradePlayer(p);
	
	if((tp != null) && (tp.isTrading()) && i.getTitle().contains("trading")){
	    tp.handleInventoryClick(e);
	}
	
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent e) {
    }
}
