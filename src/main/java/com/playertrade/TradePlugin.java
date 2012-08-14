package com.playertrade;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class TradePlugin extends JavaPlugin {
    
    public static final String pluginName = "PlayerTrade";


    @Override
    public void onDisable() {
	super.onDisable();
    }

    @Override
    public void onEnable() {
	super.onEnable();
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	return super.onCommand(sender, command, label, args);
    }

    

}
