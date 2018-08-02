package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;

public class IndGui {
	
	public static void openGui(Player p) {
		String guiName = Manager.getIndicatorGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 27, guiName);
		
		List<String> openTrail = new ArrayList<String>();
		openTrail.add("Use this to open the trail GUI");
		
		inv.setItem(22, Methods.miscItem(Material.END_CRYSTAL, "Open Trail GUI", ChatColor.DARK_AQUA, openTrail));
		inv.setItem(1, Methods.createItem(p, Material.REDSTONE, "Hit Indicator", ChatColor.DARK_RED, "hit"));
		inv.setItem(3, Methods.createItem(p, Material.GHAST_TEAR, "Avatar State Indicator", ChatColor.DARK_PURPLE, "avatarstate"));
		
		p.openInventory(inv);
	}
}