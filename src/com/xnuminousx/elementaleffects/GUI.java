package com.xnuminousx.elementaleffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("etrails")) {
			gui(p);
			return true;
		}
		return false;
	}
	
	public void gui(Player p) {
		Inventory inv = Bukkit.createInventory(p, 27, "ElementalEffects");
		
		ItemStack earthItem = new ItemStack(Material.GRASS);
		ItemStack waterItem = new ItemStack(Material.WATER_BUCKET);
		ItemStack fireItem = new ItemStack(Material.BLAZE_POWDER);
		ItemStack airItem = new ItemStack(Material.STRING);
		
		ItemMeta earthMeta = earthItem.getItemMeta();
		ItemMeta waterMeta = waterItem.getItemMeta();
		ItemMeta fireMeta = fireItem.getItemMeta();
		ItemMeta airMeta = airItem.getItemMeta();
		
		earthMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "EarthTrail");
		waterMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "WaterTrail");
		fireMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "FireTrail");
		airMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "AirTrail");
		
		earthItem.setItemMeta(earthMeta);
		waterItem.setItemMeta(waterMeta);
		fireItem.setItemMeta(fireMeta);
		airItem.setItemMeta(airMeta);
		
		inv.setItem(10, earthItem);
		inv.setItem(12, waterItem);
		inv.setItem(14, fireItem);
		inv.setItem(16, airItem);
		
		p.openInventory(inv);
	}

}
