package com.xnuminousx.elementaleffects.indicators;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.CoreAbility;
import com.projectkorra.projectkorra.avatar.AvatarState;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.BlockData;
import com.xnuminousx.elementaleffects.Main;

public class AvatarStateInd {
	
	private Main plugin = Main.getInstance();
	Location location;
	private boolean playSpark;
	private boolean playEyeGlow = Main.plugin.getConfig().getBoolean("Indicators.AvatarState.PlayEyeGlow");
	private boolean reqAS = Main.plugin.getConfig().getBoolean("Indicators.AvatarState.RequireAvatarState");
	private int currPoint;

	public AvatarStateInd(Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if (plugin.avatarstate.isEmpty()) {
					this.cancel();
				}
				progress(player);
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		location = p.getLocation();
		if (reqAS) {
			if (CoreAbility.hasAbility(p, AvatarState.class)) {
				playAnimations(p, location);
			} else {
				playSpark = true;
			}
		} else {
			playAnimations(p, location);
		}
	}
	
	public void playAnimations(Player p, Location location) {
		Location rightEye = GeneralMethods.getRightSide(p.getEyeLocation().add(p.getEyeLocation().getDirection().multiply(0.36)), 0.18);
		Location leftEye = GeneralMethods.getLeftSide(p.getEyeLocation().add(p.getEyeLocation().getDirection().multiply(0.36)), 0.18);
		
		if (playEyeGlow) {
			//White
			GeneralMethods.displayColoredParticle(rightEye, "FFFFFF");
			GeneralMethods.displayColoredParticle(leftEye, "FFFFFF");
			
			//Blue
			GeneralMethods.displayColoredParticle(rightEye, "00FFF7");
			GeneralMethods.displayColoredParticle(leftEye, "00FFF7");
		}
	
		for (int i = 0; i < 6; i++) {
			currPoint += 360 / 200;
			if (currPoint > 360) {
				currPoint = 0;
			}
			double angle = currPoint * Math.PI / 180 * Math.cos(Math.PI);
			double x = 0.05 * (Math.PI * 10 - angle) * Math.cos(angle + i);
            double y = 0.8 * Math.cos(angle) + 1;
            double z = 0.05 * (Math.PI * 10 - angle) * Math.sin(angle + i);
			location.add(x, y, z);
			if (new Random().nextInt(4) == 0) {
				ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.PORTAL, (byte) 0), 0F, 0F, 0F, 0F, 1, location, 257D);
			}
			ParticleEffect.PORTAL.display(location, 0, 0, 0, 0, 2);
			location.subtract(x, y, z);
		}
		
		if (new Random().nextInt(7) == 0) {
			ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 1, 1, 1, 0.1F, 1);
			ParticleEffect.MOB_SPELL.display(p.getLocation(), 1, 1, 1, 1, 2);
		}
		if (playSpark) {
			ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 0, 0, 0, 0.2F, 20);
			playSpark = false;
		}
	}
}
