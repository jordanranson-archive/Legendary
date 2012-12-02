package net.minecraft.src;

import java.util.Random;

public class BlockLanternLit extends BlockLantern
{
    public BlockLanternLit(int par1, int par2)
    {
        super(par1, par2);
		this.setLightValue(0.9375F);
    }

    public BlockLanternLit(int par1, int par2, Material par3Material)
    {
        super(par1, par2, par3Material);
    }

	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int var6 = par1World.getBlockMetadata(par2, par3, par4);
        double var7 = (double)((float)par2 + 0.5F);
        double var9 = (double)((float)par3 + 0.7F);
        double var11 = (double)((float)par4 + 0.5F);
        double var13 = 0.2199999988079071D;
        double var15 = 0.27000001072883606D;

        if (var6 == 1)
        {
            par1World.spawnParticle("smoke", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", var7 - var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
        }
        else if (var6 == 2)
        {
            par1World.spawnParticle("smoke", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", var7 + var15, var9 + var13, var11, 0.0D, 0.0D, 0.0D);
        }
        else if (var6 == 3)
        {
            par1World.spawnParticle("smoke", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", var7, var9 + var13, var11 - var15, 0.0D, 0.0D, 0.0D);
        }
        else if (var6 == 4)
        {
            par1World.spawnParticle("smoke", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", var7, var9 + var13, var11 + var15, 0.0D, 0.0D, 0.0D);
        }
        else
        {
            par1World.spawnParticle("smoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
            par1World.spawnParticle("flame", var7, var9, var11, 0.0D, 0.0D, 0.0D);
        }
    }
	
	public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		int meta = world.getBlockMetadata(blockX, blockY, blockZ);
		world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lantern.blockID, meta);
		
		return true;
	}
}
