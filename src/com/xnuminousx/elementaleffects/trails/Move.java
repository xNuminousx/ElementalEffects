package com.xnuminousx.elementaleffects.trails;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.AirAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.util.ParticleEffect.BlockData;
import com.xnuminousx.elementaleffects.Main;

public class Move implements Listener {
	
	public static void earthTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Earth.Particles.Amount");
		
		byte blockByte = 0;
		Block getBlock;
		Material getMat;
		getBlock = p.getLocation().add(0, -1, 0).getBlock();
		getMat = getBlock.getType();
		boolean reqEarthBlock = Main.getInstance().getConfig().getBoolean("Trails.Earth.RequireEarthBlock");
		
		if (reqEarthBlock) {
			if (!EarthAbility.isEarthbendable(p, getBlock)) {
				getMat = null;
			} else {
				ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
						new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 1, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
				return;
			}
		} else {
			getMat = Material.GRASS;
			ParticleEffect.BLOCK_CRACK.display((ParticleEffect.ParticleData) 
					new ParticleEffect.BlockData(getMat, blockByte), (float) 0.5, (float) 1, (float) 0.5, (float) speed, amount, p.getLocation(), 500);
		}
	}
	
	public static void lavaTrail(Player p) {
		new BukkitRunnable() {
			double t = 0;
			Main plugin = Main.getInstance();
			int amount = Main.getInstance().getConfig().getInt("Trails.Lava.Particles.Amount");
			public void run() {
				t += Math.PI / 16;
				Location loc = p.getLocation();
				for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 5) {
					double x = 0.3 * (4 * Math.PI - t) * Math.cos(t + phi);
					double y = 0.2 * t;
					double z = 0.3 * (4 * Math.PI - t) * Math.sin(t + phi);
					loc.add(x, y, z);
					GeneralMethods.displayColoredParticle(loc, "f47142", 0F, 0F, 0F);
					ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.LAVA, (byte) 0), 0F, 0F, 0F, 0F, amount, loc, 257D);
					loc.subtract(x, y, z);
				
					if (t >= 4 * Math.PI) {
						ParticleEffect.LAVA.display(p.getLocation().add(0, 3, 0), 0F, 0F, 0F, 0, 20);
						t = 0;
					}
					if (plugin.lava.isEmpty()) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void staticField(Player p) {
		new BukkitRunnable() {
			int amount = Main.getInstance().getConfig().getInt("Trails.Lightning.Particles.Amount");
			int fieldamount = Main.getInstance().getConfig().getInt("Trails.Lightning.Field.Amount");
			int speed = Main.getInstance().getConfig().getInt("Trails.Lightning.Field.Speed");
			boolean field = Main.getInstance().getConfig().getBoolean("Trails.Lightning.Field.Enabled");
			float size = 2;
			int points = 60;
			int currPoint;
			Main plugin = Main.getInstance();
			public void run() {
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
				if (plugin.lightning.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void sandCloak(Player p) {
		new BukkitRunnable() {
			double radius = Main.getInstance().getConfig().getDouble("Trails.Sand.Cloak.Radius");
			Main plugin = Main.getInstance();
			public void run() {
				for (int i = -180; i < 180; i += 10) {
					Location loc = p.getLocation().clone().add(0, radius, 0);
					Location locUp = p.getLocation().clone().add(0, radius, 0);
					Location locDown = p.getLocation().clone().add(0, radius, 0);
					double x = Math.cos(Math.toRadians(i)) * radius;
					double z = Math.sin(Math.toRadians(i)) * radius;
					
					GeneralMethods.displayColoredParticle(loc.add(x, 0.5, z), "FBFFBA");
					GeneralMethods.displayColoredParticle(locDown.add(x, 0, z), "FBFFBA");
					GeneralMethods.displayColoredParticle(locUp.add(x, 1, z), "FBFFBA");
					
					if (plugin.sand.isEmpty()) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void sandTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Sand.Particles.Amount");
		
		ParticleEffect.BLOCK_DUST.display(new BlockData(Material.SAND, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, p.getLocation().add(0, 1, 0), 257D);
	}
	
	public static void fireTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Fire.Particles.Amount");
		
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Fire.DisappearInWater");
		boolean boilEffect = Main.getInstance().getConfig().getBoolean("Trails.Fire.BoilEffect");
		Material getBlock = p.getLocation().add(0, 1, 0).getBlock().getType();
		
		if (vanishInWater) {
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				if (boilEffect) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
				}
				return;
			} else {
				ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
				ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		} else {
			if (boilEffect) {
				if (getBlock.equals(Material.STATIONARY_WATER)) {
					ParticleEffect.BUBBLE.display(p.getLocation().add(0, 1, 0), 0.5F, 1, 0.5F, 0.5F, 2);
					return;
				}
			}
			ParticleEffect.SMOKE.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 2);
			ParticleEffect.FLAME.display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
		}
	}
	
	public static void waterTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Water.Particles.Amount");
		
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.5F, 2);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), (float) 0.3, (float) 0.3, (float) 0.3, (float) speed, amount);
	}
	
	public static void waterTrail2(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Water2.Particles.Amount");
		
		GeneralMethods.displayColoredParticle(p.getLocation(), "A8A8FF", 0.3F, 0.3F, 0.3F);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 1, 0), "A8A8FF", 0.3F, 0.3F, 0.3F);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 2, 0), "A8A8FF", 0.3F, 0.3F, 0.3F);
		ParticleEffect.SPLASH.display(p.getLocation(), 0.2F, 0.2F, 0.2F, 0.2F, 5);
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, 0F, 5);
		ParticleEffect.SPLASH.display(p.getLocation().add(0, 2, 0), 0.2F, 0.2F, 0.2F, 0F, 5);
		ParticleEffect.DRIP_WATER.display(p.getLocation(), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 1, 0), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.DRIP_WATER.display(p.getLocation().add(0, 2, 0), 0.2F, 0.2F, 0.2F, speed, amount);
	}
	
	public static void iceBoots(Player p) {
		new BukkitRunnable() {
			int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Boots.Amount");
			float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Boots.Speed");
			Main plugin = Main.getInstance();
			public void run() {
				ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 257D);
				ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 257D);
				if (plugin.ice.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void iceTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Ice.Particles.Amount");
		
		ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.PACKED_ICE, (byte) 0), 0.5F, 0.5F, 0.5F, speed, amount, p.getLocation().add(0, 1, 0), 257D);
		ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 0.2F, 0.2F, 0.2F, speed, amount);
		ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 0.2F, 0.2F, 0.2F, speed, amount);
	}
	
	public static void aeroSphere(Player p) {
		new BukkitRunnable() {
			double yaw;
			public Random random = new Random("Numin goes poopy".hashCode());
			Main plugin = Main.getInstance();
			public void run() {
				double size = Main.getInstance().getConfig().getDouble("Trails.Air2.Sphere.Size");
				Location baseLoc = p.getLocation().clone().add(0, size / 2, 0);
			    Location fakeLoc = baseLoc.clone();
			    fakeLoc.setPitch(0.0F);
			    fakeLoc.setYaw((float)(yaw += 40.0D));
			    Vector direction = fakeLoc.getDirection();
			    for (double j = -180.0D; j <= 180.0D; j += 45.0D) {
			        Location tempLoc = fakeLoc.clone();
			        Vector newDir = direction.clone().multiply(size * Math.cos(Math.toRadians(j)));
			        tempLoc.add(newDir);
			        tempLoc.setY(tempLoc.getY() + size * Math.sin(Math.toRadians(j)));
			        if (random.nextInt(30) == 0) {
			        	ParticleEffect.MOB_SPELL.display(255.0F, 255.0F, 255.0F, 0.003F, 0, tempLoc, 257.0D);
			        } else {
			        	ParticleEffect.MOB_SPELL_AMBIENT.display(tempLoc, 0.2F, 0.2F, 0.2F, 0.0F, 2);
			        }
			        if (plugin.air2.isEmpty()) {
						this.cancel();
					}
			    }
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void ffloat(Player p) {
		new BukkitRunnable() {
			Main plugin = Main.getInstance();
			float speed = Main.getInstance().getConfig().getInt("Trails.Flight.Particles.Speed");
			int amount = Main.getInstance().getConfig().getInt("Trails.Flight.Particles.Amount");
			boolean enablesparks = Main.getInstance().getConfig().getBoolean("Trails.Flight.Sparks.Enabled");
			public void run() {
				if (p.isSprinting()) {
					ParticleEffect.SWEEP.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0, 1);
					if (enablesparks) {
						ParticleEffect.FIREWORKS_SPARK.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, 0.05F, 3);
					}
				}
				ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, speed, amount);
				ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, speed, amount);
				ParticleEffect.CLOUD.display(GeneralMethods.getRightSide(p.getLocation(), -.55).add(0, 0, 0), 0F, 0F, 0F, speed, amount);
				ParticleEffect.CLOUD.display(GeneralMethods.getLeftSide(p.getLocation(), -.55).add(0, 0, 0), 0F, 0F, 0F, speed, amount);
				
				if (plugin.flight.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void playWaterRings(Player p) {
		new BukkitRunnable() {
			int ringspeed = Main.getInstance().getConfig().getInt("Trails.Water2.Ring.Speed");
			float size = Main.getInstance().getConfig().getInt("Trails.Water2.Ring.Size");
			int points = 60;
			int currPoint;
			Main plugin = Main.getInstance();
			public void run() {
				for (int i = 0; i < ringspeed; ++i) {
					currPoint += 360 / points;
					if (currPoint > 360) {
						currPoint = 0;
					}
					double angle = currPoint * 3.141592653589793D / 180.0D;
					double x = size * Math.cos(angle);
					double z = size * Math.sin(angle);
					Location loc = p.getLocation().add(x, 1, z);
					ParticleEffect.SPLASH.display(loc, 0f, 0f, 0f, 0, 2);
					ParticleEffect.DRIP_WATER.display(loc, 0f, 0f, 0f, 0, 2);
					
					if (plugin.water2.isEmpty()) {
						this.cancel();
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void airTrail(Player p) {
		float speed = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Speed");
		int amount = Main.getInstance().getConfig().getInt("Trails.Air.Particles.Amount");
		boolean doCloud = Main.getInstance().getConfig().getBoolean("Trails.Air.CloudEffect");
		boolean vanishInWater = Main.getInstance().getConfig().getBoolean("Trails.Air.DisappearInWater");
		Material getBlock = p.getLocation().add(0, -1, 0).getBlock().getType();
		
		if (vanishInWater) {
			
			if (getBlock.equals(Material.STATIONARY_WATER)) {
				return;
			} else {
				if (doCloud) {
					if (getBlock.equals(Material.AIR)) {
						AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
						return;
					} else {
						AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
						return;
					}
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			}
		} else {
			if (doCloud) {
				if (getBlock.equals(Material.AIR)) {
					AirAbility.getAirbendingParticles().display(p.getLocation(), (float) 0.5, 0, (float) 0.5, (float) speed, 5);
					return;
				} else {
					AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
					return;
				}
			} else {
				AirAbility.getAirbendingParticles().display(p.getLocation().add(0, 1, 0), (float) 0.5, (float) 0.5, (float) 0.5, (float) speed, amount);
				return;
			}
		}
	}
	
	public static void chiTrail(Player p) {
		int amount = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Amount");
		float speed = Main.getInstance().getConfig().getInt("Trails.Chi.Particles.Speed");
		boolean doMagic = Main.getInstance().getConfig().getBoolean("Trails.Chi.Particles.DoMagicCrit"); 
		
		ParticleEffect.CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, (float) speed, amount);
		if (doMagic) {
			ParticleEffect.MAGIC_CRIT.display(p.getLocation().add(0, 1, 0), 0.5F, 0.5F, 0.5F, (float) speed, amount);
		}
		
	}
	
	public static void chiTrail2(Player p) {
		int amount = Main.getInstance().getConfig().getInt("Trails.Chi2.Particles.Amount");
		float speed = Main.getInstance().getConfig().getInt("Trails.Chi2.Particles.Speed");
		boolean dash = Main.getInstance().getConfig().getBoolean("Trails.Chi2.DashEffect.Enabled");
		int dashamount = Main.getInstance().getConfig().getInt("Trails.Chi2.DashEffect.Amount");
		float dashspeed = Main.getInstance().getConfig().getInt("Trails.Chi2.DashEffect.Speed");
		boolean enablesound = Main.getInstance().getConfig().getBoolean("Trails.Chi2.Sound.Enabled");
		
		ParticleEffect.CRIT.display(p.getLocation(), 0.2F, 0.2F, 0.2F, (float) speed, amount);
		ParticleEffect.CLOUD.display(p.getLocation(), 0.3F, 0.3F, 0.3F, (float) speed, amount);
		GeneralMethods.displayColoredParticle(p.getLocation().add(0, 1, 0), "F4CE42", (float) Math.random(), (float) Math.random(), (float) Math.random());
		
		if (dash) {
			if (!p.isOnGround()) {
				if (enablesound) {
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 0.25F, 1F);
				}
				ParticleEffect.SWEEP.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, dashspeed, dashamount);
				ParticleEffect.SWEEP.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, dashspeed, dashamount);
			}
		}
	}
	
	public static void fireTrail2(Player p) {
		new BukkitRunnable() {
			int amount = Main.getInstance().getConfig().getInt("Trails.Fire2.Particles.Amount");
			float speed = Main.getInstance().getConfig().getInt("Trails.Fire2.Particles.Speed");
			boolean enablerings = Main.getInstance().getConfig().getBoolean("Trails.Fire2.Rings.Enabled");
			int anglee;
			int anglee2;
			int point;
			int point2;
			Main plugin = Main.getInstance();
			public void run() {
				ParticleEffect.FLAME.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 0.15F, 0.15F, 0.15F, speed, amount);
				ParticleEffect.FLAME.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 0.15F, 0.15F, 0.15F, speed, amount);
				
				if (enablerings) {
					anglee+=10;
					Location location = GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0);
					double angle = (anglee * Math.PI / 180);
					double xRotation = 3.141592653589793D / 3 * 2.1;
					Vector v = new Vector(Math.cos(angle + point), Math.sin(angle), 0.0D).multiply(1);
					Vector v1 = v.clone();
					rotateAroundAxisX(v, xRotation);
					rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
					rotateAroundAxisX(v1, -xRotation);
					rotateAroundAxisY(v1, -((location.getYaw() * Math.PI / 180) - 1.575));

					ParticleEffect.CRIT.display(0F, 0F, 0F, 0F, 1, location.clone().add(v1), 257D);
					ParticleEffect.CRIT.display(0F, 0F, 0F, 0F, 1, location.clone().add(v), 257D);
					GeneralMethods.displayColoredParticle(location.clone().add(v), "D86827", 0.0F, 0.0F, 0.0F);
					GeneralMethods.displayColoredParticle(location.clone().add(v1), "D86827", 0.0F, 0.0F, 0.0F);
					if (anglee == 360) {
						anglee = 0;
					}
					
					anglee2+=10;
					Location locationRight = GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0);
					double angleRight = (anglee * Math.PI / 180);
					double xRotationRight = 3.141592653589793D / 3 * 2.1;
					Vector vRight = new Vector(Math.cos(angleRight + point2), Math.sin(angleRight), 0.0D).multiply(1);
					Vector v1Right = vRight.clone();
					rotateAroundAxisX(vRight, xRotationRight);
					rotateAroundAxisY(vRight, -((locationRight.getYaw() * Math.PI / 180) - 1.575));
					rotateAroundAxisX(v1Right, -xRotationRight);
					rotateAroundAxisY(v1Right, -((locationRight.getYaw() * Math.PI / 180) - 1.575));

					ParticleEffect.CRIT.display(0F, 0F, 0F, 0F, 1, locationRight.clone().add(v1Right), 257D);
					ParticleEffect.CRIT.display(0F, 0F, 0F, 0F, 1, locationRight.clone().add(vRight), 257D);
					GeneralMethods.displayColoredParticle(locationRight.clone().add(vRight), "D86827", 0.0F, 0.0F, 0.0F);
					GeneralMethods.displayColoredParticle(locationRight.clone().add(v1Right), "D86827", 0.0F, 0.0F, 0.0F);
					if (anglee2 == 360) {
						anglee2 = 0;
					}
				}
				
				if (!p.isOnGround()) {
					ParticleEffect.FLAME.display(GeneralMethods.getRightSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, 0.1F, 10);
					ParticleEffect.FLAME.display(GeneralMethods.getLeftSide(p.getLocation(), .55).add(0, 1.2, 0), 0F, 0F, 0F, 0.1F, 10);
				}
				
				if (plugin.fire2.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void elementalRings(Player p) {
		new BukkitRunnable() {
			int anglee;
			int point;
			int currPoint;
			Main plugin = Main.getInstance();
			public void run() {
				for (int i = 0; i < 1.2; ++i) {
					currPoint += 360 / 60;
					if (currPoint > 360) {
						currPoint = 0;
					}
					double angle = currPoint * 3.141592653589793D / 180.0D;
					double x = 2 * Math.cos(angle);
					double z = 2 * Math.sin(angle);
					Location loc = p.getLocation().add(x, 1, z);
					ParticleEffect.FLAME.display(loc, 0f, 0f, 0f, 0, 2);
				}
				
				anglee+=12;
				Location location = p.getLocation().add(0, 1, 0);
				double angle = (anglee * Math.PI / 180);
				double xRotation = 3.141592653589793D / 3 * 2.1;
				Vector v = new Vector(Math.cos(angle + point), Math.sin(angle), 0.0D).multiply(1.7);
				Vector negV = new Vector(-Math.cos(angle + point), -Math.sin(angle), 0.0D).multiply(1.7);
				rotateAroundAxisX(v, xRotation);
				rotateAroundAxisY(v, -((location.getYaw() * Math.PI / 180) - 1.575));
				rotateAroundAxisX(negV, -xRotation);
				rotateAroundAxisY(negV, -((location.getYaw() * Math.PI / 180) - 1.575));

				ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.WATER, (byte) 0), 0F, 0F, 0F, 0F, 5, location.clone().add(v), 257D);
				GeneralMethods.displayColoredParticle(location.clone().add(v), "06C1FF", 0.0F, 0.0F, 0.0F);
				ParticleEffect.BLOCK_CRACK.display(new BlockData(Material.DIRT, (byte) 0), 0F, 0F, 0F, 0F, 5, location.clone().add(negV), 257D);
				GeneralMethods.displayColoredParticle(location.clone().add(negV), "754719", 0.0F, 0.0F, 0.0F);
				if (anglee == 360) {
					anglee = 0;
				}
				if (plugin.avatar2.isEmpty()) {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);
	}
	
	public static void avatarTrail(Player p) {
		//Fire
		fireTrail(p);
		//Water
		waterTrail(p);
		//Air
		airTrail(p);
		//Earth
		earthTrail(p);
	}
	
	private static Vector rotateAroundAxisX(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double y = v.getY() * cos - v.getZ() * sin;
		double z = v.getY() * sin + v.getZ() * cos;
		return v.setY(y).setZ(z);
	}

	private static Vector rotateAroundAxisY(Vector v, double angle) {
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;
		return v.setX(x).setZ(z);
	}

}
