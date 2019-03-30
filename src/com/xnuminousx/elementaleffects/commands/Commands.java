package com.xnuminousx.elementaleffects.commands;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.gui.TrailGui;
import com.xnuminousx.elementaleffects.trails.AeroSphere;
import com.xnuminousx.elementaleffects.trails.Blood;
import com.xnuminousx.elementaleffects.trails.Cloud;
import com.xnuminousx.elementaleffects.trails.Combust;
import com.xnuminousx.elementaleffects.trails.ElementalRings;
import com.xnuminousx.elementaleffects.trails.FlameArms;
import com.xnuminousx.elementaleffects.trails.Float;
import com.xnuminousx.elementaleffects.trails.LavaTrail;
import com.xnuminousx.elementaleffects.trails.SandCloak;
import com.xnuminousx.elementaleffects.trails.StaticField;
import com.xnuminousx.elementaleffects.trails.WaterRings;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class Commands implements CommandExecutor {

	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	String headerLeft = ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "   " + ChatColor.RESET + " ";
	String headerRight = " " + ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "   ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		String title = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "ElementalEffects" + ChatColor.RESET;
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		List<Trails> trails = new ArrayList<Trails>();
		for (Trails trail : Trails.values()) {
			trails.add(trail);
		}
		
		List<Indicators> inds = new ArrayList<Indicators>();
		for (Indicators indicator : Indicators.values()) {
			inds.add(indicator);
		}
		
		if (lable.equalsIgnoreCase("elementaleffects") || lable.equalsIgnoreCase("ee")) {
			if (args.length == 0) {
				sender.sendMessage(headerLeft + title + headerRight);
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/elementaleffects");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Shows a list of commands");
				
				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee trails");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee trail, /ee effects");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Opens the trail GUI.");

				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee indicators");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee indicator, /ee ind");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Opens the indicators GUI.");

				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee list [trail/indicator]");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "none");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Shows a list of trails or indicators.");
				
				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee enable [name]");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee set [name]");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Activates a trail or indicator without the GUI.");
				
				sender.sendMessage("");
				
				sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee disable [trail/indicator]");
				sender.sendMessage(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee remove [trail/indicator]");
				sender.sendMessage(" " + ChatColor.YELLOW + "- Removes your active trail or indicator.");
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
					} else if (args[0].equalsIgnoreCase("get")) {
						String header = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + player.getName();
						sender.sendMessage(headerLeft + header + headerRight);
						if (!Main.plugin.trails.containsKey(player)) {
							sender.sendMessage(ChatColor.GREEN + "Active trail: " + ChatColor.YELLOW + "None");
						} else {
							Trails trail = Trail.getTrail(player);
							sender.sendMessage(ChatColor.GREEN + "Active trail: " + ChatColor.YELLOW + Methods.normalizeString(trail.toString()));
						}
						if (!Main.plugin.inds.containsKey(player)) {
							sender.sendMessage(ChatColor.GREEN + "Active indicator: " + ChatColor.YELLOW + "None");
						} else {
							Indicators ind = Indicator.getIndicator(player);
							sender.sendMessage(ChatColor.GREEN + "Active indicator: " + ChatColor.YELLOW + Methods.normalizeString(ind.toString()));
						}
						return true;
					} else if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("set")) {
						sender.sendMessage(prefix + ChatColor.RED + "Please specify an effect name.");
						return true;
					} else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("remove")) {
						sender.sendMessage(prefix + ChatColor.RED + "Please specify whether to disable a trail or indicator.");
						return true;
					} else if (args[0].equalsIgnoreCase("list")) {
						sender.sendMessage(prefix + ChatColor.RED + "Please specify whether to list trails or indicators.");
						return true;
					} else {
						sender.sendMessage(prefix + ChatColor.RED + "Unknown command! Try: " + ChatColor.YELLOW + "/ee");
						return true;
					}
				} else {
					sender.sendMessage("You're not a player!");
					return true;
				}
			} else if (args.length == 2) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("set")) {
						if (args[1].isEmpty() || args[1].equals(null)) {
							sender.sendMessage(prefix + ChatColor.RED + "Please specify an effect name.");
							return true;
						} else {
							for (Trails trail : trails) {
								if (trail.toString().equalsIgnoreCase(args[1])) {
									if (Methods.hasPermission(player, trail.toString())) {
										sender.sendMessage(this.enabled(ChatColor.AQUA, trail.toString()));
										Trail.setTrail(player, trail);
										activateAnimation(player, trail);
									} else {
										sender.sendMessage(prefix + ChatColor.RED + "You do not have the necessary permissions for that!");
									}
								}
							}
							for (Indicators indicator: inds) {
								if (indicator.toString().equalsIgnoreCase(args[1])) {
									if (Methods.hasPermission(player, indicator.toString())) {
										sender.sendMessage(this.enabled(ChatColor.AQUA, indicator.toString()));
										Indicator.setIndicator(player, indicator);
									} else {
										sender.sendMessage(prefix + ChatColor.RED + "You do not have the necessary permissions for that!");
									}
								}
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("remove")) {
						if (args[1].isEmpty() || args[1].equals(null)) {
							sender.sendMessage(prefix + ChatColor.RED + "Please specify whether to disable a trail or indicator.");
							return true;
						} else {
							if (args[1].equalsIgnoreCase("trails") || args[1].equalsIgnoreCase("trail")) {
								Trail.removeTrail((Player)sender);
								sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active trail" + ChatColor.RESET + ChatColor.RED + " disabled!");
								return true;
							} else if (args[1].equalsIgnoreCase("indicators") || args[1].equalsIgnoreCase("indicator")) {
								Indicator.removeIndicator((Player)sender);
								sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active indicator" + ChatColor.RESET + ChatColor.RED + " disabled!");
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("list")) {
						if (args[1].isEmpty() || args[1].equals(null)) {
							sender.sendMessage(prefix + ChatColor.RED + "Please specify whether to list trails or indicators.");
							return true;
						} else {
							if (args[1].equalsIgnoreCase("trails") || args[1].equalsIgnoreCase("trail")) {
								String header = ChatColor.DARK_PURPLE + "" + "" + ChatColor.BOLD + trails.size() + " Active Trails";
								sender.sendMessage(headerLeft + header + headerRight);
								for (Trails trail : trails) {
									int bullet = trails.indexOf(trail) + 1;
									String name = Methods.normalizeString(trail.toString());
									if (Methods.hasPermission(player, trail.toString())) {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.YELLOW + name);
									} else {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.RED + name + " " + 
												ChatColor.DARK_GRAY + "(" + ChatColor.DARK_RED + "No permission" + ChatColor.DARK_GRAY + ")");
									}
								}
								return true;
							} else if (args[1].equalsIgnoreCase("indicators") || args[1].equalsIgnoreCase("indicator")) {
								String header = ChatColor.DARK_PURPLE + "" + "" + ChatColor.BOLD + inds.size() + " Active Indicators";
								sender.sendMessage(headerLeft + header + headerRight);
								for (Indicators indicator: inds) {
									int bullet = inds.indexOf(indicator) + 1;
									String name = Methods.normalizeString(indicator.toString());
									if (Methods.hasPermission(player, indicator.toString())) {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.YELLOW + name);
									} else {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.RED + name + " " + 
												ChatColor.DARK_GRAY + "(" + ChatColor.DARK_RED + "No permission" + ChatColor.DARK_GRAY + ")");
									}
								}
								return true;
							}
						}
					} else if (args[0].equalsIgnoreCase("get")) {
						for (Player target : Bukkit.getOnlinePlayers()) {
							if (args[1].equalsIgnoreCase(target.getName())) {
								String header = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + target.getName();
								sender.sendMessage(headerLeft + header + headerRight);
								if (!Main.plugin.trails.containsKey(target)) {
									sender.sendMessage(ChatColor.GREEN + "Active trail: " + ChatColor.YELLOW + "None");
								} else {
									Trails trail = Trail.getTrail(target);
									sender.sendMessage(ChatColor.GREEN + "Active trail: " + ChatColor.YELLOW + Methods.normalizeString(trail.toString()));
								}
								if (!Main.plugin.inds.containsKey(target)) {
									sender.sendMessage(ChatColor.GREEN + "Active indicator: " + ChatColor.YELLOW + "None");
								} else {
									Indicators ind = Indicator.getIndicator(target);
									sender.sendMessage(ChatColor.GREEN + "Active indicator: " + ChatColor.YELLOW + Methods.normalizeString(ind.toString()));
								}
							} else {
								sender.sendMessage(prefix + ChatColor.RED + "Invalid player name; player may be offline.");
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
			
		} else if (trail.equals(Trails.FIRE)) {
			new FlameArms(player);
		} else if (trail.equals(Trails.STATICFIELD)) {
			new StaticField(player);
		} else if (trail.equals(Trails.COMBUST)) {
			new Combust(player);
			
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
