package net.minecraft.src;

import java.util.ArrayList;

public class EntityChargedOrb extends EntityThrowable
{
    public EntityChargedOrb(World par1World)
    {
        super(par1World);
    }

    public EntityChargedOrb(World par1World, EntityLiving par2EntityLiving, float angle)
    {
        super(par1World, par2EntityLiving);
		//this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity + 0.2F, 1.0F);
    }

    public EntityChargedOrb(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    public void onUpdate()
	{
		for (int var3 = 0; var3 < 3; ++var3)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            this.worldObj.spawnParticle("frozenEnchant", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
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
				
				LegendaryEnchantmentHelper.chainLightning(entityHit, this.func_85052_h(), new ArrayList(), 6, 2, 0);
				LegendaryEnchantmentHelper.chainLightningEffect(entityHit);
			}
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
