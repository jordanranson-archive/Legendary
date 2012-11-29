package net.minecraft.src;

public class EntityCannonballHeavy extends EntityThrowable
{
    public EntityCannonballHeavy(World par1World)
    {
        super(par1World);
    }

    public EntityCannonballHeavy(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityCannonballHeavy(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition movingObjPos)
    {
        if (movingObjPos.entityHit != null)
        {
            movingObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_85052_h()) /* thrower */, 8);
			
			float velocity = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			if (velocity > 0.0F)
			{
				movingObjPos.entityHit.addVelocity(this.motionX * 0.725D / (double)velocity, 0.175D, this.motionZ * 0.725D / (double)velocity);
			}
        }

        for (int i = 0; i < 4; ++i)
        {
            this.worldObj.spawnParticle("cannonballImpact", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
	
	public void onUpdate() 
	{
		super.onUpdate();
		for (int i = 0; i < 4; ++i)
		{
			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}
}
