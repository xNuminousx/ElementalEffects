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

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.trails.AeroSphere;
import com.xnuminousx.elementaleffects.trails.AirTrail;
import com.xnuminousx.elementaleffects.trails.AvatarTrail;
import com.xnuminousx.elementaleffects.trails.Blood;
import com.xnuminousx.elementaleffects.trails.ChiTrail;
import com.xnuminousx.elementaleffects.trails.Cloud;
import com.xnuminousx.elementaleffects.trails.Combust;
import com.xnuminousx.elementaleffects.trails.EarthTrail;
import com.xnuminousx.elementaleffects.trails.ElementalRings;
import com.xnuminousx.elementaleffects.trails.FireTrail;
import com.xnuminousx.elementaleffects.trails.FlameArms;
import com.xnuminousx.elementaleffects.trails.Float;
import com.xnuminousx.elementaleffects.trails.IceTrail;
import com.xnuminousx.elementaleffects.trails.Intensity;
import com.xnuminousx.elementaleffects.trails.LavaTrail;
import com.xnuminousx.elementaleffects.trails.SandCloak;
import com.xnuminousx.elementaleffects.trails.SandTrail;
import com.xnuminousx.elementaleffects.trails.StaticField;
import com.xnuminousx.elementaleffects.trails.WaterRings;
import com.xnuminousx.elementaleffects.trails.WaterTrail;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class TrailGui implements Listener {
	
	HashMap<Player, Trail> trails = Main.plugin.trails;
	String prefix;
	String prefixColor = ChatColor.DARK_AQUA + "" + ChatColor.BOLD;
	Element missingEle;
	boolean doPrefix = Main.getInstance().getConfig().getBoolean("Language.Prefix.Enabled");
	boolean closeInv = Manager.closeInv();
	boolean reqEle = Manager.requireElement();
	String trailGuiName = Manager.getTrailGuiName();
	
	public String enabled(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.GREEN + " enabled!";
	}
	
	public String disabled(ChatColor color, String trailName) {
		return prefix + color + "" + ChatColor.BOLD + trailName + ChatColor.RESET + ChatColor.RED + " disabled!";
	}
	
	public String noElement(ChatColor color) {
		return prefix + color + "You don't have the necessary element! Missing element: ";
	}
	
	public String noPerm(ChatColor color) {
		return prefix + color + "You don't have the necessary permission!";
	}
	
	public static void openGUI(Player p) {
		String guiName = Manager.getTrailGuiName();
		
		Inventory inv = Bukkit.createInventory(p, 54, guiName);
		
		List<String> openInd = new ArrayList<String>();
		List<String> removeTrail = new ArrayList<String>();
		openInd.add("Use this to open the indicator GUI");
		removeTrail.add("Click here to remove your active trail");
		
		inv.setItem(13, Methods.createItem(Material.END_CRYSTAL, "Open Indicator GUI", ChatColor.DARK_AQUA, openInd));
		inv.setItem(31, Methods.createItem(Material.BARRIER, "Disable Trail", ChatColor.DARK_RED, removeTrail));
		inv.setItem(9, Methods.createItem(p, Material.GRASS_BLOCK, EarthTrail.getName(), ChatColor.GREEN, Trails.EARTH));
		inv.setItem(18, Methods.createItem(p, Material.SAND, SandTrail.getName(), ChatColor.YELLOW, Trails.SANDCLOAK));
		inv.setItem(27, Methods.createItem(p, Material.MAGMA_CREAM, LavaTrail.getName(), ChatColor.DARK_GREEN, Trails.ERUPTION));
		inv.setItem(11, Methods.createItem(p, Material.WATER_BUCKET, WaterTrail.getName(), ChatColor.AQUA, Trails.WATER));
		inv.setItem(20, Methods.createItem(p, Material.ICE, IceTrail.getName(), ChatColor.DARK_AQUA, Trails.ICE));
		inv.setItem(29, Methods.createItem(p, Material.BLACK_GLAZED_TERRACOTTA, Blood.getName(), ChatColor.DARK_RED, Trails.BLOOD));
		//inv.setItem(20, Methods.createItem(p, Material.POTION, Methods.normalizeString(Trails.HYDRO.toString()), ChatColor.BLUE, Trails.HYDRO));
		inv.setItem(15, Methods.createItem(p, Material.BLAZE_POWDER, FireTrail.getName(), ChatColor.RED, Trails.FIRE));
		//inv.setItem(24, Methods.createItem(p, Material.FIRE_CHARGE, Methods.normalizeString(Trails.FLAMEARMS.toString()), ChatColor.RED, Trails.FLAMEARMS));
		inv.setItem(24, Methods.createItem(p, Material.REDSTONE_TORCH, StaticField.getName(), ChatColor.BLUE, Trails.STATICFIELD));
		inv.setItem(33, Methods.createItem(p, Material.NETHER_STAR, Combust.getName(), ChatColor.DARK_RED, Trails.COMBUST));
		inv.setItem(17, Methods.createItem(p, Material.STRING, AirTrail.getName(), ChatColor.GRAY, Trails.AIR));
		inv.setItem(26, Methods.createItem(p, Material.COBWEB, AeroSphere.getName(), ChatColor.GRAY, Trails.AEROSPHERE));
		inv.setItem(35, Methods.createItem(p, Material.FEATHER, Float.getName(), ChatColor.DARK_GRAY, Trails.FLOAT));
		inv.setItem(51, Methods.createItem(p, Material.LEAD, ChiTrail.getName(), ChatColor.GOLD, Trails.CHI));
		inv.setItem(52, Methods.createItem(p, Material.WOODEN_SWORD, Intensity.getName(), ChatColor.GOLD, Trails.INTENSITY));
		inv.setItem(46, Methods.createItem(p, Material.NETHER_STAR, AvatarTrail.getName(), ChatColor.DARK_PURPLE, Trails.AVATAR));
		inv.setItem(47, Methods.createItem(p, Material.BEACON, ElementalRings.getName(), ChatColor.DARK_PURPLE, Trails.ELEMENTALRINGS));
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public void invClick(InventoryClickEvent event) {
		Player player = (Player)event.getWhoClicked();
		
		if (!event.getInventory().getTitle().contains(trailGuiName)) {
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
		//EARTH
		if (clickedItem.getItemMeta().getDisplayName().contains(EarthTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.EARTH, Element.EARTH, null);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(SandTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.SANDCLOAK, Element.EARTH, Element.SAND);
			new SandCloak(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(LavaTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.ERUPTION, Element.EARTH, Element.LAVA);
			new LavaTrail(player);
			return;
		//FIRE	
		} else if (clickedItem.getItemMeta().getDisplayName().contains(FireTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.FIRE, Element.FIRE, null);
			new FlameArms(player);
			return;
		/*} else if (clickedItem.getItemMeta().getDisplayName().contains(Names.flameArms())) {
			event.setCancelled(true);
			manageTrails(player, Trails.FLAMEARMS, Element.FIRE, null);
			new FlameArms(player);
			return;*/
		} else if (clickedItem.getItemMeta().getDisplayName().contains(StaticField.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.STATICFIELD, Element.FIRE, Element.LIGHTNING);
			new StaticField(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(Combust.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.COMBUST, Element.FIRE, Element.COMBUSTION);
			new Combust(player);
			return;
		//WATER	
		} else if (clickedItem.getItemMeta().getDisplayName().contains(WaterTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.WATER, Element.WATER, null);
			new WaterRings(player);
			return;
		/*} else if (clickedItem.getItemMeta().getDisplayName().contains(Names.hydro())) {
			event.setCancelled(true);
			manageTrails(player, Trails.HYDRO, Element.WATER, null);
			new WaterRings(player);
			return;*/
		} else if (clickedItem.getItemMeta().getDisplayName().contains(Blood.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.BLOOD, Element.WATER, Element.BLOOD);
			new Blood(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(IceTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.ICE, Element.WATER, Element.ICE);
			return;
		//AIR
		} else if (clickedItem.getItemMeta().getDisplayName().contains(AirTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.AIR, Element.AIR, null);
			new Cloud(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(AeroSphere.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.AEROSPHERE, Element.AIR, null);
			new AeroSphere(player);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(Float.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.FLOAT, Element.AIR, Element.FLIGHT);
			new Float(player);
			return;
		//CHI
		} else if (clickedItem.getItemMeta().getDisplayName().contains(ChiTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.CHI, Element.CHI, null);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(Intensity.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.INTENSITY, Element.CHI, null);
			return;
		//AVATAR	
		} else if (clickedItem.getItemMeta().getDisplayName().contains(AvatarTrail.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.AVATAR, Element.AVATAR, null);
			return;
		} else if (clickedItem.getItemMeta().getDisplayName().contains(ElementalRings.getName())) {
			event.setCancelled(true);
			manageTrails(player, Trails.ELEMENTALRINGS, Element.AVATAR, null);
			new ElementalRings(player);
			return;
		//INDICATOR GUI	
		} else if (clickedItem.getItemMeta().getDisplayName().contains("Open Indicator GUI")) {
			event.setCancelled(true);
			IndGui.openGui(player);
			return;
		//DISABLE TRAIL	
		} else if (clickedItem.getItemMeta().getDisplayName().contains("Disable Trail")) {
			event.setCancelled(true);
			Trail.removeTrail(player);
			player.sendMessage(prefix + ChatColor.RED + ChatColor.BOLD + "Active trail" + ChatColor.RESET + ChatColor.RED + " disabled!");
			return;
		} else {
			event.setCancelled(true);
			return;
		}
	}
	
	public void setTrail(Player player, Trails type) {
		if (trails.containsKey(player)) {
			if (type == trails.get(player).getType()) {
				trails.remove(player);
				player.sendMessage(this.disabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
			} else {
				trails.put(player, new Trail(type));
				player.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
			}
		} else {
			trails.put(player, new Trail(type));
			player.sendMessage(this.enabled(ChatColor.AQUA, Methods.normalizeString(type.toString())));
		}
	}
	
	public void manageTrails(Player player, Trails type, Element element, SubElement sub) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		
		if (Methods.hasPermission(player, "trails", type.toString().toLowerCase())) {
			if (reqEle) {
				if (bPlayer.hasElement(element) && bPlayer.hasSubElement(sub)) {
					closeInv(player);
					setTrail(player, type);
				} else {
					closeInv(player);
					player.sendMessage(this.noElement(ChatColor.RED) + missingElement(bPlayer, element, sub));
				}
			} else {
				closeInv(player);
				setTrail(player, type);
			}
		} else {
			closeInv(player);
			player.sendMessage(this.noPerm(ChatColor.RED));
		}
	}
	
	public String missingElement(BendingPlayer bPlayer, Element element, SubElement sub) {
		if (!bPlayer.hasElement(element)) {
			return element.getName();
		} else if (!bPlayer.hasSubElement(sub)) {
			return sub.getName();
		}
		return "";
	}
	
	public void closeInv(Player p) {
		if (closeInv) {
			p.closeInventory();
		}
	}
	
	
}