package com.playertrade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TradePlugin extends JavaPlugin {

    public static final String pluginName = "PlayerTrade";

    public Logger log;
    public TradeConfiguration config;
    public TradePlayerListener tradePlayerListener;
    public TradeScreenListener tradeScreenListener;

    public List<TradeProcessor> activeTrades = new ArrayList<TradeProcessor>();

    public void onEnable() {
	log = this.getLogger();
	config = new TradeConfiguration(this);
	tradePlayerListener = new TradePlayerListener(this);
	tradeScreenListener = new TradeScreenListener(this);

	config.load();
	tradePlayerListener.register();
	tradeScreenListener.register();

	getCommand("trade").setExecutor(this);
    }

    public void onDisable() {
	config.save();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label,
	    String[] args) {

	if (!(sender instanceof Player)) {
	    sender.sendMessage("You can not manage trades from the console!");
	    return false;
	}

	if (args.length <= 1) {
	    showUsage(sender);
	    return false;
	}

	if (cmd.getName().equalsIgnoreCase("trade")) {
	    try {
		Player reqr = (Player) sender;
		String command = args[0];
		String targetPlayerName = args[1];
		Player reqd = getServer().getPlayer(targetPlayerName);

		if ((command.equalsIgnoreCase("request") || command.equals("r"))) {
		    if (reqd != null && reqr != null
			    && reqr.hasPermission("tradeplugin.perm.trade") && reqd.hasPermission("tradeplugin.perm.trade")) {

			TradePlayer requester = new TradePlayer(this, reqr);
			TradePlayer requested = new TradePlayer(this, reqd);

			if (requester.hasPermission("tradeplugin.perm.trade")
				&& requested
					.hasPermission("tradeplugin.perm.trade")) {

			    TradeProcessor proc = new TradeProcessor(this,
				    requester, requested);
			    proc.initiateTrade();
			}
		    }
		} else if ((command.equalsIgnoreCase("accept") || command
			.equals("a"))) {

		} else if ((command.equalsIgnoreCase("deny") || command
			.equals("d"))) {

		} else {
		    showUsage(sender);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}

	return false;
    }

    public boolean hasPermission(String name, String perm) {
	return ((CommandSender) getServer().getPlayer(name))
		.hasPermission(perm);
    }

    public void showUsage(CommandSender sender) {
	sender.sendMessage(ChatColor.LIGHT_PURPLE
		+ String.format("%s v%s usage screen:", this.getDescription()
			.getName(), this.getDescription().getVersion()));
	sender.sendMessage(ChatColor.LIGHT_PURPLE
		+ String.format("Authors: %s", this.getDescription()
			.getAuthors()));
	sender.sendMessage(ChatColor.RED + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	sender.sendMessage(ChatColor.GREEN
		+ "/trade r | request <name> - request a trade with another player (requires permission)");
	sender.sendMessage(ChatColor.GREEN
		+ "/trade a | accept <name>  - accept  a trade from another player (requires permission)");
	sender.sendMessage(ChatColor.GREEN
		+ "/trade d | deny <name>    - deny    a trade from another player (requires permission");
	sender.sendMessage(ChatColor.RED + "____________________________________________________");

    }

    public void info(String msg) {
	log.info(msg);
    }

    public void warn(String msg) {
	log.warning(msg);
    }

}
