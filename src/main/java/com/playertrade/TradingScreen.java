package com.playertrade;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class TradingScreen extends InventoryView {

    public Inventory tradeInv;
    public Inventory requestedInv;
    public TradePlugin plugin;
    
    public TradingScreen(TradePlugin plugin, TradePlayer rqr, TradePlayer rqd) {
	this.plugin = plugin;
	this.tradeInv = plugin.getServer().createInventory(null, 36, "Trade Window");
    }
    
    @Override
    public Inventory getBottomInventory() {
	return tradeInv;
    }

    @Override
    public HumanEntity getPlayer() {
	return null;
    }

    @Override
    public Inventory getTopInventory() {
	return tradeInv;
    }

    @Override
    public InventoryType getType() {
	return InventoryType.CHEST;
    }
    

}
