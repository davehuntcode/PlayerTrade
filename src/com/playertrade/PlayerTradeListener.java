package com.playertrade;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerTradeListener implements Listener {

	public PlayerTradePlugin plugin;

	public PlayerTradeListener(PlayerTradePlugin plugin) {
		this.plugin = plugin;
	}

	public void register() {
		PluginManager manager;
		manager = plugin.getServer().getPluginManager();
		manager.registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

		Player initiater = event.getPlayer();
		Entity target = event.getRightClicked();

		PlayerTradePlayer sender = PlayerTradePlayer.getPlayerByName(initiater
				.getName());

		if ((sender != null) && sender.isRequested()) {
			sender.tradeCount++;

			PlayerTradePlayer other = PlayerTradePlayer.getPlayerByName(sender
					.getName());
			other.sendMessage("You have accepted " + sender.getName()
					+ "'s trade request.");
			initiater.sendMessage(other.getName()
					+ " has accepted your trade request.");

			sender.acceptRequest(other);
		} else if ((target != null) && (initiater != null))
			plugin.command.requestTrade(initiater, new String[] { "request",
					((CraftPlayer) target).getDisplayName() });

	}

}
