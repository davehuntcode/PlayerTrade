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

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class TradeConfiguration extends YamlConfiguration {
    public File config;
    public TradePlugin plugin;

    public boolean debug; 
    public boolean logEachTrade; 
    public double maxTradeRadius;
    public int tradeRequestTimeout; 
    public int tradeThreshold;

    public TradeConfiguration(TradePlugin plugin) {
	this.plugin = plugin;
	this.config = new File(plugin.getDataFolder(), "config.yml");
    }

    public void save() {
	try {
	    super.save(config);
	} catch (Exception e) {
	    plugin.warn("Unable to save configuration, using defaults instead.");
	}

	set("playertrade.debug", debug);
	set("playertrade.logtrades", logEachTrade);
	set("playertrade.maxtraderadius", maxTradeRadius);
	set("playertrade.requesttimeout", tradeRequestTimeout);
	set("playertrade.threshold", tradeThreshold);
    }

    public void load() {
	try {
	    super.load(config);
	} catch (Exception e) {
	    plugin.warn("Unable to load configuration, using defaults instead.");
	}

	debug = getBoolean("playertrade.debug", true);
	logEachTrade = getBoolean("playertrade.logtrades", true);
	maxTradeRadius = getDouble("playertrade.maxtraderadius", 10);
	tradeRequestTimeout = getInt("playertrade.requesttimeout", 10);
	tradeThreshold = getInt("playertrade.threshold", 5);

	save();

    }
}
