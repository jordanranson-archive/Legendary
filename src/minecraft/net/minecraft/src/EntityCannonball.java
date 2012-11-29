package net.minecraft.src;

public class EntityCannonball extends EntityThrowable
{
    public EntityCannonball(World par1World)
    {
        super(par1World);
    }

    public EntityCannonball(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityCannonball(World par1World, double par2, double par4, double par6)
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
            movingObjPos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_85052_h()) /* thrower */, 6);
        }

        for (int var3 = 0; var3 < 8; ++var3)
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
		for (var9 = 0; var9 < 4; ++var9)
		{
			this.worldObj.spawnParticle("reddust", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}
}
