package com.xnuminousx.elementaleffects.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.xnuminousx.elementaleffects.config.Manager;

public class IndGui {
	
	public static void openGui(Player p) {
		String guiName = Manager.getIndicatorGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 27, guiName);
		
		ItemStack trailGui = new ItemStack(Material.END_CRYSTAL);
		ItemStack hitItem = new ItemStack(Material.REDSTONE);
		
		ItemMeta trailMeta = trailGui.getItemMeta();
		ItemMeta hitMeta = hitItem.getItemMeta();
		
		trailMeta.setDisplayName(ChatColor.DARK_AQUA + "Open Trail GUI");
		hitMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Hit Indicator");
		
		trailGui.setItemMeta(trailMeta);
		hitItem.setItemMeta(hitMeta);
		
		inv.setItem(22, trailGui);
		inv.setItem(1, hitItem);
		
		p.openInventory(inv);
	}
}
