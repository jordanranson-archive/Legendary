package net.minecraft.src;

public class EntityFrozenOrb extends EntityThrowable
{
    public EntityFrozenOrb(World par1World)
    {
        super(par1World);
    }

    public EntityFrozenOrb(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityFrozenOrb(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    public void onUpdate()
	{
		int groundHeight = -1;
		int groundCounter = 0;
		int x; int z; int i;
		
		while(true)
		{
			for(x = -2; x < 3; x++)
			{
				for(z = -2; z < 3; z++)
				{
					// Found solid block
					if(this.worldObj.getBlockId((int)this.posX + x, (int)this.posY - groundCounter, (int)this.posZ + z) != 0)
					{
						// Found block that can burn
						if(Block.fire.canBlockCatchFire(this.worldObj, (int)this.posX + x, (int)this.posY - groundCounter, (int)this.posZ + z))
						{
							groundHeight = (int)this.posY - groundCounter;
							if (this.worldObj.getBlockId((int)this.posX + x, groundHeight + 1, (int)this.posZ + z) == 0)
							{
								this.worldObj.setBlockWithNotify((int)this.posX + x, groundHeight, (int)this.posZ + z, Block.icyFire.blockID);
							}
						}
						
						break;
					}
					// Found air
					else 
					{
						groundCounter++;
					}
				}
			}
		}
	}
	
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            byte var2 = 0;

            if (par1MovingObjectPosition.entityHit instanceof EntityBlaze)
            {
                var2 = 3;
            }
			
			if (par1MovingObjectPosition.entityHit instanceof EntityLiving)
			{
				EntityLiving entityHit = (EntityLiving)par1MovingObjectPosition.entityHit;
				entityHit.addPotionEffect(new PotionEffect(Potion.frozen.id, 20 * 6, 0));
			}

            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_85052_h()), var2);
        }

        for (int var3 = 0; var3 < 4; ++var3)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
		
        for (int var3 = 0; var3 < 4; ++var3)
        {
            this.worldObj.spawnParticle("frozenEnchant", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
