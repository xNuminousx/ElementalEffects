package com.xnuminousx.elementaleffects.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Methods {
	
	/**
	 * Creates an inventory item with a custom lore message.
	 * @param item The icon that will appear in the inventory
	 * @param name The name of the item in the inventory
	 * @param color The chat color of the name
	 * @param description The lore message
	 * @return An item with a custom name and lore.
	 */
	public static ItemStack createItem(Material item, String name, ChatColor color, List<String> description) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		itemMeta.setLore(description);
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	/**
	 * Creates an inventory item checking for a trails permission.
	 * @param p The player being checked for permissions
	 * @param item The icon that will appear in the inventory
	 * @param name The name of the item in the inventory
	 * @param color The chat color of the name
	 * @param type The type of trail permission being checked
	 * @return An item with a custom name and permission based lore.
	 */
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
	/**
	 * Creates an inventory item checking for an indicators permission.
	 * @param p The player being checked for permissions
	 * @param item The icon that will appear in the GUI
	 * @param name The name of the item in the GUI
	 * @param color The chat color of the name
	 * @param type The type of indicator permission being checked
	 * @return An item with a custom name and permission based lore.
	 */
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
	
	/**
	 * Checks to see if the player has any of the ElementalEffects permissions or isOP()
	 * @param player The player being checked
	 * @param type The type of permission being checked
	 * @param suffix The defining variable of what the command is for
	 * @return true if the player has permission
	 */
	public static boolean hasPermission(Player player, String type, String suffix) {
		if (player.hasPermission("elementaleffects." + type.toLowerCase() + "." + suffix.toLowerCase()) || 
				player.hasPermission("elementaleffects." + type.toLowerCase() + ".*") ||
				player.isOp()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Used to turn any odd strings into normal strings where the first character is upper case
	 * and the following characters are lower case. Mostly used here to turn upper case enum
	 * values into normal words.
	 * @param input The string being manipulated.
	 * @return A new normalized string.
	 */
	public static String normalizeString(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
	}
	
	/**
	 * Plays a redstone particle with a custom color based on RGB values.
	 * @param location The location at which the particle will spawn
	 * @param amount The amount of particles to spawn per call of this method
	 * @param x The offset of the x-axis
	 * @param y The offset of the y-axis
	 * @param z The offset of the z-axis
	 * @param r The red value
	 * @param g The green value
	 * @param b The blue value
	 */
	public static void playColoredParticle(Player player, Location location, int amount, double x, double y, double z, int r, int g, int b) {
		Color color = Color.fromRGB(r, g, b);
		DustOptions dustOptions = new DustOptions(color, 1);
		player.getWorld().spawnParticle(Particle.REDSTONE, location, amount, x, y, z, dustOptions);
	}
	
	/**
	 * Used to rotate a vector around the x-axis by a certain angle
	 * @param v The vector being manipulated
	 * @param angle The angle at which to rotate
	 * @return New vector
	 */
	public static Vector rotateAroundAxisX(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = v.getY() * cos - v.getZ() * sin;
		double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	/**
	 * Used to rotate a vector around the y-axis by a certain angle
	 * @param v The vector being manipulated
	 * @param angle The angle at which to rotate
	 * @return New vector
	 */
	public static Vector rotateAroundAxisY(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}
}