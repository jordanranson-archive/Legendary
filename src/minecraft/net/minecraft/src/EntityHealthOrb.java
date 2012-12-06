package net.minecraft.src;

public class EntityHealthOrb extends EntityXPOrb
{
    public EntityHealthOrb(World par1World, double par2, double par4, double par6, int par8)
    {
        super(par1World, par2, par4, par6, 0);
    }

    public EntityHealthOrb(World par1World)
    {
        super(par1World);
    }

    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote)
        {
            if (this.field_70532_c == 0 && par1EntityPlayer.xpCooldown == 0)
            {
                this.func_85030_a("random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
                par1EntityPlayer.onItemPickup(this, 1);
                par1EntityPlayer.heal(1);
                this.setDead();
            }
        }
    }

    public int getTextureByXP()
    {
        return 0;
    }
}
