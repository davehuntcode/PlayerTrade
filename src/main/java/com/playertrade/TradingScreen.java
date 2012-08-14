package com.playertrade;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class TradingScreen extends InventoryView {

    public Inventory requesterInv;
    public Inventory requestedInv;
    public TradePlugin plugin;
    
    public TradingScreen(TradePlugin plugin, TradePlayer rqr, TradePlayer rqd) {
	this.plugin = plugin;
	this.requesterInv = plugin.getServer().createInventory(null, 36, "Trading with " + rqr.self.getDisplayName());
	this.requestedInv = plugin.getServer().createInventory(null, 36, "Trading with " + rqd.self.getDisplayName());
	rqr.inv = requesterInv;
	rqd.inv = requestedInv;
    }
    
    @Override
    public Inventory getBottomInventory() {
	return requestedInv;
    }

    @Override
    public HumanEntity getPlayer() {
	return null;
    }

    @Override
    public Inventory getTopInventory() {
	return requesterInv;
    }

    @Override
    public InventoryType getType() {
	return InventoryType.PLAYER;
    }

}
