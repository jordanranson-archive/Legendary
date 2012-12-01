package net.minecraft.src;

import java.util.Random;

public class BlockLichen extends Block
{
    public BlockLichen(int par1)
    {
        super(par1, 143, Material.vine);
        this.setTickRandomly(true);
    }

    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public int getRenderType()
    {
        return 100;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var6 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        float var7 = 1.0F;
        float var8 = 1.0F;
        float var9 = 1.0F;
        float var10 = 0.0F;
        float var11 = 0.0F;
        float var12 = 0.0F;
        boolean var13 = var6 > 0;

        if ((var6 & 2) != 0)
        {
            var10 = Math.max(var10, 0.0625F);
            var7 = 0.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var9 = 0.0F;
            var12 = 1.0F;
            var13 = true;
        }

        if ((var6 & 8) != 0)
        {
            var7 = Math.min(var7, 0.9375F);
            var10 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var9 = 0.0F;
            var12 = 1.0F;
            var13 = true;
        }

        if ((var6 & 4) != 0)
        {
            var12 = Math.max(var12, 0.0625F);
            var9 = 0.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var13 = true;
        }

        if ((var6 & 1) != 0)
        {
            var9 = Math.min(var9, 0.9375F);
            var12 = 1.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var13 = true;
        }

        if (!var13 && this.canBePlacedOn(par1IBlockAccess.getBlockId(par2, par3 + 1, par4)))
        {
            var8 = Math.min(var8, 0.9375F);
            var11 = 1.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var9 = 0.0F;
            var12 = 1.0F;
        }

        this.setBlockBounds(var7, var8, var9, var10, var11, var12);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        switch (par5)
        {
            case 1:
                return this.canBePlacedOn(par1World.getBlockId(par2, par3 + 1, par4));

            case 2:
                return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 + 1));

            case 3:
                return this.canBePlacedOn(par1World.getBlockId(par2, par3, par4 - 1));

            case 4:
                return this.canBePlacedOn(par1World.getBlockId(par2 + 1, par3, par4));

            case 5:
                return this.canBePlacedOn(par1World.getBlockId(par2 - 1, par3, par4));

            default:
                return false;
        }
    }

    private boolean canBePlacedOn(int blockID)
    {
        if (blockID == 0)
        {
            return false;
        }
        else
        {
            return blockID == Block.cobblestone.blockID || blockID == Block.cobblestoneMossy.blockID || 
				   blockID == Block.stoneBrick.blockID  || blockID == Block.wood.blockID  			 ||
				   blockID == Block.brick.blockID;
        }
    }

    /**
     * Returns if the vine can stay in the world. It also changes the metadata according to neighboring blocks.
     */
	private boolean canVineStay(World world, int blockX, int blockY, int blockZ)
	{
		int blockMeta = world.getBlockMetadata(blockX, blockY, blockZ);
		int blockMetaCopy = blockMeta;

		if (blockMeta > 0)
		{
			for (int i = 0; i <= 3; ++i)
			{
				int i2 = 1 << i;

				if ((blockMeta & i2) != 0 && 
				!this.canBePlacedOn(world.getBlockId(blockX + Direction.offsetX[i], blockY, blockZ + Direction.offsetZ[i])) && 
				((world.getBlockId(blockX, blockY + 1, blockZ) != this.blockID) || 
				(world.getBlockMetadata(blockX, blockY + 1, blockZ) & i2) == 0))
				{
					blockMetaCopy &= ~i2;
				}
			}
		}

		if (blockMetaCopy == 0 && !this.canBePlacedOn(world.getBlockId(blockX, blockY + 1, blockZ)))
		{
			return false;
		}
		else
		{
			if (blockMetaCopy != blockMeta)
			{
				world.setBlockMetadataWithNotify(blockX, blockY, blockZ, blockMetaCopy);
			}

			return true;
		}
	}

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote && !this.canVineStay(par1World, par2, par3, par4))
        {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
        }
    }

    public int func_85104_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        byte var10 = 0;

        switch (par5)
        {
            case 2:
                var10 = 1;
                break;

            case 3:
                var10 = 4;
                break;

            case 4:
                var10 = 8;
                break;

            case 5:
                var10 = 2;
        }

        return var10 != 0 ? var10 : par9;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	public void updateTick(World world, int blockX, int blockY, int blockZ, Random random)
	{
		if(world.getBlockLightValue(blockX, blockY, blockZ) < 12 && random.nextInt(6) == 0)
		{
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lichenGlowing.blockID, meta);
		}
	}
}
