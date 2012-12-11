package net.minecraft.src;

import java.util.Random;

public class BlockLichenGlowing extends BlockLichen
{
    public BlockLichenGlowing(int par1)
    {
        super(par1);
    }
	
	public boolean onBlockActivated(World world, int blockX, int blockY, int blockZ, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		ItemStack equippedItem = player.inventory.getCurrentItem();
		if(equippedItem != null && equippedItem.getItem().shiftedIndex == Item.glassBottle.shiftedIndex)
		{
			player.inventory.decrStackSize(player.inventory.currentItem, 1);
		
			if (!player.inventory.addItemStackToInventory(new ItemStack(Item.lichenSpores)))
			{
				player.dropPlayerItem(new ItemStack(Item.lichenSpores));
			}
			
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lichen.blockID, meta);
			
			return true;
		}
		else
		{
			return false;
		}
    }
		
	public void randomDisplayTick(World world, int blockX, int blockY, int blockZ, Random random)
    {
		world.spawnParticle("lichenGlow", blockX + (double)Math.random(), blockY + (double)Math.random(), blockZ + (double)Math.random(), 0.0D, 0.0D, 0.0D);
	}
	
	public int getRenderType()
    {
        return 101;
    }
	
	public void onNeighborBlockChange(World world, int blockX, int blockY, int blockZ, int par4)
    {
        if(world.getBlockLightValue(blockX, blockY, blockZ) >= 12)
		{
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lichen.blockID, meta);
		}
    }
	
	public void updateTick(World world, int blockX, int blockY, int blockZ, Random random)
	{
		if(world.getBlockLightValue(blockX, blockY, blockZ) >= 12)
		{
			int meta = world.getBlockMetadata(blockX, blockY, blockZ);
			world.setBlockAndMetadata(blockX, blockY, blockZ, Block.lichen.blockID, meta);
		}
	}
	
}