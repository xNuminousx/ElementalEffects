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
import com.xnuminousx.elementaleffects.indicators.AvatarStateInd;
import com.xnuminousx.elementaleffects.indicators.MoonIndicator;
import com.xnuminousx.elementaleffects.indicators.SunIndicator;
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
	boolean showExpanded = Manager.doExpand();
	
	List<String> expanded = new ArrayList<String>();
	List<String> condensed = new ArrayList<String>();
	List<Trails> trails = new ArrayList<Trails>();
	List<Indicators> inds = new ArrayList<Indicators>();
	
	//Formatting
	String prefix;
	String headerLeft = ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "   " + ChatColor.RESET + " ";
	String headerRight = " " + ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "   ";
	String noPermission = ChatColor.DARK_GRAY + "(" + ChatColor.DARK_RED + "No permission" + ChatColor.DARK_GRAY + ")";
	String version = Main.plugin.getDescription().getVersion();
	String title = ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "ElementalEffects v" + version;
	
	//Config statements
	String invalidEffect = Main.getInstance().getConfig().getString("Language.InvalidEffectName");
	String invalidPlayer = Main.getInstance().getConfig().getString("Language.InvalidPlayerName");
	String noPerm = Main.getInstance().getConfig().getString("Language.NoPermissionMessage");
	String specifyTarget = Main.getInstance().getConfig().getString("Language.SpecifyTarget");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if (doPrefix) {
			prefix = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		for (Trails trail : Trails.values()) {
			trails.add(trail);
		}
		
		for (Indicators indicator : Indicators.values()) {
			inds.add(indicator);
		}
		
		if (lable.equalsIgnoreCase("elementaleffects") || lable.equalsIgnoreCase("ee")) {
			if (args.length == 0) {
				if (showExpanded) {
					for (String line : expandedList()) {
						sender.sendMessage(line);
					}
				} else {
					for (String line : condensedList()) {
						sender.sendMessage(line);
					}
				}
				return true;
			} else if (args.length == 1) {
				if (sender instanceof Player) {
					Player player = (Player)sender;
					if ((args[0].equalsIgnoreCase("trails") || args[0].equalsIgnoreCase("trail") || args[0].equalsIgnoreCase("effects"))) {
						if (Methods.hasPermission(player, "commands", "trails")) {
							TrailGui.openGUI(player);
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("ind") || args[0].equalsIgnoreCase("indicators") || args[0].equalsIgnoreCase("indicator")) {
						if (Methods.hasPermission(player, "commands", "indicators")) {
							IndGui.openGui(player);
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("get") && Methods.hasPermission(player, "commands", "get")) {
						if (Methods.hasPermission(player, "commands", "get")) {
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
							sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Version: " + version);
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("set")) {
						if (Methods.hasPermission(player, "commands", "set")) {
							sender.sendMessage(prefix + ChatColor.RED + invalidEffect);
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("remove")) {
						if (Methods.hasPermission(player, "commands", "remove")) {
							Trail.removeTrail((Player)sender);
							Indicator.removeIndicator((Player)sender);
							sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "All effects" + ChatColor.RESET + ChatColor.RED + " disabled!");
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("list")) {
						if (Methods.hasPermission(player, "commands", "list")) {
							sender.sendMessage(prefix + ChatColor.RED + specifyTarget);
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
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
						if (Methods.hasPermission(player, "commands", "set")) {
							for (Trails trail : Trails.values()) {
								if (trail.toString().equalsIgnoreCase(args[1])) {
									if (Methods.hasPermission(player, "trails", trail.toString())) {
										sender.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(trail.toString())));
										Trail.setTrail(player, trail);
										activateAnimation(player, trail);
									} else {
										sender.sendMessage(prefix + ChatColor.RED + noPerm);
									}
								}
							}
							for (Indicators indicator: Indicators.values()) {
								if (indicator.toString().equalsIgnoreCase(args[1])) {
									if (Methods.hasPermission(player, "indicators", indicator.toString())) {
										sender.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(indicator.toString())));
										Indicator.setIndicator(player, indicator);
										activateAnimation(player, indicator);
									} else {
										sender.sendMessage(prefix + ChatColor.RED + noPerm);
									}
								}
							}
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("remove")) {
						if (Methods.hasPermission(player, "commands", "remove")) {
							if (args[1].equalsIgnoreCase("trails") || args[1].equalsIgnoreCase("trail")) {
								Trail.removeTrail((Player)sender);
								sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active trail" + ChatColor.RESET + ChatColor.RED + " disabled!");
							} else if (args[1].equalsIgnoreCase("indicators") || args[1].equalsIgnoreCase("indicator")) {
								Indicator.removeIndicator((Player)sender);
								sender.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active indicator" + ChatColor.RESET + ChatColor.RED + " disabled!");
							}
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("list")) {
						if (Methods.hasPermission(player, "commands", "list")) {
							if (args[1].equalsIgnoreCase("trails") || args[1].equalsIgnoreCase("trail")) {
								String header = ChatColor.DARK_PURPLE + "" + "" + ChatColor.BOLD + Trails.values().length + " Trails";
								sender.sendMessage(headerLeft + header + headerRight);
								for (Trails trail : Trails.values()) {
									int bullet = trails.indexOf(trail) + 1;
									String name = Methods.normalizeString(trail.toString());
									if (Methods.hasPermission(player, "trails", trail.toString())) {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.YELLOW + name);
									} else {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.RED + name + " " + noPermission);
									}
								}
							} else if (args[1].equalsIgnoreCase("indicators") || args[1].equalsIgnoreCase("indicator")) {
								String header = ChatColor.DARK_PURPLE + "" + "" + ChatColor.BOLD + Indicators.values().length + " Indicators";
								sender.sendMessage(headerLeft + header + headerRight);
								for (Indicators indicator: Indicators.values()) {
									int bullet = inds.indexOf(indicator) + 1;
									String name = Methods.normalizeString(indicator.toString());
									if (Methods.hasPermission(player, "indicators", indicator.toString())) {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.YELLOW + name);
									} else {
										sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + bullet + ". " + ChatColor.RED + name + " " + noPermission);
									}
								}
							}
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
					} else if (args[0].equalsIgnoreCase("get")) {
						if (Methods.hasPermission(player, "commands", "get")) {
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
									sender.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Version: " + version);
								} else {
									sender.sendMessage(prefix + ChatColor.RED + invalidPlayer);
								}
							}
						} else {
							sender.sendMessage(prefix + ChatColor.RED + noPerm);
						}
						return true;
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
	
	public void activateAnimation(Player player, Indicators indicator) {
		if (indicator.equals(Indicators.AVATARSTATE)) {
			new AvatarStateInd(player);
		} else if (indicator.equals(Indicators.MOON)) {
			new MoonIndicator(player);
		} else if (indicator.equals(Indicators.SUN)) {
			new SunIndicator(player);
		}
	}
	
	public List<String> expandedList() {
		expanded.add(headerLeft + title + headerRight);
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/elementaleffects");
		expanded.add(" " + ChatColor.YELLOW + "- Shows a list of commands.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee trails");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee trail, /ee effects");
		expanded.add(" " + ChatColor.YELLOW + "- Opens the trail GUI.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee indicators");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee indicator, /ee ind");
		expanded.add(" " + ChatColor.YELLOW + "- Opens the indicators GUI.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee get [name]");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "none");
		expanded.add(" " + ChatColor.YELLOW + "- Gets the effect information of a player.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee list [trail/indicator]");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "none");
		expanded.add(" " + ChatColor.YELLOW + "- Shows a list of trails or indicators.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee enable [name]");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee set [name]");
		expanded.add(" " + ChatColor.YELLOW + "- Activates a trail or indicator without the GUI.");
		expanded.add("");
		expanded.add(ChatColor.GREEN + "" + ChatColor.BOLD + "/ee disable [trail/indicator]");
		expanded.add(" " + ChatColor.GRAY + "Alias: " + ChatColor.ITALIC + "/ee remove [trail/indicator]");
		expanded.add(" " + ChatColor.YELLOW + "- Removes your active trail or indicator.");
		return expanded;
	}
	
	public List<String> condensedList() {
		condensed.add(headerLeft + title + headerRight);
		condensed.add(ChatColor.GREEN + "/ee " + ChatColor.YELLOW + "- Shows a list of commands");
		condensed.add(ChatColor.GREEN + "/ee [trail/indicator] " + ChatColor.YELLOW + "- Opens the trail/indicator GUI");
		condensed.add(ChatColor.GREEN + "/ee get [name] " + ChatColor.YELLOW + "- Gets the effect information of a player.");
		condensed.add(ChatColor.GREEN + "/ee list [trail/indicator] " + ChatColor.YELLOW + "- Shows a list of all trails or indicators.");
		condensed.add(ChatColor.GREEN + "/ee enable [effect name] " + ChatColor.YELLOW + "- Activates a trail or indicator without the GUI.");
		condensed.add(ChatColor.GREEN + "/ee disable [trail/indicator] " + ChatColor.YELLOW + "- Disables your active trail or indicator.");
		return condensed;
	}

}
