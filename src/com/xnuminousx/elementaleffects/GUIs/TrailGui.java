package com.xnuminousx.elementaleffects.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.xnuminousx.elementaleffects.config.Manager;

public class TrailGui {
	
	public static void openGUI(Player p) {
		String guiName = Manager.getTrailGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 45, guiName);
		
		ItemStack earthItem = new ItemStack(Material.GRASS);
		ItemStack waterItem = new ItemStack(Material.WATER_BUCKET);
		ItemStack fireItem = new ItemStack(Material.BLAZE_POWDER);
		ItemStack airItem = new ItemStack(Material.STRING);
		ItemStack chiItem = new ItemStack(Material.LEASH);
		ItemStack avatarItem = new ItemStack(Material.NETHER_STAR);
		
		ItemMeta earthMeta = earthItem.getItemMeta();
		ItemMeta waterMeta = waterItem.getItemMeta();
		ItemMeta fireMeta = fireItem.getItemMeta();
		ItemMeta airMeta = airItem.getItemMeta();
		ItemMeta chiMeta = chiItem.getItemMeta();
		ItemMeta avatarMeta = avatarItem.getItemMeta();
		
		earthMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth Trail");
		waterMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Water Trail");
		fireMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Trail");
		airMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Air Trail");
		chiMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Chi Trail");
		avatarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Avatar Trail");
		
		earthItem.setItemMeta(earthMeta);
		waterItem.setItemMeta(waterMeta);
		fireItem.setItemMeta(fireMeta);
		airItem.setItemMeta(airMeta);
		chiItem.setItemMeta(chiMeta);
		avatarItem.setItemMeta(avatarMeta);
		
		inv.setItem(10, earthItem);
		inv.setItem(12, waterItem);
		inv.setItem(14, fireItem);
		inv.setItem(16, airItem);
		inv.setItem(30, chiItem);
		inv.setItem(32, avatarItem);
		
		p.openInventory(inv);
	}

}
