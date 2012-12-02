package net.minecraft.src;

import java.util.Random;

public class BlockLantern extends BlockSand
{
    /** Do blocks fall instantly to where they stop or do they fall over time */
    public static boolean fallInstantly = false;

    public BlockLantern(int par1, int par2)
    {
        super(par1, par2, Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setStepSound(soundMetalFootstep);
    }

    public BlockLantern(int par1, int par2, Material par3Material)
    {
        super(par1, par2, par3Material);
    }

	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            this.tryToFall(par1World, par2, par3, par4);
        }
    }
	
    private void tryToFall(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 + 1, par4) && canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
        {
            byte var8 = 32;
			//int roofMeta = par1World.getBlockMetadata(par2, par3 + 1, par4);

            if (!fallInstantly && par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
            {
                if (!par1World.isRemote)
                {
                    EntityFallingSand var9 = new EntityFallingSand(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), this.blockID, par1World.getBlockMetadata(par2, par3, par4));
                    this.onStartFalling(var9);
                    par1World.spawnEntityInWorld(var9);
                }
            }
            else
            {
                par1World.setBlockWithNotify(par2, par3, par4, 0);

                while (canFallBelow(par1World, par2, par3 - 1, par4) && par3 > 0)
                {
                    --par3;
                }

                if (par3 > 0)
                {
                    par1World.setBlockWithNotify(par2, par3, par4, this.blockID);
                }
            }
        }
    }
}
