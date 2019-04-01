package com.xnuminousx.elementaleffects.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Methods {
	
	public static ItemStack createItem(Player p, Material item, String name, ChatColor color, Trails type) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		List<String> hasPerm = new ArrayList<String>();
		List<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + " / " + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "No permission!");
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		if (hasPermission(p, "trails", type.toString())) {
			itemMeta.setLore(hasPerm);
		} else {
			itemMeta.setLore(noPerm);
		}
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	public static ItemStack createItem(Player p, Material item, String name, ChatColor color, Indicators type) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		List<String> hasPerm = new ArrayList<String>();
		List<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + " / " + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "No permission!");
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		if (hasPermission(p, "indicators", type.toString())) {
			itemMeta.setLore(hasPerm);
		} else {
			itemMeta.setLore(noPerm);
		}
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	
	public static boolean hasPermission(Player player, String type, String suffix) {
		if (player.hasPermission("elementaleffects." + type.toLowerCase() + "." + suffix.toLowerCase()) || 
				player.hasPermission("elementaleffects." + type.toLowerCase() + ".*") ||
				player.isOp()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ItemStack miscItem(Material item, String name, ChatColor color, List<String> description) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		itemMeta.setLore(description);
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	
	public static String normalizeString(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
	
	public static void playColoredParticle(Location location, int amount, double x, double y, double z, int r, int g, int b) {
		Color color = Color.fromRGB(r, g, b);
		DustOptions dustOptions = new DustOptions(color, 1);
		ParticleEffect.REDSTONE.display(location, amount, x, y, z, dustOptions);
	}
	
	public static Vector rotateAroundAxisX(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = v.getY() * cos - v.getZ() * sin;
		double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	public static Vector rotateAroundAxisY(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}
}