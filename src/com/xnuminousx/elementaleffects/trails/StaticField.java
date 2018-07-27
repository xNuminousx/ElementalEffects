package com.xnuminousx.elementaleffects.trails;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.xnuminousx.elementaleffects.Main;

public class StaticField {
	
	int amount = Main.getInstance().getConfig().getInt("Trails.Lightning.Particles.Amount");
	int fieldamount = Main.getInstance().getConfig().getInt("Trails.Lightning.Field.Amount");
	int speed = Main.getInstance().getConfig().getInt("Trails.Lightning.Field.Speed");
	boolean field = Main.getInstance().getConfig().getBoolean("Trails.Lightning.Field.Enabled");
	float size = 2;
	int points = 60;
	int currPoint;
	Main plugin = Main.getInstance();

	public StaticField(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if (plugin.lightning.isEmpty()) {
					this.cancel();
				}
				progress(player);
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
				GeneralMethods.displayColoredParticle(loc, "01E1FF", (float) Math.random(), (float) Math.random(), (float) Math.random());
				ParticleEffect.MAGIC_CRIT.display(negloc, (float) Math.random(), (float) Math.random(), (float) Math.random(), 0.05F, fieldamount);
			}
		}
		ParticleEffect.MAGIC_CRIT.display(p.getLocation(), (float) Math.random(), (float) Math.random(), (float) Math.random(), 0.05F, amount);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 1, 0), "01E1FF", (float) Math.random(), (float) Math.random(), (float) Math.random());
	}
}
