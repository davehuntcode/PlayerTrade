package com.playertrade;

import org.bukkit.entity.Player;

public class TradePlayer {
    public Player self;
    public Player inTradeWith;
    
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

}
