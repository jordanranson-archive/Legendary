package net.minecraft.src;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class LegendaryEnchantmentHelper 
{
	public static void frozenEffect(EntityLiving entity) 
	{
		entity.motionX *= 0.5D;
		entity.motionZ *= 0.5D;

		for(int i = 0; i < 4; i++)
		{
			LegendaryEnchantmentHelper.spawnParticle(entity, "frozenEnchant");
		}
		
		if(entity.rand.nextInt(95) == 0)
		{
			for (int var1 = 0; var1 < 3; ++var1)
			{
				int var2 = MathHelper.floor_double(entity.posX + (double)((float)(var1 % 2 * 2 - 1) * 0.25F));
				int var3 = MathHelper.floor_double(entity.posY);
				int var4 = MathHelper.floor_double(entity.posZ + (double)((float)(var1 / 2 % 2 * 2 - 1) * 0.25F));

				if (entity.worldObj.getBlockId(var2, var3, var4) == 0 && Block.snow.canPlaceBlockAt(entity.worldObj, var2, var3, var4))
				{
					entity.worldObj.setBlockWithNotify(var2, var3, var4, Block.snow.blockID);
				}
			}
		}
	}
	
	public static void curseDamageEffect(EntityLiving entity)
	{
		Random random = new Random();
		if(random.nextInt(3) == 0)
		{
			entity.worldObj.spawnEntityInWorld(new EntityHealthOrb(entity.worldObj, entity.posX, entity.posY, entity.posZ, 1));
		}
	}

	public static void afflictionDamageEffect(EntityLiving entity)
	{
		for(int i = 0; i < 11; i++)
		{
			LegendaryEnchantmentHelper.spawnParticle(entity, "cursedEnchant");
		}
		
		Random random = new Random();
		if(random.nextInt(5) == 0)
		{
			entity.addPotionEffect(new PotionEffect(Potion.curse.id, 20 * 5, 0));
		}
	}
	
	public static void impact(EntityLiving entity, int radius, String impactType)
	{
		List entities = getEntitiesInRange(entity, radius);
		EntityLiving target;
		
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) instanceof EntityLiving)
			{
				target = (EntityLiving)entities.get(i);
				
				if(impactType == "flameEnchant")
				{
					target.setFire(2);
				}
				
				if(impactType == "frozenEnchant")
				{
					target.addPotionEffect(new PotionEffect(Potion.frozen.id, 20 * 3, 0));
				}
				
				if(impactType == "cursedEnchant")
				{
					target.addPotionEffect(new PotionEffect(Potion.curse.id, 20 * 5, 0));
				}
				
				if(impactType == "chargedEnchant")
				{
					double posX = target.posX - entity.posX;
					double posY = target.posY + (double)target.getEyeHeight() - entity.posY;
					double posZ = target.posZ - entity.posZ;
					double velocity = (double)MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
					double amplifier = 1.5D;
					
					if (velocity != 0.0D)
					{
						posX /= velocity;
						posY /= velocity;
						posZ /= velocity;
						target.motionX += posX * amplifier;
						target.motionY += posY * (amplifier / 1.35D);
						target.motionZ += posZ * amplifier;
					}
				}
			}
		}
	}
	
	public static void impactEffect(EntityLiving entity, double radius, String impactType)
	{
		double x;
		double z;
		Random random = new Random();
		int offset = random.nextInt(90);
		
		for(int a = offset; a < 360 + offset; a += 15)
		{
			x = radius * Math.cos(Math.toRadians(a));
			z = radius * Math.sin(Math.toRadians(a));
			
			entity.worldObj.spawnParticle(
				impactType,
				entity.posX + x + ((double)entity.width / 2),
				entity.posY - ((double)entity.height - 0.25D), 
				entity.posZ + z + ((double)entity.width / 2),
				-entity.motionX, 0.1D, -entity.motionZ
			);
		}
	}
	
	public static void fieryAuraAttack(EntityLiving entity, int radius)
	{
		List entities = getEntitiesInRange(entity, radius);
		
		Random random = new Random();
		Entity attackTarget = null;
		double targetDistance;
		Entity curTarget = null;
		double curDistance;
		
		for (int i = 0; i < entities.size(); ++i)
		{	
			curTarget = (Entity)entities.get(i);
			
			if(curTarget instanceof EntityMob || curTarget instanceof EntitySlime)
			{
				if(attackTarget != null)
				{
					curDistance = curTarget.getDistance(entity.posX, entity.posY, entity.posZ) / (double)radius;
					targetDistance = attackTarget.getDistance(entity.posX, entity.posY, entity.posZ) / (double)radius;
					
					if(curDistance < targetDistance)
					{
						attackTarget = curTarget;
					}
				}
				else
				{
					attackTarget = curTarget;
				}
			}
		}
		
		if(attackTarget != null)
		{
			targetDistance = attackTarget.getDistance(entity.posX, entity.posY, entity.posZ) / (double)radius;
			if (targetDistance <= 1.0D)
			{
				EntityArrowFlame arrow = new EntityArrowFlame(entity.worldObj, entity, 0.8F);
				arrow.setDamage(1.5F);
				arrow.setFire(100);
				arrow.canBePickedUp = 0;
				
				double x = attackTarget.posX - entity.posX;
				double y = attackTarget.posY + (double)attackTarget.getEyeHeight() - 1.100000023841858D - arrow.posY;
				double z = attackTarget.posZ - entity.posZ;
				float dist = MathHelper.sqrt_double(x * x + z * z) * 0.2F;
				arrow.setThrowableHeading(x, y + (double)dist, z, 1.6F, 12.0F);
				
				entity.worldObj.playSoundAtEntity(entity, "random.bow", 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 0.5F * 0.5F);

				if (!entity.worldObj.isRemote)
				{
					entity.worldObj.spawnEntityInWorld(arrow);
					entity.attackCooldown = entity.maxAttackCooldown;
				}
			}
		}
	}
	
	public static void chargedAuraAttack(EntityLiving attacker, EntityLiving target)
	{
		Random random = new Random();
		
		if(random.nextInt(9) == 0)
		{
			attacker.worldObj.playSoundAtEntity(target, "ambient.weather.thunder", 1.0F, 0.8F + random.nextFloat() * 0.2F);
			attacker.worldObj.playSoundAtEntity(target, "random.explode", 1.0F, 0.8F + random.nextFloat() * 0.2F);
			
			for(int j = 0; j < 4; j++)
			{
				LegendaryEnchantmentHelper.spawnParticle(target, "chargedEnchant");
			}
			
			double posX = target.posX - attacker.posX;
			double posY = target.posY + (double)target.getEyeHeight() - attacker.posY;
			double posZ = target.posZ - attacker.posZ;
			double velocity = (double)MathHelper.sqrt_double(posX * posX + posY * posY + posZ * posZ);
			double amplifier = 3.0D;
			
			if (velocity != 0.0D)
			{
				posX /= velocity;
				posY /= velocity;
				posZ /= velocity;
				target.motionX += posX * amplifier;
				target.motionY += posY * (amplifier / 1.5D);
				target.motionZ += posZ * amplifier;
			}
			
			target.setFire(1);
		}
	}
	
	public static void chainLightning(EntityLiving target, EntityLiving attacker, List attackers, int radius, int numTargets, int step)
	{
		attackers.add(attacker);
		List entities = getEntitiesInRange(target, radius);
		
		Random random = new Random();
		Entity attackTarget = null;
		double targetDistance;
		Entity curTarget = null;
		double curDistance;

		if (!target.worldObj.isRemote)
		{
			target.recentlyShocked = target.maxRecentlyShocked;
			target.attackEntityFrom(DamageSource.chainLightning, 1);
			// TODO: do stuff to 'target'
		}

		for (int i = 0; i < entities.size(); ++i)
		{	
			curTarget = (Entity)entities.get(i);
			
			if(curTarget instanceof EntityLiving && LegendaryEnchantmentHelper.isViableLightningTarget(attackers, (EntityLiving)curTarget))
			{
				if(attackTarget != null)
				{
					curDistance = curTarget.getDistance(target.posX, target.posY, target.posZ) / (double)radius;
					targetDistance = attackTarget.getDistance(target.posX, target.posY, target.posZ) / (double)radius;
					
					if(curDistance < targetDistance)
					{
						attackTarget = curTarget;
					}
				}
				else
				{
					attackTarget = curTarget;
				}
			}
		}
		
		if(attackTarget != null)
		{
			if(target != null)
			{
				LegendaryEnchantmentHelper.lightingBoltEffect(target, (EntityLiving)attackTarget);
				LegendaryEnchantmentHelper.lightingBoltEffect(target, (EntityLiving)attackTarget);
			}
		
			if(step < numTargets)
			{
				if(((EntityLiving)attackTarget).recentlyShocked == 0)
				{
					LegendaryEnchantmentHelper.chainLightning((EntityLiving)attackTarget, target, attackers, radius, numTargets, step + 1);
				}
			}
		}
	}
	
	public static boolean isViableLightningTarget(List attackers, EntityLiving target)
	{
		for(int i = 0; i < attackers.size(); i++)
		{
			if(attackers.get(i) == target) { return false; }
		}
		
		return true;
	}
	
	public static void chainLightningEffect(EntityLiving entity)
	{
		Random random = new Random();
		entity.worldObj.playSoundAtEntity(entity, "random.explode", 1.0F, 0.5F);
	}	
	
	public static void lightingBoltEffect(EntityLiving entity, EntityLiving target)
	{
		EntityLiving entityStart = entity;
		EntityLiving entityEnd = target;

		Random random = new Random();

		double x1 = entityStart.posX;
		double y1 = entityStart.posY + (entityStart.height / 2.0D);
		double z1 = entityStart.posZ;
		double x2 = entityEnd.posX;
		double y2 = entityEnd.posY + (entityEnd.height / 2.0D);
		double z2 = entityEnd.posZ;
		double x = x2 - x1;
		double y = y2 - y1;
		double z = z2 - z1;
		double segments = 7;
		double distance = Math.sqrt(x * x + z * z + y * y);
		double width = distance / segments;
		double prevX = x1;
		double prevY = y1;
		double prevZ = z1;
		double vertexWidth = 0.1D;

		for(int i = 0; i <= segments; i++) 
		{
			double magnitude = (width * i) / distance;

			double x3 = magnitude * x2 + (1 - magnitude) * x1;
			double y3 = magnitude * y2 + (1 - magnitude) * y1;
			double z3 = magnitude * z2 + (1 - magnitude) * z1;
			
			if(i != 0 && i != segments) 
			{
				x3 += (random.nextDouble() * width) - (width / 1.75);
				y3 += (random.nextDouble() * width) - (width / 1.75);
				z3 += (random.nextDouble() * width) - (width / 1.75);
			}
			
			double x4 = x3 - prevX;
			double y4 = y3 - prevY;
			double z4 = z3 - prevZ;
		
			double distance2 = Math.sqrt(x4 * x4 + z4 * z4 + y4 * y4);
			int res = (int)distance2 * 7;
			double width2 = distance2 / res;
				
			for(int k = 0; k < res; k++)
			{
				double magnitude2 = (width2 * k) / distance2;

				double x5 = magnitude2 * x3 + (1 - magnitude2) * prevX;
				double y5 = magnitude2 * y3 + (1 - magnitude2) * prevY;
				double z5 = magnitude2 * z3 + (1 - magnitude2) * prevZ;
				
				entityStart.worldObj.spawnParticle("chainLightning", x5, y5, z5, 0.0D, 0.0D, 0.0D);
			}
			
			entityStart.worldObj.spawnParticle("chainLightning", x3, y3, z3, 0.0D, 0.0D, 0.0D);
			
			prevX = x3;
			prevY = y3;
			prevZ = z3;
		}
	}
	
	public static List getEntitiesInRange(Entity target, int radius)
	{
		int aabbPosX = MathHelper.floor_double(target.posX - (double)radius - 1.0D);
		int aabbMotionX = MathHelper.floor_double(target.posX + (double)radius + 1.0D);
		int aabbPosY = MathHelper.floor_double(target.posY - (double)radius - 1.0D);
		int aabbMotionY = MathHelper.floor_double(target.posY + (double)radius + 1.0D);
		int aabbPosZ = MathHelper.floor_double(target.posZ - (double)radius - 1.0D);
		int aabbMotionZ = MathHelper.floor_double(target.posZ + (double)radius + 1.0D);
		List entities = target.worldObj.getEntitiesWithinAABBExcludingEntity(
			target, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(
				(double)aabbPosX, (double)aabbPosY, (double)aabbPosZ, 
				(double)aabbMotionX, (double)aabbMotionY, (double)aabbMotionZ
			)
		);
		
		return entities;
	}
	
	public static List getEntitiesInRange(Entity target, double distance, double radius)
	{
		double x = distance * Math.cos(Math.toRadians((double)target.rotationYaw + 90.0F));
		double z = distance * Math.sin(Math.toRadians((double)target.rotationYaw + 90.0F));
	
		int aabbPosX = MathHelper.floor_double(target.posX + x - radius - 1.0D);
		int aabbMotionX = MathHelper.floor_double(target.posX + x + radius + 1.0D);
		int aabbPosY = MathHelper.floor_double(target.posY - radius - 1.0D);
		int aabbMotionY = MathHelper.floor_double(target.posY + radius + 1.0D);
		int aabbPosZ = MathHelper.floor_double(target.posZ + z - radius - 1.0D);
		int aabbMotionZ = MathHelper.floor_double(target.posZ + z + radius + 1.0D);
		List entities = target.worldObj.getEntitiesWithinAABBExcludingEntity(
			target, AxisAlignedBB.getAABBPool().addOrModifyAABBInPool(
				(double)aabbPosX, (double)aabbPosY, (double)aabbPosZ, 
				(double)aabbMotionX, (double)aabbMotionY, (double)aabbMotionZ
			)
		);
		
		return entities;
	}
	
	public static void spawnParticle(Entity entity, String particle)
	{
		entity.worldObj.spawnParticle(
			particle,
			entity.posX + ((double)entity.rand.nextFloat() - 0.5D) * (double)entity.width,
			entity.posY + (double)entity.rand.nextFloat() * (double)entity.height, 
			entity.posZ + ((double)entity.rand.nextFloat() - 0.5D) * (double)entity.width,
			-entity.motionX * 8.0D, 0.5D,
			-entity.motionZ * 8.0D
		);
	}
}