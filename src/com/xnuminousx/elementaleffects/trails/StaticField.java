package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;
import com.xnuminousx.elementaleffects.config.Manager;
import com.xnuminousx.elementaleffects.utils.Methods;
import com.xnuminousx.elementaleffects.utils.Trail.Trails;

public class StaticField {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.StaticField.Amount");
	int speed = Main.getInstance().getConfig().getInt("Trails.StaticField.Speed");
	boolean field = Main.getInstance().getConfig().getBoolean("Trails.StaticField.Enabled");
	float size = 2;
	int points = 60;
	int currPoint;
	Main plugin = Main.getInstance();

	public StaticField(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!plugin.trails.containsKey(player) || !plugin.trails.get(player).getType().equals(Trails.STATICFIELD)) {
					this.cancel();
				}
				BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
				if (Manager.requireElement()) {
					if (bPlayer.hasElement(Element.FIRE)) {
						progress(player);
					}
				} else {
					progress(player);
				}
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public void progress(Player p) {
		if (field) {
			for (int i = 0; i < speed; ++i) {
				currPoint += 360 / points;
				if (currPoint > 360) {
					currPoint = 0;
				}
				double angle = currPoint * 3.141592653589793D / 180.0D;
				double x = size * Math.cos(angle);
				double z = size * Math.sin(angle);
				Location loc = p.getLocation().add(x, 1, z);
				Location negloc = p.getLocation().add(-x, 1, -z);
				Methods.playColoredParticle(loc, 1, (float) Math.random(), (float) Math.random(), (float) Math.random(), 1, 255, 255);
				ParticleEffect.CRIT_MAGIC.display(negloc, amount, (float) Math.random(), (float) Math.random(), (float) Math.random(), 0.05F);
			}
		}
		ParticleEffect.CRIT_MAGIC.display(p.getLocation(), amount, (float) Math.random(), (float) Math.random(), (float) Math.random(), 0.05F);
		Methods.playColoredParticle(p.getLocation().add(0, 1, 0), 1, (float) Math.random(), (float) Math.random(), (float) Math.random(), 1, 255, 255);
	}
}
