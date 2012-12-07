package net.minecraft.src;

import java.util.Random;

public class EntityChargedEnchantFX extends EntityFX
{
    float reddustParticleScale;

    public EntityChargedEnchantFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
    {
        this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
    }

    public EntityChargedEnchantFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10, float par11)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.03D;
        this.motionY *= 0.03D;
        this.motionZ *= 0.03D;

        if (par9 == 0.0F)
        {
            par9 = 1.0F;
        }

        float var12 = (float)Math.random() * 0.4F + 0.6F;
		
		Random random = new Random();
		if(random.nextInt(3) == 0)
		{
			this.particleRed = 1.0F;
			this.particleRed = 1.0F;
			this.particleRed = 1.0F;
		}
		else
		{
			this.particleRed = 0.5F + (float)Math.random() * 0.1F - 0.2F;
			this.particleGreen = 1.0F;
			this.particleBlue = 0.5F + (float)Math.random() * 0.1F - 0.2F;
		}
        
        this.particleScale *= 1.0F;
        this.particleScale *= par8;
        this.reddustParticleScale = this.particleScale;
        this.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * par8);
        this.noClip = false;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float var8 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 32.0F;

        if (var8 < 0.0F)
        {
            var8 = 0.0F;
        }

        if (var8 > 1.0F)
        {
            var8 = 1.0F;
        }

		Random random = new Random();
		par1Tessellator.setBrightness(random.nextInt(132) + 64);
        this.particleScale = this.reddustParticleScale * var8;
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.94D;
        this.motionY *= 0.94D;
        this.motionZ *= 0.94D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
