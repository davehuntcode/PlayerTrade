package com.playertrade;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.EntityPlayer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerTradePlayer {

    public boolean isTrading;
    private EntityPlayer entityPlayer;
    private PlayerTradePlayer requested;
    private PlayerTradePlayer requester;
    private PlayerTradeModel trade;

    public int chestStartSlotIndex = -1;
    public int chestEndSlotIndex = -1;
    public int acceptButton = -1;
    public int cancelButton = -1;

    public static List<PlayerTradePlayer> players = new ArrayList<PlayerTradePlayer>();

    public int tradeCount = 0;

    public PlayerTradePlugin plugin;

    public PlayerTradePlayer(EntityPlayer player, PlayerTradePlugin plugin) {
	this.plugin = plugin;
	this.entityPlayer = player;
	players.add(this);
    }

    public void handleClickEvent(InventoryClickEvent e) {

	if (e.isShiftClick()) {
	    e.setCancelled(true);
	    return;
	}



	e.setCancelled(true);
    }

    public boolean onTradeRequest(PlayerTradePlayer other) {

	if ((tradeCount >= plugin.config.tradeThreshold)) {
	    sendMessage(ChatColor.RED   + "You have attempted to send too many trade requests! Please wait for your existing trades to expire then try again.");
	    return false;
	} else if (other != null) {
	    tradeCount++;

	    this.requested = other;
	    other.requested = this;

	    startRequestTimer(this.plugin.config.tradeRequestTimeout);

	    return true;
	}

	return false;
    }

    private void startRequestTimer(int timeInSeconds) {
	this.plugin.getServer().getScheduler()
		.scheduleSyncDelayedTask(this.plugin, new Runnable() {
		    @Override
		    public void run() {
			PlayerTradePlayer.this.cancelRequest(true,
				"Request timed out.");
		    }
		}, timeInSeconds * 20);
    }

    protected void cancelRequest(boolean informPlayers, String reasonForCancel) {

	try {
	    if (informPlayers)
	        if (this.requested != null) {
	    	
	        requested.entityPlayer.sendMessage("Your trade request has been declined. Reason: "+ reasonForCancel);
	    	this.entityPlayer.sendMessage(requested.entityPlayer.getName() + "'s trade request has been declined. Reason: " + reasonForCancel);
	        
	        } else {
	    	
	            this.entityPlayer.sendMessage("Your trade request has been declined. Reason: " + reasonForCancel);
	    	requested.entityPlayer.sendMessage(requested.entityPlayer.getName() + "'s trade request has been declined. Reason: " + reasonForCancel);
	        }

	    
	    if(this.requested != null) {
	        players.remove(this.requested);
	    } else {
	        players.remove(this.requester);
	    }

	    players.remove(this);
	    
	    this.requested = null;
	    this.requester = null;
	} catch (Exception e) {
	  plugin.warn("Could not cancel trade! " + e.getMessage());
	}
	if (this.tradeCount > 0)
	    this.tradeCount--;

    }

    private void closeTrade() {
	this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
	   public void run() {
	       ((Player) PlayerTradePlayer.this).closeInventory();
	   }
	});
	
    }

    public void acceptRequest(PlayerTradePlayer other) {
	if (((other != null) && (this.requested != null))) {

	    this.requested = null;
	    other.requested = null;

	    this.isTrading = true;
	    other.isTrading = true;

	    this.trade = new PlayerTradeModel(other, this, plugin);
	    other.trade = this.trade;

	    trade.doTrade();
	}
    }

    public void removePlayer(PlayerTradePlayer other) {
	if ((other != null) && players.contains(other)) {
	    players.remove(other);
	    other = null;
	}
    }

    public static PlayerTradePlayer getTradingPlayer(Player player) {
	return getTradingPlayer(player.getName());
    }

    public static PlayerTradePlayer getTradingPlayer(String name) {
	for (PlayerTradePlayer player : players)
	    if (player.entityPlayer.name.equalsIgnoreCase(name))
		return player;
	return null;
    }

    public void openTradeScreen(PlayerTradeChest tradeChestScreen) {
	this.entityPlayer.openContainer(tradeChestScreen);
    }

    public boolean isRequested() {
	return this.requested != null;
    }

    public PlayerTradePlayer getRequestedTarget() {
	return requested;
    }
    
    public PlayerTradePlayer getRequester() {
	if(this.isRequested())
	    return this.requester;
	return null;
    }

    public void sendMessage(String string) {
	this.entityPlayer.sendMessage(string);

    }

    public String getName() {
	return entityPlayer.getName();
    }

}
