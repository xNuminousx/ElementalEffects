package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.xnuminousx.elementaleffects.config.Manager;

public class TrailGui {
	
	@SuppressWarnings("deprecation")
	public static void openGUI(Player p) {
		String guiName = Manager.getTrailGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 45, guiName);
		
		ArrayList<String> hasPerm = new ArrayList<String>();
		ArrayList<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + " / " + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "No permission!");
			
		ItemStack indGui = new ItemStack(Material.END_CRYSTAL);
		ItemStack earthItem = new ItemStack(Material.GRASS);
		ItemStack sandItem = new ItemStack(Material.SAND);
		ItemStack lavaItem = new ItemStack(Material.MAGMA_CREAM);
		ItemStack waterItem = new ItemStack(Material.WATER_BUCKET);
		ItemStack waterItem2 = new ItemStack(Material.POTION);
		// Finding a way to get a water bottle instead of a potion 
		waterItem2.getData().setData((byte) 0);
		ItemStack iceItem = new ItemStack(Material.ICE);
		ItemStack fireItem = new ItemStack(Material.BLAZE_POWDER);
		ItemStack fireItem2 = new ItemStack(Material.FIREBALL);
		ItemStack lightningItem = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemStack airItem = new ItemStack(Material.STRING);
		ItemStack airItem2 = new ItemStack(Material.WEB);
		ItemStack flightItem = new ItemStack(Material.FEATHER);
		ItemStack chiItem = new ItemStack(Material.LEASH);
		ItemStack chiItem2 = new ItemStack(Material.WOOD_SWORD);
		ItemStack avatarItem = new ItemStack(Material.NETHER_STAR);
		ItemStack avatarItem2 = new ItemStack(Material.BEACON);
		
		ItemMeta indMeta = indGui.getItemMeta();
		ItemMeta earthMeta = earthItem.getItemMeta();
		ItemMeta sandMeta = sandItem.getItemMeta();
		ItemMeta lavaMeta = lavaItem.getItemMeta();
		ItemMeta waterMeta = waterItem.getItemMeta();
		ItemMeta waterMeta2 = waterItem2.getItemMeta();
		ItemMeta iceMeta = iceItem.getItemMeta();
		ItemMeta fireMeta = fireItem.getItemMeta();
		ItemMeta fireMeta2 = fireItem2.getItemMeta();
		ItemMeta lightningMeta = lightningItem.getItemMeta();
		ItemMeta airMeta = airItem.getItemMeta();
		ItemMeta airMeta2 = airItem2.getItemMeta();
		ItemMeta flightMeta = flightItem.getItemMeta();
		ItemMeta chiMeta = chiItem.getItemMeta();
		ItemMeta chiMeta2 = chiItem2.getItemMeta();
		ItemMeta avatarMeta = avatarItem.getItemMeta();
		ItemMeta avatarMeta2 = avatarItem2.getItemMeta();
		
		indMeta.setDisplayName(ChatColor.DARK_AQUA + "Open Indicator GUI");
		earthMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Earth Trail");
		if (p.hasPermission("elementaleffects.earth") || p.hasPermission("elementaleffects.*")) {
			earthMeta.setLore(hasPerm);
		} else {
			earthMeta.setLore(noPerm);
		}
		
		sandMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sandy Cloak");
		if (p.hasPermission("elementaleffects.sand") || p.hasPermission("elementaleffects.*")) {
			sandMeta.setLore(hasPerm);
		} else {
			sandMeta.setLore(noPerm);
		}
		
		lavaMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Eruption");
		if (p.hasPermission("elementaleffects.lava") || p.hasPermission("elementaleffects.*")) {
			lavaMeta.setLore(hasPerm);
		} else {
			lavaMeta.setLore(noPerm);
		}
		
		waterMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Water Trail");
		if (p.hasPermission("elementaleffects.water") || p.hasPermission("elementaleffects.*")) {
			waterMeta.setLore(hasPerm);
		} else {
			waterMeta.setLore(noPerm);
		}
		
		waterMeta2.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Hydro");
		if (p.hasPermission("elementaleffects.water2") || p.hasPermission("elementaleffects.*")) {
			waterMeta2.setLore(hasPerm);
		} else {
			waterMeta2.setLore(noPerm);
		}
		
		iceMeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Icicle Shoes");
		if (p.hasPermission("elementaleffects.ice") || p.hasPermission("elementaleffects.*")) {
			iceMeta.setLore(hasPerm);
		} else {
			iceMeta.setLore(noPerm);
		}
		
		fireMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Fire Trail");
		if (p.hasPermission("elementaleffects.fire") || p.hasPermission("elementaleffects.*")) {
			fireMeta.setLore(hasPerm);
		} else {
			fireMeta.setLore(noPerm);
		}
		
		fireMeta2.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Flame Arms");
		if (p.hasPermission("elementaleffects.fire2") || p.hasPermission("elementaleffects.*")) {
			fireMeta2.setLore(hasPerm);
		} else {
			fireMeta2.setLore(noPerm);
		}
		
		lightningMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Static Field");
		if (p.hasPermission("elementaleffects.lightning") || p.hasPermission("elementaleffects.*")) {
			lightningMeta.setLore(hasPerm);
		} else {
			lightningMeta.setLore(noPerm);
		}
		
		airMeta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Air Trail");
		if (p.hasPermission("elementaleffects.air") || p.hasPermission("elementaleffects.*")) {
			airMeta.setLore(hasPerm);
		} else {
			airMeta.setLore(noPerm);
		}
		
		airMeta2.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Aero Sphere");
		if (p.hasPermission("elementaleffects.air2") || p.hasPermission("elementaleffects.*")) {
			airMeta2.setLore(hasPerm);
		} else {
			airMeta2.setLore(noPerm);
		}
		
		flightMeta.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Float!");
		if (p.hasPermission("elementaleffects.flight") || p.hasPermission("elementaleffects.*")) {
			flightMeta.setLore(hasPerm);
		} else {
			flightMeta.setLore(noPerm);
		}
		
		chiMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Chi Trail");
		if (p.hasPermission("elementaleffects.chi") || p.hasPermission("elementaleffects.*")) {
			chiMeta.setLore(hasPerm);
		} else {
			chiMeta.setLore(noPerm);
		}
		
		chiMeta2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Intensity");
		if (p.hasPermission("elementaleffects.chi2") || p.hasPermission("elementaleffects.*")) {
			chiMeta2.setLore(hasPerm);
		} else {
			chiMeta2.setLore(noPerm);
		}
		
		avatarMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Avatar Trail");
		if (p.hasPermission("elementaleffects.avatar") || p.hasPermission("elementaleffects.*")) {
			avatarMeta.setLore(hasPerm);
		} else {
			avatarMeta.setLore(noPerm);
		}
		
		avatarMeta2.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Elemental Rings");
		if (p.hasPermission("elementaleffects.avatar2") || p.hasPermission("elementaleffects.*")) {
			avatarMeta2.setLore(hasPerm);
		} else {
			avatarMeta2.setLore(noPerm);
		}
		
		indGui.setItemMeta(indMeta);
		earthItem.setItemMeta(earthMeta);
		sandItem.setItemMeta(sandMeta);
		lavaItem.setItemMeta(lavaMeta);
		waterItem.setItemMeta(waterMeta);
		waterItem2.setItemMeta(waterMeta2);
		iceItem.setItemMeta(iceMeta);
		fireItem.setItemMeta(fireMeta);
		fireItem2.setItemMeta(fireMeta2);
		lightningItem.setItemMeta(lightningMeta);
		airItem.setItemMeta(airMeta);
		airItem2.setItemMeta(airMeta2);
		flightItem.setItemMeta(flightMeta);
		chiItem.setItemMeta(chiMeta);
		chiItem2.setItemMeta(chiMeta2);
		avatarItem.setItemMeta(avatarMeta);
		avatarItem2.setItemMeta(avatarMeta2);
		
		inv.setItem(40, indGui);
		inv.setItem(10, earthItem);
		inv.setItem(19, sandItem);
		inv.setItem(9, lavaItem);
		inv.setItem(12, waterItem);
		inv.setItem(3, waterItem2);
		inv.setItem(21, iceItem);
		inv.setItem(14, fireItem);
		inv.setItem(23, fireItem2);
		inv.setItem(5, lightningItem);
		inv.setItem(16, airItem);
		inv.setItem(25, airItem2);
		inv.setItem(17, flightItem);
		inv.setItem(30, chiItem);
		inv.setItem(39, chiItem2);
		inv.setItem(32, avatarItem);
		inv.setItem(41, avatarItem2);
		
		p.openInventory(inv);
	}

}
