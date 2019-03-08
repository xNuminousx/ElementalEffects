package com.xnuminousx.elementaleffects.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.IndGui;
import com.xnuminousx.elementaleffects.trails.AeroSphere;
import com.xnuminousx.elementaleffects.trails.Cloud;
import com.xnuminousx.elementaleffects.trails.ElementalRings;
import com.xnuminousx.elementaleffects.trails.FlameArms;
import com.xnuminousx.elementaleffects.trails.LavaTrail;
import com.xnuminousx.elementaleffects.trails.SandCloak;
import com.xnuminousx.elementaleffects.trails.StaticField;
import com.xnuminousx.elementaleffects.trails.WaterRings;
import com.xnuminousx.elementaleffects.utils.Names;
import com.xnuminousx.elementaleffects.utils.TrailUtils;
import com.xnuminousx.elementaleffects.trails.Float;

public class TrailInvEvent implements Listener {
	
	Main plugin = Main.getInstance();
	String trailGuiName = Manager.getTrailGuiName();
	boolean reqEle = Manager.requireElement();
	boolean closeInv = Manager.closeInv();
	
	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	
	public String enableMessage(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}
	
	public String disableMessage(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.RED + " disabled!";
	}
	
	public String noElementMessage(ChatColor color) {
		return prefix + color + "You don't have the necessary element!";
	}
	
	public String noPermissionMessage(ChatColor color) {
		return prefix + color + "You don't have the necessary permission!";
	}
	
	@EventHandler
	public void onTrailInvClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (!event.getInventory().getTitle().contains(trailGuiName)) {
			return;
		} else if ((event.getCurrentItem() == null) || 
				(event.getCurrentItem() == new ItemStack(Material.AIR)) || 
				event.getCurrentItem().getItemMeta() == null || 
				event.getCurrentItem().getItemMeta().getDisplayName().isEmpty()) {
			event.setCancelled(true);
			return;
			
		// Enable/Disable Earth Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.earthTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.earth, player, ChatColor.GREEN, Names.earthTrail(), Element.EARTH, null);
			return;
			
		// Enable/Disable Earth Trail 2 (Eruption)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.lavaTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.lava, player, ChatColor.DARK_GREEN, Names.lavaTrail(), Element.EARTH, Element.LAVA);
			new LavaTrail(player);
			return;
			
		// Enable/Disable SandCloak Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.sandyCloak())) {
			event.setCancelled(true);
			this.setTrail(plugin.sand, player, ChatColor.YELLOW, Names.sandyCloak(), Element.EARTH, Element.SAND);
			new SandCloak(player);
			return;
			
		// Enable/Disable Fire Trail
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.fireTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.fire, player, ChatColor.RED, Names.fireTrail(), Element.FIRE, null);
			return;
			
		// Enable/Disable Fire Trail 2 (Flame Arms)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.flameArms())) {
			event.setCancelled(true);
			this.setTrail(plugin.flamearms, player, ChatColor.RED, Names.flameArms(), Element.FIRE, null);
			new FlameArms(player);
			return;
			
		// Enable/Disable Lightning (Static Field) Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.staticField())) {
			event.setCancelled(true);
			this.setTrail(plugin.lightning, player, ChatColor.DARK_RED, Names.staticField(), Element.FIRE, Element.LIGHTNING);
			new StaticField(player);
			return;
		
