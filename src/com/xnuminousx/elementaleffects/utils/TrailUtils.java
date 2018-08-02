package com.xnuminousx.elementaleffects.utils;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.xnuminousx.elementaleffects.Main;

public class TrailUtils {
	
	Main plugin = Main.getInstance();
	
	/*
	 * Grabs the trail that the @player currently has equipped.
	 */
	public static ArrayList<Player> getActiveTrail(Player player) {
		Main plugin = Main.getInstance();
		
		if (plugin.earth.contains(player)) {
			return plugin.earth;
		} else if (plugin.sand.contains(player)) {
			return plugin.sand;
		} else if (plugin.lava.contains(player)) {
			return plugin.lava;
		} else if (plugin.fire.contains(player)) {
			return plugin.fire;
		} else if (plugin.flamearms.contains(player)) {
			return plugin.flamearms;
		} else if (plugin.lightning.contains(player)) {
			return plugin.lightning;
		} else if (plugin.water.contains(player)) {
			return plugin.water;
		} else if (plugin.hydro.contains(player)) {
			return plugin.hydro;
		} else if (plugin.ice.contains(player)) {
			return plugin.ice;
		} else if (plugin.air.contains(player)) {
			return plugin.air;
		} else if (plugin.aero.contains(player)) {
			return plugin.aero;
		} else if (plugin.flight.contains(player)) {
			return plugin.flight;
		} else if (plugin.chi.contains(player)) {
			return plugin.chi;
		} else if (plugin.intensity.contains(player)) {
			return plugin.intensity;
		} else if (plugin.avatar.contains(player)) {
			return plugin.avatar;
		} else if (plugin.elementrings.contains(player)) {
			return plugin.elementrings;
		} else {
			return null;
		}
	}
	
	/*
	 * Removes a specific trail from @player.
	 */
	public static void removeActiveTrail(ArrayList<Player> object, Player player) {
		if (object.contains(player)) {
			object.remove(player);
		}
	}
	
	/*
	 * Removes all active trails from @player.
	 */
	public static void removeActiveTrails(Player p) {
		Main plugin = Main.getInstance();
		
		plugin.elementrings.remove(p);
		plugin.avatar.remove(p);
		plugin.fire.remove(p);
		plugin.flamearms.remove(p);
		plugin.lightning.remove(p);
		plugin.water.remove(p);
		plugin.hydro.remove(p);
		plugin.ice.remove(p);
		plugin.air.remove(p);
		plugin.aero.remove(p);
		plugin.flight.remove(p);
		plugin.earth.remove(p);
		plugin.sand.remove(p);
		plugin.lava.remove(p);
		plugin.chi.remove(p);
		plugin.intensity.remove(p);
	}
	
	/*
	 * Equips @player with a specific trail.
	 */
	public static void setActiveTrail(String trailName, Player p) {
		Main plugin = Main.getInstance();
		
		if (trailName == Names.earthTrail()) {
			plugin.earth.add(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.lavaTrail()) {
			plugin.lava.add(p);
			plugin.sand.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.sandyCloak()) {
			plugin.sand.add(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.fireTrail()) {
			plugin.fire.add(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.flameArms()) {
			plugin.flamearms.add(p);
			plugin.fire.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.staticField()) {
			plugin.lightning.add(p);
			plugin.flamearms.remove(p);
			plugin.fire.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.waterTrail()) {
			plugin.water.add(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.hydro()) {
			plugin.hydro.add(p);
			plugin.water.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.iceBoots()) {
			plugin.ice.add(p);
			plugin.hydro.remove(p);
			plugin.water.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.airTrail()) {
			plugin.air.add(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.aeroSphere()) {
			plugin.aero.add(p);
			plugin.air.remove(p);
			plugin.flight.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.flight()) {
			plugin.flight.add(p);
			plugin.aero.remove(p);
			plugin.air.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.chiTrail()) {
			plugin.chi.add(p);
			plugin.intensity.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
		} else if (trailName == Names.intensity()) {
			plugin.intensity.add(p);
			plugin.hydro.remove(p);
			plugin.water.remove(p);
			plugin.ice.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.avatar.remove(p);
			plugin.elementrings.remove(p);
			plugin.chi.remove(p);
		} else if (trailName == Names.avatarTrail()) {
			plugin.avatar.add(p);
			plugin.elementrings.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		} else if (trailName == Names.elementalRings()) {
			plugin.elementrings.add(p);
			plugin.avatar.remove(p);
			plugin.fire.remove(p);
			plugin.flamearms.remove(p);
			plugin.lightning.remove(p);
			plugin.water.remove(p);
			plugin.hydro.remove(p);
			plugin.ice.remove(p);
			plugin.air.remove(p);
			plugin.aero.remove(p);
			plugin.flight.remove(p);
			plugin.earth.remove(p);
			plugin.sand.remove(p);
			plugin.lava.remove(p);
			plugin.chi.remove(p);
			plugin.intensity.remove(p);
		}
	}
}