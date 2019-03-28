package com.xnuminousx.elementaleffects.commands;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.gui.TrailGui;
import com.xnuminousx.elementaleffects.trails.AeroSphere;
import com.xnuminousx.elementaleffects.trails.Blood;
import com.xnuminousx.elementaleffects.trails.Cloud;
import com.xnuminousx.elementaleffects.trails.ElementalRings;
import com.xnuminousx.elementaleffects.trails.FlameArms;
import com.xnuminousx.elementaleffects.trails.Float;
import com.xnuminousx.elementaleffects.trails.LavaTrail;
import com.xnuminousx.elementaleffects.trails.SandCloak;
import com.xnuminousx.elementaleffects.trails.StaticField;
import com.xnuminousx.elementaleffects.trails.WaterRings;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

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
		List<Trails> types = new ArrayList<Trails>();
		for (Trails trail : Trails.values()) {
			types.add(trail);
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
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee list");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "none");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Shows a list of trails.");
				
				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee enable");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee set");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Activates a trail without the GUI.");
				
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
					} else if (args[0].equalsIgnoreCase("list")) {
						String header = ChatColor.DARK_PURPLE + "" + "" + ChatColor.BOLD + types.size() + " Active Trails";
						sender.sendMessage(ChatColor.DARK_AQUA + "--= " + header + ChatColor.DARK_AQUA + " =--");
						for (Trails trail : types) {
							int bullet = types.indexOf(trail) + 1;
							String list = trail.toString().substring(0, 1).toUpperCase() + trail.toString().substring(1).toLowerCase();
							if (Methods.hasPermission(player, trail.toString().toLowerCase())) {
								sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.YELLOW + list);
							} else {
								sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.RED + list + " " + 
										ChatColor.DARK_GRAY + "(" + ChatColor.DARK_RED + "No permission" + ChatColor.DARK_GRAY + ")");
							}
						}
						return true;
					} else {
						sender.sendMessage(ChatColor.RED + "Unknown command! Try: " + ChatColor.YELLOW + "/ee");
						return true;
					}
				} else {
					sender.sendMessage("You're not a player!");
					return false;
				}
			} else if (args.length == 2) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("enable")) {
						if (args[1].isEmpty()) {
							sender.sendMessage("Please specify an effect name.");
							return true;
						} else {
							for (Trails trail : types) {
								if (trail.toString().equalsIgnoreCase(args[1])) {
									if (Methods.hasPermission(player, trail.toString().toLowerCase())) {
										sender.sendMessage(this.enabled(ChatColor.AQUA, trail.toString()));
										Trail.setTrail(player, trail);
										activateAnimation(player, trail);
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have the necessary permissions for that!");
									}
								}
							}
							return true;
						}
					}
				}
			}
			return false;
		}
		return false;
	}
	public String enabled(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}
	public void activateAnimation(Player player, Trails trail) {
		if (trail.equals(Trails.SANDCLOAK)) {
			new SandCloak(player);
		} else if (trail.equals(Trails.ERUPTION)) {
			new LavaTrail(player);
			
		} else if (trail.equals(Trails.AIR)) {
			new Cloud(player);
		} else if (trail.equals(Trails.AEROSPHERE)) {
			new AeroSphere(player);
		} else if (trail.equals(Trails.FLOAT)) {
			new Float(player);
			
		} else if (trail.equals(Trails.FLAMEARMS)) {
			new FlameArms(player);
		} else if (trail.equals(Trails.STATICFIELD)) {
			new StaticField(player);
			
		} else if (trail.equals(Trails.WATER)) {
			new WaterRings(player);
		} else if (trail.equals(Trails.BLOOD)) {
			new Blood(player);
		
		} else if (trail.equals(Trails.ELEMENTALRINGS)) {
			new ElementalRings(player);
		} else {
			return;
		}
	}

}
