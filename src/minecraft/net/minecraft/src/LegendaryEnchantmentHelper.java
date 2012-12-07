package net.minecraft.src;

import java.util.Random;

public class LegendaryEnchantmentHelper 
{
	public static void frozenEffect(EntityLiving entity) 
	{
		entity.motionX *= 0.5D;
		entity.motionZ *= 0.5D;
		entity.motionY *= 0.5D;

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
	
	// affliction potion effect
	public static void afflictionEffect(EntityLiving entity)
	{
		for(int i = 0; i < 4; i++)
		{
			LegendaryEnchantmentHelper.spawnParticle(entity, "cursedEnchant");
		}
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