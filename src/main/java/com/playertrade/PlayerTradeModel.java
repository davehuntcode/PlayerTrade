package com.playertrade;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class PlayerTradeModel {
    public PlayerTradePlayer requester;
    public PlayerTradePlayer requested;
    public ArrayList<ItemStack> requesterItems = new ArrayList<ItemStack>();

    // private Inventory tradeInv;
    private PlayerTradeChest tradeChestScreen;
    private PlayerTradePlugin plugin;

    public PlayerTradeModel(PlayerTradePlayer p1, PlayerTradePlayer p2,    PlayerTradePlugin plugin) {
	this.plugin = plugin;
	this.requester = p1;
	this.requested = p2;

	this.plugin.activeTrades.add(this);
    }

    public void doTrade() {
	this.tradeChestScreen = new PlayerTradeChest(requester);
	this.requester.openTradeScreen(tradeChestScreen);
	this.requested.openTradeScreen(tradeChestScreen);
	
    }

}
