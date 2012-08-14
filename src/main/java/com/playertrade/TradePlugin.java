package com.playertrade;

import java.util.*;
import java.util.logging.Logger;

import org.bukkit.command.*;

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

    
    public boolean onCommand(CommandSender sender, Command command,  String label, String[] args) {
	return super.onCommand(sender, command, label, args);
    }

    public boolean hasPermission(String name, String perm) {
	return ((CommandSender) getServer().getPlayer(name)).hasPermission(perm);
    }

    public void info(String msg) {
	log.info(msg);
    }

    public void warn(String msg) {
	log.warning(msg);
    }

}
