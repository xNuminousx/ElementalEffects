package com.xnuminousx.elementaleffects.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.GUIs.IndGui;
import com.xnuminousx.elementaleffects.GUIs.TrailGui;

public class Commands implements CommandExecutor {

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
						TrailGui.openGUI(p);
						return true;
					} else if (args[0].equalsIgnoreCase("ind") || args[0].equalsIgnoreCase("indicaotrs") || args[0].equalsIgnoreCase("indicator")) {
						IndGui.openGui(p);
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

}
