package com.playertrade;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;


public class TradeInventoryView extends InventoryView {
    
    Inventory topRequesterInventory;
    Inventory bottomRequestedInventory;
    
    public TradeInventoryView(TradePlugin plugin, PlayerInstance requester, PlayerInstance requested ) {
	this.topRequesterInventory    = plugin.getServer().createInventory(requester.self, InventoryType.CHEST);
	this.bottomRequestedInventory = plugin.getServer().createInventory(requested.self, InventoryType.CHEST);
    }

    @Override
    public Inventory getBottomInventory() {
	return bottomRequestedInventory;
    }

    @Override
    public Inventory getTopInventory() {
	return topRequesterInventory;
    }

    @Override
    public InventoryType getType() {
	return InventoryType.CHEST;
    }
    
    @Override
    public HumanEntity getPlayer() {
	return null;
    }
    



}
