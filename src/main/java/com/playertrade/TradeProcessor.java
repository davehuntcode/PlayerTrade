package com.playertrade;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class TradeProcessor {
    
    public PlayerInstance requester;
    public PlayerInstance requested;
    
    public ArrayList<ItemStack> requesterItems = new ArrayList<ItemStack>();
    public ArrayList<ItemStack> requestedItems = new ArrayList<ItemStack>();
    
    public TradeInventoryView tradeInv;
    
    public TradePlugin plugin;
    
    public TradeProcessor(TradePlugin plugin, PlayerInstance reqr, PlayerInstance reqd) {
	this.plugin = plugin;
	this.requested = reqd;
	this.requester = reqr;
	plugin.activeTrades.add(this);
    }

}
