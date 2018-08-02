package com.xnuminousx.elementaleffects.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Methods {
	
	public static ItemStack createItem(Player p, Material item, String name, ChatColor color, String permissionType) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		List<String> hasPerm = new ArrayList<String>();
		List<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + " / " + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "No permission!");
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		if (p.hasPermission("elementaleffects." + permissionType) || p.hasPermission("elementaleffects.*")) {
			itemMeta.setLore(hasPerm);
		} else {
			itemMeta.setLore(noPerm);
		}
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	
	public static ItemStack miscItem(Material item, String name, ChatColor color, List<String> description) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		itemMeta.setLore(description);
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
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