package com.playertrade;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class TradingScreen extends InventoryView {

    @Override
    public Inventory getBottomInventory() {
	return null;
    }

    @Override
    public HumanEntity getPlayer() {
	return null;
    }

    @Override
    public Inventory getTopInventory() {
	return null;
    }

    @Override
    public InventoryType getType() {
	return InventoryType.CHEST;
    }

}
