package com.xnuminousx.elementaleffects.events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.gui.TrailGui;
import com.xnuminousx.elementaleffects.indicators.AvatarStateInd;
import com.xnuminousx.elementaleffects.indicators.MoonIndicator;
import com.xnuminousx.elementaleffects.utils.IndUtils;
import com.xnuminousx.elementaleffects.utils.Names;

public class IndicatorInvEvent implements Listener {
	
	Main plugin = Main.getInstance();
	
	String indGuiName = Manager.getIndicatorGuiName();
	boolean closeInv = Manager.closeInv();
	
	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;

	public String enableMessage(ChatColor color, String indName) {
		return prefix + color + "" + ChatColor.BOLD + indName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}
	
	public String disableMessage(ChatColor color, String indName) {
		return prefix + color + "" + ChatColor.BOLD + indName + ChatColor.RESET + ChatColor.RED + " disabled!";
	}
	
	public String noPermissionMessage(ChatColor color) {
		return prefix + color + "You don't have the necessary permission!";
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player p = (Player)event.getWhoClicked();
		
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		
		if (event.getInventory().getName() != indGuiName) {
			return;
		}
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName().equals(null)) {
			event.setCancelled(true);
			return;
			
		//Enable/Disable HitIndicator	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.hitIndicator())) {
			event.setCancelled(true);
			this.setIndicator(plugin.hit, p, ChatColor.DARK_RED, Names.hitIndicator());
			return;
			
		//Enable/Disable AvatarStateIndicator	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.avatarIndicator())) {
			event.setCancelled(true);
			this.setIndicator(plugin.avatarstate, p, ChatColor.DARK_PURPLE, Names.avatarIndicator());
			new AvatarStateInd(p);
			return;
			
		//Enable/Disable MoonIndicator	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains(Names.moonIndicator())) {
			event.setCancelled(true);
			this.setIndicator(plugin.moon, p, ChatColor.BLUE, Names.moonIndicator());
			new MoonIndicator(p);
			
		//Open Trail GUI	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Trail GUI")) {
			event.setCancelled(true);
			TrailGui.openGUI(p);
			return;
			
		//Disable active indicator	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Disable Indicator")) {
			event.setCancelled(true);
			ArrayList<Player> activeIndicator = IndUtils.getActiveIndicator(p);
			IndUtils.removeActiveIndicator(activeIndicator, p);
			p.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active indicator" + ChatColor.RESET + ChatColor.RED + " disabled!");
			closeInv(p);
			return;
		}
	}
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
	
	public void setIndicator(ArrayList<Player> object, Player player, ChatColor chatColor, String indName) {
		if (object.contains(player)) {
			object.remove(player);
			closeInv(player);
			player.sendMessage(disableMessage(chatColor, indName));
		} else if (hasPermissions(player, indName)) {
			object.add(player);
			closeInv(player);
			player.sendMessage(enableMessage(chatColor, indName));
		} else {
			closeInv(player);
			player.sendMessage(noPermissionMessage(chatColor));
		}
	}
	
	public boolean hasPermissions(Player player, String indName) {
		if (indName == Names.hitIndicator() && player.hasPermission("elementaleffects.hit")) {
			return true;
		} else if (indName == Names.avatarIndicator() && player.hasPermission("elementaleffects.avatarstate")) {
			return true;
		} else if (indName == Names.moonIndicator() && player.hasPermission("elementaleffects.moon")) {
			return true;
		} else if (player.hasPermission("elementaleffects.*")) {
			return true;
		} else {
			return false;
		}
	}
}