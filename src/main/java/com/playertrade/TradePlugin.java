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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class TradePlugin extends JavaPlugin {
    public static String pluginName = "PlayerTrade";

    public Logger log;
    public TradeConfiguration config;
    public TradeCommand command;
    public TradeListener listener;

    public List<TradeProcessor> activeTrades = new ArrayList<TradeProcessor>();

    @Override
    public void onDisable() {
	config.save();
    }

    @Override
    public void onEnable() {
	info(String.format("%s is enabled.", pluginName));

	config.load();
	listener.register();

	getCommand("playertrade").setExecutor(command);
    }

    @Override
    public void onLoad() {

	log      = this.getLogger();
	config   = new TradeConfiguration(this);
	command  = new TradeCommand(this);
	listener = new TradeListener(this);

    }

    public boolean hasPermission(CommandSender sender, String perm) {
	return sender.hasPermission(perm);
    }

    public void info(String msg) {
	log.info(msg);
    }

    public void warn(String msg) {
	log.warning(msg);
    }

}
