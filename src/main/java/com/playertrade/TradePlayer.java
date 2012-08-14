package com.playertrade;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TradePlayer {
    public static List<TradePlayer> players = new ArrayList<TradePlayer>();
    
    public Player self;
    
    public TradePlayer inTradeWith;
    
    public boolean trading;
    public boolean confirmed;
    
    public int minSlot;
    public int maxSlot;
    
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
    
    public void cancelTrade() {
   	this.closeTradeWindow();
   	TradePlayer.players.remove(inTradeWith);
   	this.inTradeWith = null;
   	this.setTrading(false);
   	
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
    
    public void sendMessage(String m) {
	self.sendMessage(m);
    }

    public void setTrading(boolean trading) {
	this.trading = trading;
    }

    public boolean isTrading() {
	return trading;
    }


    public void handleInventoryClick(InventoryClickEvent e) {
	try {
	    int slot = e.getSlot();
	    ItemStack i = e.getCurrentItem();
	    TradePlayer other = null;
	    if(this.inTradeWith != null) other = this.inTradeWith;
	    
	    if( (slot > minSlot) && (slot < maxSlot) ) {
		if(this.hasConfirmedTrade()) {
		    
		} else {
		    plugin.info(String.format("%s has added/removed %s", e.getWhoClicked().getName(), i.getType().name()));
		    e.setCancelled(true);
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }


    public boolean hasConfirmedTrade() {
	// TODO Auto-generated method stub
	return false;
    }


    public TradePlayer getRequested() {
	return inTradeWith;
    }


    public void registerTrade(TradePlayer other) {
	players.add(other);
	this.setTrading(true);
	this.inTradeWith = other;
	
    }

}
