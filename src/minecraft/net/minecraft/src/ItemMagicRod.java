package net.minecraft.src;

public class ItemMagicRod extends Item
{
    public ItemMagicRod(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
		int ticks = this.getMaxItemUseDuration(par1ItemStack) - par4;
		float effectStrength = (float)ticks / 20.0F;

		if(effectStrength < 1.0F)
		{
			return;
		}
		
		if(effectStrength > 3.0F)
		{
			effectStrength = 3.0F;
		}
		
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new EntitySnowball(par2World, par3EntityPlayer));
        }
    }
	
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

        return par1ItemStack;
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    public int getItemEnchantability()
    {
        return 0;
    }
}
