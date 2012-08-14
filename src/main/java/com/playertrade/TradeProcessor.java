package com.playertrade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class TradeProcessor {

 
    public TradePlayer requester;
    public TradePlayer requested;
    
    public List<ItemStack> requesterItems = new ArrayList<ItemStack>();
    public List<ItemStack> requestedItems = new ArrayList<ItemStack>();
    
    public TradePlugin plugin;
    
    public TradeProcessor(TradePlugin plugin, TradePlayer requester,   TradePlayer requested) {
	this.plugin = plugin;
	this.requested = requested;
	this.requester = requester;
    }

    public void initiateTrade() {
	
    }

}
