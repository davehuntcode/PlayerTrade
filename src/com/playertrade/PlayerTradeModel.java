package com.playertrade;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class PlayerTradeModel {
	public PlayerTradePlayer initiater;
	public PlayerTradePlayer responder;
	public ArrayList<ItemStack> requesterItems = new ArrayList<ItemStack>();

	// private Inventory tradeInv;
	private PlayerTradeChest tradeChestScreen;
	private PlayerTradePlugin plugin;

	public PlayerTradeModel(PlayerTradePlayer p1, PlayerTradePlayer p2,
			PlayerTradePlugin plugin) {
		this.plugin = plugin;
		this.initiater = p1;
		this.responder = p2;

		this.plugin.activeTrades.add(this);
	}

	public void doTrade() {
		this.tradeChestScreen = new PlayerTradeChest(initiater);
		this.initiater.openTradeScreen(tradeChestScreen);
		this.responder.openTradeScreen(tradeChestScreen);
	}

}
