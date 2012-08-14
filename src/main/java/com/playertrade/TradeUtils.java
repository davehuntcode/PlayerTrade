package com.playertrade;

import org.bukkit.Location;

public class TradeUtils {
    public TradePlugin plugin;

    public TradeUtils(TradePlugin plugin) {
	this.plugin = plugin;
    }

    public boolean withinDistance(Location p1, Location p2, double r) {
  	if (r <= 0)
  	    return true; // No limit, negative limit defaults to no
  			 // limit

  	int x = (int) (p2.getX() - p1.getX());
  	int y = (int) (p2.getY() - p1.getY());
  	int z = (int) (p2.getZ() - p1.getZ());

  	double d = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

  	return d < r;
      }
}
