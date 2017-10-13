package com.xnuminousx.elementaleffects.commands;

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

import com.xnuminousx.elementaleffects.config.Manager;

public class GUICommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		String title = ChatColor.DARK_PURPLE + "" + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "ElementalEffects";
		
		if (lable.equalsIgnoreCase("elementaleffects") || lable.equalsIgnoreCase("ee") || lable.equalsIgnoreCase("e")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.DARK_AQUA + "--= " + title + ChatColor.DARK_AQUA + " =--");
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/elementaleffects");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Shows list of commands");
				
				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee trails");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee trail, /ee effects");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Opens the trail GUI.");

				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee indicators");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee indicator, /ee ind");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Opens the indicators GUI.");
				return true;
			} else if (args.length == 1) {
				if (sender instanceof Player) {
					Player p = (Player)sender;
					if (args[0].equalsIgnoreCase("trails") || args[0].equalsIgnoreCase("trail") || args[0].equalsIgnoreCase("effects")) {
						trailGui(p);
						return true;
					} else if (args[0].equalsIgnoreCase("ind") || args[0].equalsIgnoreCase("indicaotrs") || args[0].equalsIgnoreCase("indicator")) {
						indicatorGui(p);
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Command not known! Try: " + ChatColor.YELLOW + "/ee trails");
						return true;
					}
				} else {
					sender.sendMessage("You're not a player!");
					return false;
				}
			}
			return false;
		}
		return false;
	}
	
	public void trailGui(Player p) {
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
	
	public void indicatorGui(Player p) {
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
