package net.minecraft.src;

public class EntityFrozenOrb extends EntityThrowable
{
	private int timeAlive = 0;

    public EntityFrozenOrb(World par1World)
    {
        super(par1World);
    }

    public EntityFrozenOrb(World par1World, EntityLiving par2EntityLiving, float velocity)
    {
        super(par1World, par2EntityLiving);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity + 0.2F, 1.0F);
    }

    public EntityFrozenOrb(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    public void onUpdate()
	{
		int groundHeight = -1;
		int groundCounter = 0;
		int x = 1 - this.worldObj.rand.nextInt(3); 
		int z = 1 - this.worldObj.rand.nextInt(3);

		
		if(timeAlive > 3 && this.worldObj.rand.nextInt(2) == 0)
		{
			while(true)
			{
				if(groundCounter > 5)
				{
					break;
				}

				// Found solid block
				if(this.worldObj.getBlockId((int)this.posX + x, (int)this.posY - groundCounter, (int)this.posZ + z) != 0)
				{
					if(Block.icyFire.canPlaceBlockAt(this.worldObj, (int)this.posX + x, (int)this.posY - groundCounter + 1, (int)this.posZ + z))
					{
						groundHeight = (int)this.posY - groundCounter + 1;
						if (this.worldObj.getBlockId((int)this.posX + x, groundHeight, (int)this.posZ + z) == 0)
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
		
		for (int var3 = 0; var3 < 3; ++var3)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("frozenEnchant", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
		
		if(timeAlive < 5)
		{
			timeAlive++;
		}
		
		super.onUpdate();
	}
	
	protected void randomParticles(String particle)
	{
		double x = 3.0D - (this.worldObj.rand.nextDouble() * 6.0D);
		double y = 3.0D - (this.worldObj.rand.nextDouble() * 6.0D);
		double z = 3.0D - (this.worldObj.rand.nextDouble() * 6.0D);
		this.worldObj.spawnParticle(particle, this.posX + x, this.posY + y, this.posZ + z, x / 10.0D, y / 10.0D, z / 10.0D);
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

        for (int var3 = 0; var3 < 7; ++var3)
        {
            this.randomParticles("snowballpoof");
            this.randomParticles("frozenEnchant");
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
