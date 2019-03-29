package com.xnuminousx.elementaleffects.indicators;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.FireAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Indicator.Indicators;

public class SunIndicator {

	Main plugin = Main.getInstance();
	Location location;
	int currPoint;
	boolean reqDay = Main.plugin.getConfig().getBoolean("Indicators.Sun.RequireDay");
	boolean reqFire = Main.plugin.getConfig().getBoolean("Indicators.Sun.RequireFireElement");
	
	public SunIndicator(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (!plugin.inds.containsKey(player) || !plugin.inds.get(player).getType().equals(Indicators.SUN)) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static String getName() {
		return Methods.normalizeString(Indicators.SUN.toString());
	}
	
	public void progress(Player player) {
		location = player.getLocation();
		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		
		if (reqFire) {
			if (bPlayer.hasElement(Element.FIRE)) {
				if (reqDay) {
					if (FireAbility.isDay(player.getWorld())) {
						playAnimation(player);
					}
				} else {
					playAnimation(player);
				}
			}
		} else {
			if (reqDay) {
				if (FireAbility.isDay(player.getWorld())) {
					playAnimation(player);
				}
			} else {
				playAnimation(player);
			}
		}
	}
	
	public void playAnimation(Player player) {
		for (int i = 0; i < 6; i++) {
			currPoint += 360/250;
			if (currPoint > 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180;
			double x = 0.4 * Math.cos(angle);
			double z = 0.4 * Math.sin(angle);
			location.add(x, 0, z);
			ParticleEffect.FLAME.display(location, 1, 0, 0, 0, 0.01F);
			location.subtract(x, 0, z);
		}
		for (int i = 0; i < 8; i++) {
			double angle = 360 / 8 * i;
			angle = Math.toRadians(angle);
			double x = 0.5 * Math.cos(angle);
			double z = 0.5 * Math.sin(angle);
			location.add(x, 0, z);
			Methods.playColoredParticle(location, 1, 0, 0, 0, 255, 115, 33);
			location.subtract(x, 0, z);
		}
		ParticleEffect.FLAME.display(GeneralMethods.getRightSide(player.getLocation(), .4).add(0, 0.7, 0), 1, 0.08F, 0.08F, 0.08F, 0.01F);
		ParticleEffect.FLAME.display(GeneralMethods.getLeftSide(player.getLocation(), .4).add(0, 0.7, 0), 1, 0.08F, 0.08F, 0.08F, 0.01F);
		ParticleEffect.SMOKE_NORMAL.display(player.getLocation().add(0, 1.7, 0), 2, 0.2F, 0.2F, 0.2F, 0F);
	}
}
