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
		
		Inventory inv = Bukkit.createInventory(p, 9, guiName);
		
		ItemStack hitItem = new ItemStack(Material.REDSTONE);
		
		ItemMeta hitMeta = hitItem.getItemMeta();
		
		hitMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Hit Indicator");
		
		hitItem.setItemMeta(hitMeta);
		
		inv.setItem(1, hitItem);
		
		p.openInventory(inv);
	}
}
