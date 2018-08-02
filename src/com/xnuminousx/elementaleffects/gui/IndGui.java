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
import com.xnuminousx.elementaleffects.utils.Names;

public class IndGui {
	
	public static void openGui(Player p) {
		String guiName = Manager.getIndicatorGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 54, guiName);
		
		List<String> openTrail = new ArrayList<String>();
		List<String> removeInd = new ArrayList<String>();
		openTrail.add("Use this to open the trail GUI");
		removeInd.add("Click here to remove your active indicator");
		
		
		inv.setItem(13, Methods.miscItem(Material.END_CRYSTAL, "Open Trail GUI", ChatColor.DARK_AQUA, openTrail));
		inv.setItem(31, Methods.miscItem(Material.BARRIER, "Disable Indicator", ChatColor.DARK_RED, removeInd));
		inv.setItem(11, Methods.createItem(p, Material.REDSTONE, Names.hitIndicator(), ChatColor.DARK_RED, "hit"));
		inv.setItem(15, Methods.createItem(p, Material.GHAST_TEAR, Names.avatarIndicator(), ChatColor.DARK_PURPLE, "avatarstate"));
		
		p.openInventory(inv);
	}
}