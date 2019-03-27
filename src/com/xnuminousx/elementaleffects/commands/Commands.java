package com.xnuminousx.elementaleffects.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.gui.TrailGui;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Trail;

public class Commands implements CommandExecutor {

	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		String title = ChatColor.DARK_PURPLE + "" + "" + ChatColor.UNDERLINE + "" + ChatColor.BOLD + "ElementalEffects";
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (lable.equalsIgnoreCase("elementaleffects") || lable.equalsIgnoreCase("ee")) {
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

				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee disable");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee remove");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Removes your active trail.");
				return true;
			} else if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = (Player)sender;
					if (args[0].equalsIgnoreCase("trails") || args[0].equalsIgnoreCase("trail") || args[0].equalsIgnoreCase("effects")) {
						TrailGui.openGUI(player);
						return true;
					} else if (args[0].equalsIgnoreCase("ind") || args[0].equalsIgnoreCase("indicators") || args[0].equalsIgnoreCase("indicator")) {
						IndGui.openGui(player);
						return true;
					} else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("remove")) {
						Trail.removeTrail((Player)sender);
						Indicator.removeIndicator((Player)sender);
						sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active trail" + ChatColor.RESET + ChatColor.RED + " disabled!");
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Unknown command! Try: " + ChatColor.YELLOW + "/ee");
						return true;
					}
				} else {
					sender.sendMessage("You're not a player!");
					return false;
				}
			/*} else if (args.length == 2) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("enable")) {
						for (Trails type : Main.plugin.types) {
							if (type.toString().equalsIgnoreCase(args[1])) {
								Main.plugin.trails.put(player, new Trail(type));
								player.sendMessage(this.enabled(ChatColor.AQUA, type.toString()));
							} else {
								sender.sendMessage(ChatColor.RED + "Unknown trail name!");
							}
							return true;
						}
						return true;
					}
				}*/
			}
			return false;
		}
		return false;
	}
	public String enabled(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}

}
