package com.xnuminousx.elementaleffects.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Names;

public class TrailGui {
	
	public static void openGUI(Player p) {
		String guiName = Manager.getTrailGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 54, guiName);
		
		List<String> openInd = new ArrayList<String>();
		List<String> removeTrail = new ArrayList<String>();
		openInd.add("Use this to open the indicator GUI");
		removeTrail.add("Click here to remove your active trail");
		
		inv.setItem(13, Methods.miscItem(Material.END_CRYSTAL, "Open Indicator GUI", ChatColor.DARK_AQUA, openInd));
		inv.setItem(31, Methods.miscItem(Material.BARRIER, "Disable Trail", ChatColor.DARK_RED, removeTrail));
		inv.setItem(9, Methods.createItem(p, Material.GRASS_BLOCK, Names.earthTrail(), ChatColor.GREEN, "earth"));
		inv.setItem(18, Methods.createItem(p, Material.SAND, Names.sandyCloak(), ChatColor.YELLOW, "sand"));
		inv.setItem(27, Methods.createItem(p, Material.MAGMA_CREAM, Names.lavaTrail(), ChatColor.DARK_GREEN, "lava"));
		inv.setItem(11, Methods.createItem(p, Material.WATER_BUCKET, Names.waterTrail(), ChatColor.AQUA, "water"));
		inv.setItem(20, Methods.createItem(p, Material.POTION, Names.hydro(), ChatColor.BLUE, "water2"));
		inv.setItem(29, Methods.createItem(p, Material.ICE, Names.iceBoots(), ChatColor.DARK_AQUA, "ice"));
		inv.setItem(15, Methods.createItem(p, Material.BLAZE_POWDER, Names.fireTrail(), ChatColor.RED, "fire"));
		inv.setItem(24, Methods.createItem(p, Material.FIRE_CHARGE, Names.flameArms(), ChatColor.RED, "fire2"));
		inv.setItem(33, Methods.createItem(p, Material.REDSTONE_TORCH, Names.staticField(), ChatColor.DARK_RED, "lightning"));
		inv.setItem(17, Methods.createItem(p, Material.STRING, Names.airTrail(), ChatColor.GRAY, "air"));
		inv.setItem(26, Methods.createItem(p, Material.COBWEB, Names.aeroSphere(), ChatColor.GRAY, "air2"));
		inv.setItem(35, Methods.createItem(p, Material.FEATHER, Names.flight(), ChatColor.DARK_GRAY, "flight"));
		inv.setItem(51, Methods.createItem(p, Material.LEAD, Names.chiTrail(), ChatColor.GOLD, "chi"));
		inv.setItem(52, Methods.createItem(p, Material.WOODEN_SWORD, Names.intensity(), ChatColor.GOLD, "chi2"));
		inv.setItem(46, Methods.createItem(p, Material.NETHER_STAR, Names.avatarTrail(), ChatColor.DARK_PURPLE, "avatar"));
		inv.setItem(47, Methods.createItem(p, Material.BEACON, Names.elementalRings(), ChatColor.DARK_PURPLE, "avatar2"));
		
		p.openInventory(inv);
	}
}