package net.minecraft.src;

import java.util.Random;
import java.util.List;

public class BlockLantern extends BlockSand
{
    /** Do blocks fall instantly to where they stop or do they fall over time */
    public static boolean fallInstantly = false;

    public BlockLantern(int par1, int par2)
    {
        super(par1, par2, Material.iron);
		this.setStepSound(soundMetalFootstep);
		this.setLightValue(0.9375F);
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
		int roofId = par1World.getBlockId(par2, par3 + 1, par4);
        if ((roofId != Block.fence.blockID && roofId != Block.netherFence.blockID && roofId != Block.glass.blockID && roofId != Block.cobblestoneWall.blockID) &&
		!par1World.doesBlockHaveSolidTopSurface(par2, par3 + 1, par4) && canFallBelow(par1World, par2, par3 - 1, par4) && par3 >= 0)
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
	
	/*public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		ItemStack equippedItem = player.inventory.getCurrentItem();
		if(equippedItem != null && equippedItem.getItem().shiftedIndex == Item.flintAndSteel.shiftedIndex && !(this instanceof BlockLanternLit))
		{
            equippedItem.damageItem(1, player);
		
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lanternLit.blockID, meta);
			
			return true;
		}
		else
		{
			return false;
		}
    }*/
	
	public int getBlockTextureFromSideAndMetadata(int id, int meta)
    {
		return this.blockIndexInTexture + meta;
    }

    public int damageDropped(int damage)
    {
        return damage;
    }
	
	public void getSubBlocks(int blockID, CreativeTabs creativeTab, List itemList)
    {
        for (int i = 0; i < 3; ++i)
        {
            itemList.add(new ItemStack(blockID, 1, i));
        }
    }
}
