package com.playertrade;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerTradeLogger {
    public PlayerTradePlugin plugin;

    public static final String TRADE_LOG_FORMAT = "[Trade] - uid: %s  Time: %s  Sender: %s Items: %s";

    public PlayerTradeLogger(PlayerTradePlugin plugin) {
	this.plugin = plugin;
    }

    public void storeTrade(PlayerTradeModel trade) {
	File tradeLog = new File(plugin.getDataFolder(), "trades.log");

	try {

	    FileWriter writer = new FileWriter(tradeLog);

	    // Close writer
	    writer.close();

	} catch (IOException e) {
	    plugin.warn(e.getLocalizedMessage());
	}
    }

}
