/* Copyright (c) 2012 Dave Hunt <thedavidhunt@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

package com.playertrade;

import net.minecraft.server.EntityPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerTradeCommand implements CommandExecutor {

    public PlayerTradePlugin plugin;

    public PlayerTradeCommand(PlayerTradePlugin plugin) {
	this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
	    String label, String[] args) {
	if ((args.length <= 1) && (sender instanceof Player)) {
	    sendUsage(sender);
	    return false;
	}

	plugin.info(String.format("Sender: %s Command: %s", sender.getName(), args[0]));

	if ((sender instanceof Player)) {
	    if (args[0].equalsIgnoreCase("request"))
		requestTrade(sender, args);
	    if (args[0].equalsIgnoreCase("accept"))
		acceptTrade(sender, args);
	    if (args[0].equalsIgnoreCase("deny"))
		denyTrade(sender, args);
	} else
	    plugin.warn("Trades can not be initiated via the console");
	return false;
    }

    public void acceptTrade(CommandSender sender, String[] args) {

	if (plugin.hasPermission(sender, "playertrade.accept")) {

	    PlayerTradePlayer requester = PlayerTradePlayer.getTradingPlayer((Player) sender);
	    
	    if ((requester != null) && requester.isRequested()) {
		PlayerTradePlayer requested = requester.getRequester();
		requested.sendMessage("You have accepted " + sender.getName()+ "'s trade request.");
		requester.sendMessage(requested.getName()  + " has accepted your trade request.");
		requested.acceptRequest(requester);
	    } else
		requester.sendMessage("You must request a trade with the other player!");

	} else
	    sender.sendMessage(ChatColor.RED   + "You do not have the permission to accept a trade");
    }

    public void denyTrade(CommandSender sender, String[] args) {

    }

    public void requestTrade(CommandSender sender, String[] args) {
	Player requester = (Player) sender;

	if (args.length < 2)
	    sender.sendMessage(ChatColor.RED + "Usage: /pt request <name>");

	if (plugin.hasPermission(sender, "playertrade.request")) {

	    Player acceptor = plugin.getServer().getPlayer(args[1]);

	    EntityPlayer p1 = ((CraftPlayer) requester).getHandle();
	    EntityPlayer p2 = ((CraftPlayer) acceptor).getHandle();

	    if (p2 == p1) {
		sender.sendMessage(ChatColor.RED + "You can not trade yourself!");
		return;
	    }

	    if (p2 != null) {

		if (plugin.withinDistance(requester.getLocation(),
			acceptor.getLocation(), plugin.config.maxTradeRadius)) {

		    PlayerTradePlayer requestor = new PlayerTradePlayer(p1, this.plugin);
		    PlayerTradePlayer requested = new PlayerTradePlayer(p2, this.plugin);

		    if (requestor.onTradeRequest(requested)) {
			acceptor.sendMessage(String.format(ChatColor.BLUE+ "[%s] "+ ChatColor.GREEN + "wants to trade with you!", requester.getDisplayName()));
		    }

		} else
		    sender.sendMessage(ChatColor.RED  + "You are too far away from your target. You must be within "  + plugin.config.maxTradeRadius + " blocks!");

	    } else
		sender.sendMessage(ChatColor.RED + "Could not find a player named " + args[1]);

	} else
	    sender.sendMessage(ChatColor.RED   + "You do not have the permission to request a trade");
    }

    public void sendUsage(CommandSender sender) {
	sender.sendMessage(ChatColor.GREEN + "Player Trade Help:");
	sender.sendMessage("PlayerTrade " + plugin.getDescription().getVersion() + " by: " + plugin.getDescription().getAuthors());
	sender.sendMessage(ChatColor.AQUA + "________________________________________");
	sender.sendMessage("General information: /pt or /playertrade");
	sender.sendMessage("Request a trade: /pt request <name>");
	sender.sendMessage("Accept a trade:  /pt accept <name> or /pt accept");
    }

}