		// Enable/Disable Water Trail		
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.waterTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.water, player, ChatColor.AQUA, Names.waterTrail(), Element.WATER, null);
			return;
			
			// Enable/Disable Water Trail 2 (Hydro)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.hydro())) {
			event.setCancelled(true);
			this.setTrail(plugin.hydro, player, ChatColor.BLUE, Names.hydro(), Element.WATER, null);
			new WaterRings(player);
			return;
			
		// Enable/Disable Ice Trail	(Ice Shoes)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.iceBoots())) {
			event.setCancelled(true);
			this.setTrail(plugin.ice, player, ChatColor.DARK_AQUA, Names.iceBoots(), Element.WATER, Element.ICE);
			return;
			
		// Enable/Disable Chi Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.chiTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.chi, player, ChatColor.GOLD, Names.chiTrail(), Element.CHI, null);
			return;
			
		//Enable/Disable Chi Trail 2 (Intensity)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.intensity())) {
			event.setCancelled(true);
			this.setTrail(plugin.intensity, player, ChatColor.YELLOW, Names.intensity(), Element.CHI, null);
			return;
			
		// Enable/Disable Avatar Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.avatarTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.avatar, player, ChatColor.DARK_PURPLE, Names.avatarTrail(), Element.AVATAR, null);
			return;
		
		// Enable/Disable Avatar Trail 2 (Elemental Rings)
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.elementalRings())) {
			event.setCancelled(true);
			this.setTrail(plugin.elementrings, player, ChatColor.DARK_PURPLE, Names.elementalRings(), Element.AVATAR, null);
			new ElementalRings(player);
			return;
			
		// Enable/Disable Air Trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.airTrail())) {
			event.setCancelled(true);
			this.setTrail(plugin.air, player, ChatColor.GRAY, Names.airTrail(), Element.AIR, null);
			new Cloud(player);
			return;

		// Enable/Disable Air Trail 2 (Aero)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.aeroSphere())) {
			event.setCancelled(true);
			this.setTrail(plugin.aero, player, ChatColor.GRAY, Names.aeroSphere(), Element.AIR, null);
			new AeroSphere(player);
			return;
			
		// Enable/Disable Flight Trail (Float!)	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.flight())) {
			event.setCancelled(true);
			this.setTrail(plugin.flight, player, ChatColor.DARK_GRAY, Names.flight(), Element.AIR, Element.FLIGHT);
			new Float(player);
			return;
			
		//Open indicator GUI	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Indicator GUI")) {
			event.setCancelled(true);
			IndGui.openGui(player);
			return;
			
		//Disable trail	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Disable Trail")) {
			event.setCancelled(true);
			ArrayList<Player> activeTrail = TrailUtils.getActiveTrail(player);
			if (activeTrail.equals(Main.plugin.noTrail)) {
				return;
			} else {
				TrailUtils.removeActiveTrail(activeTrail, player);
				player.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active trail" + ChatColor.RESET + ChatColor.RED + " disabled!");
				closeInv(player);
			}
			return;
		
		//Security	
		} else {
			event.setCancelled(true);
			return;
		}
	}
	
	public void setTrail(ArrayList<Player> object, Player player, ChatColor chatColor, String trailName, Element element, Element sub) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		
		if (object.contains(player)) {
			object.remove(player);
			closeInv(player);
			player.sendMessage(disableMessage(chatColor, trailName));
		} else if (hasPermissions(player, trailName)) {
			if (reqEle) {
				if (bPlayer.hasElement(element) && bPlayer.hasElement(sub)) {
					// Give trail
					TrailUtils.setActiveTrail(trailName, player);
					closeInv(player);
					player.sendMessage(enableMessage(chatColor, trailName));
				} else {
					// Doesn't have element
					closeInv(player);
					player.sendMessage(noElementMessage(chatColor));
				}
			} else {
				// Give trail
				TrailUtils.setActiveTrail(trailName, player);
				closeInv(player);
				player.sendMessage(enableMessage(chatColor, trailName));
			}
		} else {
			// Doesn't have permission
			closeInv(player);
			player.sendMessage(noPermissionMessage(chatColor));
		}
	}
	
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
	
	public boolean hasPermissions(Player player, String trailName) {
		if (trailName == Names.earthTrail() && player.hasPermission("elementaleffects.earth")) {
			return true;
		} else if (trailName == Names.lavaTrail() && player.hasPermission("elementaleffects.lava")) {
			return true;
		} else if (trailName == Names.sandyCloak() && player.hasPermission("elementaleffects.sand")) {
			return true;
		} else if (trailName == Names.fireTrail() && player.hasPermission("elementaleffets.fire")) {
			return true;
		} else if (trailName == Names.flameArms() && player.hasPermission("elementaleffects.fire2")) {
			return true;
		} else if (trailName == Names.staticField() && player.hasPermission("elementaleffects.lightning")) {
			return true;
		} else if (trailName == Names.waterTrail() && player.hasPermission("elementaleffects.water")) {
			return true;
		} else if (trailName == Names.hydro() && player.hasPermission("elementaleffects.water2")) {
			return true;
		} else if (trailName == Names.iceBoots() && player.hasPermission("elementaleffects.ice")) {
			return true;
		} else if (trailName == Names.airTrail() && player.hasPermission("elementaleffects.air")) {
			return true;
		} else if (trailName == Names.aeroSphere() && player.hasPermission("elementaleffects.air2")) {
			return true;
		} else if (trailName == Names.flight() && player.hasPermission("elementaleffects.flight")) {
			return true;
		} else if (trailName == Names.chiTrail() && player.hasPermission("elementaleffects.chi")) {
			return true;
		} else if (trailName == Names.intensity() && player.hasPermission("elementaleffects.chi2")) {
			return true;
		} else if (trailName == Names.avatarTrail() && player.hasPermission("elementaleffects.avatar")) {
			return true;
		} else if (trailName == Names.elementalRings() && player.hasPermission("elementaleffects.avatar2")) {
			return true;
		} else if (player.hasPermission("elementaleffects.*")) {
			return true;
		} else {
			return false;
		}
	}
}