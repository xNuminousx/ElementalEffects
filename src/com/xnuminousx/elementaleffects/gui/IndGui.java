package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.projectkorra.Element;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.indicators.AvatarStateInd;
import com.xnuminousx.elementaleffects.indicators.MoonIndicator;
import com.xnuminousx.elementaleffects.indicators.SunIndicator;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Indicator;
import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;

public class IndGui implements Listener {
	
	HashMap<Player, Indicator> inds = Main.plugin.inds;
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	Element missingEle;
	boolean doPrefix = Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	boolean closeInv = Manager.closeInv();
	boolean reqEle = Manager.requireElement();
	String indGuiName = Manager.getIndicatorGuiName();
	
	String enabled(ChatColor color, String indName) {
		return prefix + color + "" + ChatColor.BOLD + indName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}
	
	String disabled(ChatColor color, String indName) {
		return prefix + color + "" + ChatColor.BOLD + indName + ChatColor.RESET + ChatColor.RED + " disabled!";
	}
	
	String noPerm(ChatColor color) {
		return prefix + color + "You don't have the necessary permission!";
	}
	
	public static void openGui(Player p) {
		String guiName = Manager.getIndicatorGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 54, guiName);
		
		List<String> openTrail = new ArrayList<String>();
		List<String> removeInd = new ArrayList<String>();
		openTrail.add("Use this to open the trail GUI");
		removeInd.add("Click here to remove your active indicator");
		
		
		inv.setItem(13, Methods.createItem(Material.END_CRYSTAL, "Open Trail GUI", ChatColor.DARK_AQUA, openTrail));
		inv.setItem(31, Methods.createItem(Material.BARRIER, "Disable Indicator", ChatColor.DARK_RED, removeInd));
		inv.setItem(15, Methods.createItem(p, Material.GHAST_TEAR, AvatarStateInd.getName(), ChatColor.DARK_PURPLE, Indicators.AVATARSTATE));
		inv.setItem(11, Methods.createItem(p, Material.REDSTONE, Methods.normalizeString(Indicators.HIT.toString()), ChatColor.DARK_RED, Indicators.HIT));
		inv.setItem(29, Methods.createItem(p, Material.ENDER_PEARL, MoonIndicator.getName(), ChatColor.BLUE, Indicators.MOON));
		inv.setItem(33, Methods.createItem(p, Material.CLOCK, SunIndicator.getName(), ChatColor.YELLOW, Indicators.SUN));
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		
		if (!event.getInventory().getTitle().contains(indGuiName)) {
			return;
		} else if ((event.getCurrentItem() == null) || 
				(event.getCurrentItem() == new ItemStack(Material.AIR)) || 
				event.getCurrentItem().getItemMeta() == null || 
				event.getCurrentItem().getItemMeta().getDisplayName().isEmpty()) {
			event.setCancelled(true);
			return;
				
		}
		ItemStack clickedItem = event.getCurrentItem();
		
		if (doPrefix) {
			prefix = prefixColor + "ElementalEffects: ";
		} else {
			prefix = "";
		}
		if (clickedItem.getItemMeta().getDisplayName().contains(AvatarStateInd.getName())) {
			event.setCancelled(true);
			manageInds(player, Indicators.AVATARSTATE);
			new AvatarStateInd(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(Methods.normalizeString(Indicators.HIT.toString()))) {
			event.setCancelled(true);
			manageInds(player, Indicators.HIT);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(MoonIndicator.getName())) {
			event.setCancelled(true);
			manageInds(player, Indicators.MOON);
			new MoonIndicator(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(SunIndicator.getName())) {
			event.setCancelled(true);
			manageInds(player, Indicators.SUN);
			new SunIndicator(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains("Open Trail GUI")) {
			event.setCancelled(true);
			TrailGui.openGUI(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains("Disable Indicator")) {
			event.setCancelled(true);
			Indicator.removeIndicator(player);
			player.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active indicator" + ChatColor.RESET + ChatColor.RED + " disabled!");
			closeInv(player);
			return;
		} else {
			event.setCancelled(true);
			return;
		}
	}
	
	public void setIndicator(Player player, Indicators type) {
		if (inds.containsKey(player)) {
			if (type == inds.get(player).getType()) {
				inds.remove(player);
				player.sendMessage(this.disabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
			} else {
				inds.put(player, new Indicator(type));
				player.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
			}
		} else {
			inds.put(player, new Indicator(type));
			player.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
		}
	}
	
	public void manageInds(Player player, Indicators type) {
		if (Methods.hasPermission(player, "indicators", type.toString().toLowerCase())) {
			closeInv(player);
			setIndicator(player, type);
		} else {
			closeInv(player);
			player.sendMessage(this.noPerm(ChatColor.RED));
		}
	}
	
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
}