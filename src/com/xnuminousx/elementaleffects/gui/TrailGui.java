package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;

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
		
		ArrayList<String> hasPerm = new ArrayList<String>();
		ArrayList<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "No permission!");
		
		ItemStack testItem = new ItemStack(Material.APPLE);
		ItemMeta testMeta = testItem.getItemMeta();
		testMeta.setDisplayName("Test");
		testItem.setItemMeta(testMeta);
		inv.setItem(0, testItem);
			
		ItemStack indGui = new ItemStack(Material.END_CRYSTAL);
		ItemStack earthItem = new ItemStack(Material.GRASS);
		ItemStack waterItem = new ItemStack(Material.WATER_BUCKET);
		ItemStack fireItem = new ItemStack(Material.BLAZE_POWDER);
		ItemStack airItem = new ItemStack(Material.STRING);
		ItemStack chiItem = new ItemStack(Material.LEASH);
		ItemStack avatarItem = new ItemStack(Material.NETHER_STAR);
		
		ItemMeta indMeta = indGui.getItemMeta();
		ItemMeta earthMeta = earthItem.getItemMeta();
		ItemMeta waterMeta = waterItem.getItemMeta();
		ItemMeta fireMeta = fireItem.getItemMeta();
		ItemMeta airMeta = airItem.getItemMeta();
		ItemMeta chiMeta = chiItem.getItemMeta();
		ItemMeta avatarMeta = avatarItem.getItemMeta();
		
		indMeta.setDisplayName(ChatColor.DARK_AQUA + "Open Indicator GUI");
		earthMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth Trail");
		if (p.hasPermission("elementaleffects.earth") || p.hasPermission("elementaleffects.*")) {
			earthMeta.setLore(hasPerm);
		} else {
			earthMeta.setLore(noPerm);
		}
		
		waterMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Water Trail");
		if (p.hasPermission("elementaleffects.water") || p.hasPermission("elementaleffects.*")) {
			waterMeta.setLore(hasPerm);
		} else {
			waterMeta.setLore(noPerm);
		}
		
		fireMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Trail");
		if (p.hasPermission("elementaleffects.fire") || p.hasPermission("elementaleffects.*")) {
			fireMeta.setLore(hasPerm);
		} else {
			fireMeta.setLore(noPerm);
		}
		
		airMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Air Trail");
		if (p.hasPermission("elementaleffects.air") || p.hasPermission("elementaleffects.*")) {
			airMeta.setLore(hasPerm);
		} else {
			airMeta.setLore(noPerm);
		}
		
		chiMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Chi Trail");
		if (p.hasPermission("elementaleffects.chi") || p.hasPermission("elementaleffects.*")) {
			chiMeta.setLore(hasPerm);
		} else {
			chiMeta.setLore(noPerm);
		}
		
		avatarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Avatar Trail");
		if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
			avatarMeta.setLore(hasPerm);
		} else {
			avatarMeta.setLore(noPerm);
		}
		
		indGui.setItemMeta(indMeta);
		earthItem.setItemMeta(earthMeta);
		waterItem.setItemMeta(waterMeta);
		fireItem.setItemMeta(fireMeta);
		airItem.setItemMeta(airMeta);
		chiItem.setItemMeta(chiMeta);
		avatarItem.setItemMeta(avatarMeta);
		
		inv.setItem(40, indGui);
		inv.setItem(10, earthItem);
		inv.setItem(12, waterItem);
		inv.setItem(14, fireItem);
		inv.setItem(16, airItem);
		inv.setItem(30, chiItem);
		inv.setItem(32, avatarItem);
		
		p.openInventory(inv);
	}

}
