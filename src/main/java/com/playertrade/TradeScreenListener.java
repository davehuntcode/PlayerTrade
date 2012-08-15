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
	try {
	    Player p = (Player) e.getWhoClicked();
	    Inventory i = e.getInventory();
	    TradePlayer eventPlayer = TradePlayer.getTradePlayer(p);
	    
	    if((eventPlayer != null) && (eventPlayer.isTrading() && eventPlayer.getRequested() != null) && i.getTitle().contains("Trade")){
	        eventPlayer.handleInventoryClick(e);
	    }
	} catch (Exception e1) {
	    plugin.warn("Could not process inventory click for: " + e.getWhoClicked().getName());
	}
	
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent e) {
	 try {
	    Inventory i = e.getInventory();
	    Player p = (Player) e.getPlayer();
	   
	    TradePlayer eventPlayer = TradePlayer.getTradePlayer(p);
	    TradePlayer otherPlayer = eventPlayer.inTradeWith;
	   
	    if (eventPlayer != null && eventPlayer.isTrading() && otherPlayer != null && eventPlayer != otherPlayer) {
		eventPlayer.sendMessage("You closed the trade window and auto-cancelled the trade.");
		otherPlayer.sendMessage(p.getDisplayName() + " closed thier trade window and auto-cancelled the trade.");
		eventPlayer.cancelTrade();
		otherPlayer.cancelTrade();
	    }
	    
	} catch (Exception e2) {
	    plugin.warn("Could not process inventory close for: " + e.getPlayer().getName());
	}
	
    }
}
