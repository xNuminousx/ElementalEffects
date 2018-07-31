package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.xnuminousx.elementaleffects.config.Manager;

public class TrailGui {
	
	public static void openGUI(Player p) {
		String guiName = Manager.getTrailGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 54, guiName);
		
		List<String> openInd = new ArrayList<String>();
		List<String> removeTrail = new ArrayList<String>();
		openInd.add("Use this to open the indicator GUI");
		removeTrail.add("Click here to remove your active trail");
		
		inv.setItem(13, miscItem(Material.END_CRYSTAL, "Open Indicator GUI", ChatColor.DARK_AQUA, openInd));
		inv.setItem(31, miscItem(Material.BARRIER, "Disable Trail", ChatColor.DARK_RED, removeTrail));
		inv.setItem(9, createItem(p, Material.GRASS, "Earth Trail", ChatColor.GREEN, "earth"));
		inv.setItem(18, createItem(p, Material.SAND, "Sandy Cloak", ChatColor.YELLOW, "sand"));
		inv.setItem(27, createItem(p, Material.MAGMA_CREAM, "Eruption", ChatColor.DARK_GREEN, "lava"));
		inv.setItem(11, createItem(p, Material.WATER_BUCKET, "Water Trail", ChatColor.AQUA, "water"));
		inv.setItem(20, createItem(p, Material.POTION, "Hydro", ChatColor.BLUE, "water2"));
		inv.setItem(29, createItem(p, Material.ICE, "Icicle Shoes", ChatColor.DARK_AQUA, "ice"));
		inv.setItem(15, createItem(p, Material.BLAZE_POWDER, "Fire Trail", ChatColor.RED, "fire"));
		inv.setItem(24, createItem(p, Material.FIREBALL, "Flame Arms", ChatColor.RED, "fire2"));
		inv.setItem(33, createItem(p, Material.REDSTONE_TORCH_ON, "Static Field", ChatColor.DARK_RED, "lightning"));
		inv.setItem(17, createItem(p, Material.STRING, "Air Trail", ChatColor.GRAY, "air"));
		inv.setItem(26, createItem(p, Material.WEB, "Aero Sphere", ChatColor.GRAY, "air2"));
		inv.setItem(35, createItem(p, Material.FEATHER, "Float!", ChatColor.DARK_GRAY, "flight"));
		inv.setItem(51, createItem(p, Material.LEASH, "Chi Trail", ChatColor.GOLD, "chi"));
		inv.setItem(52, createItem(p, Material.WOOD_SWORD, "Intensity", ChatColor.GOLD, "chi2"));
		inv.setItem(46, createItem(p, Material.NETHER_STAR, "Avatar Trail", ChatColor.DARK_PURPLE, "avatar"));
		inv.setItem(47, createItem(p, Material.BEACON, "Elemental Rings", ChatColor.DARK_PURPLE, "avatar2l"));
		
		p.openInventory(inv);
	}
	
	public static ItemStack createItem(Player p, Material item, String name, ChatColor color, String permissionType) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		List<String> hasPerm = new ArrayList<String>();
		List<String> noPerm = new ArrayList<String>();
		hasPerm.add(ChatColor.GREEN + "Enable" + ChatColor.GRAY + " / " + ChatColor.DARK_RED + "Disable");
		noPerm.add(ChatColor.DARK_RED + "" + ChatColor.BOLD + "No permission!");
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		if (p.hasPermission("elementaleffects." + permissionType) || p.hasPermission("elementaleffects.*")) {
			itemMeta.setLore(hasPerm);
		} else {
			itemMeta.setLore(noPerm);
		}
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
	
	public static ItemStack miscItem(Material item, String name, ChatColor color, List<String> description) {
		ItemStack itemType = new ItemStack(item);
		ItemMeta itemMeta = itemType.getItemMeta();
		
		itemMeta.setDisplayName(color + "" + ChatColor.BOLD + name);
		itemMeta.setLore(description);
		
		itemType.setItemMeta(itemMeta);
		
		return itemType;
	}
}