package com.playertrade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TradePlayer {
    public static List<TradePlayer> players = new ArrayList<TradePlayer>();
    
    public Player self;
    public Inventory inv;
    
    public TradePlayer inTradeWith;
    
    public boolean trading;

    public TradePlugin plugin;

    public TradePlayer(TradePlugin plugin, Player player) {
	this.plugin = plugin;
	this.self = player;
    }
    
    
    public boolean hasPermission(String perm) {
	return plugin.hasPermission(self.getName(), perm);
    }


    public void showTradeWindow(TradingScreen tradeWindow) {
	self.openInventory(tradeWindow);
    }
    
    public void showInv(Inventory tradeWindow) {
  	self.openInventory(tradeWindow);
      }
    
    public void closeTradeWindow() {
	self.closeInventory();
    }
    
    
    public static TradePlayer getTradePlayer(Player p) {
	return getTradePlayer(p.getName());
    }
    
    public static TradePlayer getTradePlayer(String pName) {
	for(TradePlayer tp : players) {
	    if(tp.self.getName().equals(pName)) {
		return tp;
	    }
	}
	return null;
    }


    public boolean isTrading() {
	return trading;
    }


    public void handleInventoryClick(InventoryClickEvent e) {
	plugin.info("Player is handling Click");
    }

}
