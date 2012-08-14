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
    
    
    public TradingScreen tradeWindow;
    
    public TradeProcessor(TradePlugin plugin, TradePlayer requester,   TradePlayer requested) {
	this.plugin = plugin;
	this.requested = requested;
	this.requester = requester;
	
	plugin.activeTrades.add(this);
    }

    public void initiateTrade() {
	plugin.info(String.format("Starting trade between %s and %s", requester.self.getName(), requested.self.getName()));
	
	this.requested.inTradeWith = this.requester;
	this.requester.inTradeWith = this.requested;
	
	this.requester.trading = true;
	this.requested.trading = true;
	
	this.tradeWindow = new TradingScreen(plugin, requester, requested);
	
	this.requester.showInv(requester.inv);
	this.requested.showInv(requested.inv);
	
	this.tradeWindow.setItem(0,  new ItemStack(35, 1, (short) 14));
	this.tradeWindow.setItem(1,  new ItemStack(35, 1, (short) 5));
	this.tradeWindow.setItem(35, new ItemStack(35, 1, (short) 14));
	this.tradeWindow.setItem(34, new ItemStack(35, 1, (short) 5));

	
    }
    
    public void cancelTrade() {
	this.requester.closeTradeWindow();
	this.requested.closeTradeWindow();
	
	this.requested.inTradeWith = null;
	this.requester.inTradeWith = null;
	
	this.requester.trading = false;
	this.requested.trading = false;
    }

}
