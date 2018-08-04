package com.xnuminousx.elementaleffects.indicators;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class MoonIndicator {

	Main plugin = Main.getInstance();
	Location location;
	Location location2;
	Location location3;
	Location location4;
	int currPoint;
	boolean reqNight = Main.plugin.getConfig().getBoolean("Indicators.Moon.RequireNight");
	boolean reqWater = Main.plugin.getConfig().getBoolean("Indicators.Moon.RequireWaterElement");
	boolean doFullMoon = Main.plugin.getConfig().getBoolean("Indicators.Moon.FullMoon.Enabled");
	boolean enhanceParticles = Main.plugin.getConfig().getBoolean("Indicators.Moon.FullMoon.EnhanceParticles");
	
	public MoonIndicator(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.moon.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player player) {
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		location = player.getLocation();
		location2 = player.getLocation();
		location3 = player.getLocation();
		location4 = player.getLocation();
		
		if (reqWater) {
			if (bPlayer.hasElement(Element.WATER)) {
				if (reqNight) {
					if (WaterAbility.isFullMoon(player.getWorld()) && enhanceParticles) {
						playFullMoon(player);
					} else if (WaterAbility.isNight(player.getWorld())) {
						playNight(player);
					}
				} else {
					if (WaterAbility.isFullMoon(player.getWorld()) && enhanceParticles) {
						playFullMoon(player);
					}
					playNight(player);
				}
			}
		} else {
			if (reqNight) {
				if (WaterAbility.isFullMoon(player.getWorld()) && enhanceParticles) {
					playFullMoon(player);
				} else if (WaterAbility.isNight(player.getWorld())) {
					playNight(player);
				}
			} else {
				if (WaterAbility.isFullMoon(player.getWorld()) && enhanceParticles) {
					playFullMoon(player);
				}
				playNight(player);
			}
		}
	}
	
	public void playNight(Player player) {
		for (int i = 0; i < 1; i++) {
			currPoint += 360 / 70;
			if (currPoint > 550) {
				currPoint = 0;
			}
			if (currPoint == 0) {
				ParticleEffect.SNOWBALL_POOF.display(player.getLocation().add(0, 0.5, 0), 0.2F, 0.2F, 0.2F, 0.3F, 10);
			}
			double angle = currPoint * Math.PI / 270 * Math.cos(Math.PI);
			double x = 1 * Math.cos(angle + i);
            double y = Math.cos(angle) + 1.5;
            double y2 = -1 * Math.sin(angle) + 1.5;
            double z = 1 * Math.sin(angle + i);
			location.add(x, y, z);
			location2.add(x, y2, z);
			location3.add(z, y, x);
			location4.add(z, y2, x);
			ParticleEffect.WATER_WAKE.display(location, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_WAKE.display(location2, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_WAKE.display(location3, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_WAKE.display(location4, 0, 0, 0, 0, 1);
			location.subtract(x, y, z);
			location2.subtract(x, y2, z);
			location3.subtract(z, y, x);
			location4.subtract(z, y2, x);
		}
	}
	
	public void playFullMoon(Player player) {
		for (int i = 0; i < 1; i++) {
			currPoint += 360 / 70;
			if (currPoint > 550) {
				currPoint = 0;
			}
			if (currPoint == 0) {
				ParticleEffect.SNOWBALL_POOF.display(player.getLocation().add(0, 1, 0), 0.6F, 0.6F, 0.6F, 0.3F, 20);
			}
			double angle = currPoint * Math.PI / 270 * Math.cos(Math.PI);
			double x = 1 * Math.cos(angle + i);
			double y = Math.cos(angle) + 1.5;
            double y2 = -1 * Math.sin(angle) + 1.5;
            double z = 1 * Math.sin(angle + i);
			location.add(x, y, z);
			location2.add(x, y2, z);
			location3.add(z, y, x);
			location4.add(z, y2, x);
			ParticleEffect.WATER_DROP.display(location, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_DROP.display(location2, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_DROP.display(location3, 0, 0, 0, 0, 1);
			ParticleEffect.WATER_DROP.display(location4, 0, 0, 0, 0, 1);
			GeneralMethods.displayColoredParticle(location, "0000D1");
			GeneralMethods.displayColoredParticle(location2, "0000D1");
			GeneralMethods.displayColoredParticle(location3, "0000D1");
			GeneralMethods.displayColoredParticle(location4, "0000D1");
			location.subtract(x, y, z);
			location2.subtract(x, y2, z);
			location3.subtract(z, y, x);
			location4.subtract(z, y2, x);
		}
	}
}