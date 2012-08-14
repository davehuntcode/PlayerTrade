package com.playertrade;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TradePlugin extends JavaPlugin {

    public static final String pluginName = "PlayerTrade";

    public Logger log;
    public List<TradeProcessor> activeTrades = new ArrayList<TradeProcessor>();

    public void onEnable() {
	log = this.getLogger();
    }

    public void onDisable() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label,
	    String[] args) {

	if (!(sender instanceof Player)) {
	    sender.sendMessage("You can not manage trades from the console!");
	    return false;
	}

	if (args.length < 2 && !args[0].equalsIgnoreCase("accept")) {
	    showUsage(sender);
	    return false;
	}

	if (cmd.getName().equalsIgnoreCase("trade")) {
	    Player reqr = (Player) sender;
	    String command = args[0];
	    String targetPlayerName = args[1];
	    Player reqd = getServer().getPlayer(targetPlayerName);

	    if ((command.equalsIgnoreCase("request") || command.equals("r"))) {
		if (reqr.hasPermission("tradeplugin.perm.trade")
			&& reqd.hasPermission("tradeplugin.perm.trade")
			&& reqd != null && reqr != null) {

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
	    } else if((command.equalsIgnoreCase("accept") || command.equals("a"))) {
		
	    }else if((command.equalsIgnoreCase("deny") || command.equals("d"))) {
		
	    } else {
		 showUsage(sender);
	    }

	}

	return false;
    }

    public boolean hasPermission(String name, String perm) {
	return ((CommandSender) getServer().getPlayer(name))
		.hasPermission(perm);
    }

    public void showUsage(CommandSender sender) {

    }

    public void info(String msg) {
	log.info(msg);
    }

    public void warn(String msg) {
	log.warning(msg);
    }

}
