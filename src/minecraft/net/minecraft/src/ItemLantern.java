package net.minecraft.src;

public class ItemLantern extends ItemBlock
{
    public ItemLantern(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int par1)
    {
        return Block.cloth.getBlockTextureFromSideAndMetadata(2, par1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
		String[] name = new String[3];
		name[0] = "iron";
		name[1] = "paper";
		name[2] = "stone";
		
        return super.getItemName() + "." + name[par1ItemStack.getItemDamage()];
    }
}
