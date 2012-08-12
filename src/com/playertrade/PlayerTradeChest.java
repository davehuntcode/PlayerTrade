package com.playertrade;

import net.minecraft.server.InventoryLargeChest;
import net.minecraft.server.TileEntityChest;

import org.bukkit.ChatColor;

public class PlayerTradeChest extends InventoryLargeChest {

    public PlayerTradeChest() {
	super("PlayerTrade Screen", new TileEntityChest(),
		new TileEntityChest());
    }

    public PlayerTradeChest(PlayerTradePlayer initiater) {
	super(ChatColor.RED + "Trade Window ", new TileEntityChest(),
		new TileEntityChest());
    }

}
