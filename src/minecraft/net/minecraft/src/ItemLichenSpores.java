package net.minecraft.src;

public class ItemLichenSpores extends Item
{
    public ItemLichenSpores(int id)
    {
        super(id);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int posX, int posY, int posZ, int par7, float par8, float par9, float par10)
    {
		int blockID = world.getBlockId(posX, posY, posZ);
		if(blockID == Block.cobblestone.blockID || blockID == Block.cobblestoneMossy.blockID || 
		   blockID == Block.stoneBrick.blockID  || blockID == Block.wood.blockID  			 ||
		   blockID == Block.brick.blockID)
	    {
			int angle = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            int meta = 0;
			int offsetX = 0;
			int offsetZ = 0;

			// South
            if (angle == 0)
            {
                meta = 1;
				offsetZ = -1;
            }

			// West
            if (angle == 1)
            {
                meta = 2;
				offsetX = 1;
            }

			// North
            if (angle == 2)
            {
                meta = 4;
				offsetZ = 1;
            }

			// East
            if (angle == 3)
            {
                meta = 8;
				offsetX = -1;
            }
			
			if (player.canPlayerEdit(posX + offsetX, posY, posZ + offsetZ, par7, itemStack))
            {
                if (world.isAirBlock(posX + offsetX, posY, posZ + offsetZ))
                {
					world.setBlockAndMetadataWithNotify(posX + offsetX, posY, posZ + offsetZ, Block.lichen.blockID, meta);
                    --itemStack.stackSize;
					
					if (!player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle)))
					{
						player.dropPlayerItem(new ItemStack(Item.glassBottle));
					}
					
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
	    }
	    else
	    {
			return false;
		}
	}	
}