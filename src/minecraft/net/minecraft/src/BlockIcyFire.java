package net.minecraft.src;

import java.util.Random;

public class BlockIcyFire extends Block
{
	private int timeAlive = 0;
	private int burnLength = 4;
	
    protected BlockIcyFire(int par1, int par2)
    {
        super(par1, par2, Material.fire);
        this.setTickRandomly(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return 102;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    public boolean func_82506_l()
    {
        return false;
    }

    public boolean isCollidable()
    {
        return false;
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4))
        {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
		if(this.timeAlive > this.burnLength)
		{
			par1World.setBlock(par2, par3, par4, 0);
		}
    }
	
	public int tickRate()
	{
		return 1;
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		par1World.setBlock(par2, par3, par4, 0);
	}	

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
		this.burnLength = (6 - par1World.rand.nextInt(3)) * 20;
        if (par1World.provider.dimensionId > 0)
        {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4))
            {
                par1World.setBlockWithNotify(par2, par3, par4, 0);
            }
            else
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate() + par1World.rand.nextInt(10));
            }
        }
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(24) == 0)
        {
            par1World.playSound((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), "fire.fire", 1.0F + par5Random.nextFloat(), par5Random.nextFloat() * 0.7F + 0.3F);
        }

        int var6;
        float var7;
        float var8;
        float var9;

		for (var6 = 0; var6 < 3; ++var6)
		{
			var7 = (float)par2 + par5Random.nextFloat();
			var8 = (float)par3 + par5Random.nextFloat() * 0.5F + 0.5F;
			var9 = (float)par4 + par5Random.nextFloat();
			par1World.spawnParticle("frozenSmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
		}
    }
	
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
		if(par5Entity instanceof EntityLiving)
		{
			((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.frozen.id, 20, 0));
		}
	}
}
