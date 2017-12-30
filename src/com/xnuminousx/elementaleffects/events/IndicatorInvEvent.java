package com.xnuminousx.elementaleffects.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.GUIs.TrailGui;
import com.xnuminousx.elementaleffects.config.Manager;

public class IndicatorInvEvent implements Listener {
	
	Main plugin = Main.getInstance();
	
	String indGuiName = Manager.getIndicatorGuiName();
	boolean closeInv = Manager.closeInv();
	
	boolean doPrefix = Manager.doPrefix();
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;

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
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Hit Indicator")) {
			ChatColor indColor = ChatColor.DARK_RED;
			String indType = "Hit Indicator";
			String enableMessage = prefix + indColor + indType + " has been enabled!";
			String disableMessage = prefix + indColor + indType + " has been disabled!";;
			String noPerm = prefix + indColor + " You don't have the necessary permission!";;
			
			if (plugin.hit.contains(p)) {
				event.setCancelled(true);
				plugin.hit.remove(p);
				closeInv(p);
				p.sendMessage(disableMessage);
				return;
			} else if (p.hasPermission("elementaleffects.hit") || p.hasPermission("elementaleffects.*")) {
				event.setCancelled(true);
				plugin.hit.add(p);
				closeInv(p);
				p.sendMessage(enableMessage);
				return;
			} else {
				event.setCancelled(true);
				closeInv(p);
				p.sendMessage(noPerm);
				return;
			}
			
		//Open Trail GUI	
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Open Trail GUI")) {
			event.setCancelled(true);
			TrailGui.openGUI(p);
			return;
		}
	}
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}

}
