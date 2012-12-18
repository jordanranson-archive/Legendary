package net.minecraft.src;

public class EntityChainLightningFX extends EntityFX
{
    float reddustParticleScale;

    public EntityChainLightningFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10)
    {
        this(par1World, par2, par4, par6, 1.0F, par8, par9, par10);
    }

    public EntityChainLightningFX(World par1World, double par2, double par4, double par6, float par8, float par9, float par10, float par11)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
		this.particleRed = 0.3F;
		this.particleGreen = 0.95F;
		this.particleBlue = 0.15F;
		this.particleScale = 1.0F;
        this.reddustParticleScale = this.particleScale;
        this.particleMaxAge = 3;
        this.noClip = false;
    }
	
	public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
		par1Tessellator.setBrightness(0xF000F0);
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

        this.setParticleTextureIndex(this.particleAge % 2 == 0 ? 42 : 7);
    }
}
