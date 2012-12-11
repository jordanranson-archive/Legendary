package net.minecraft.src;

import java.util.List;

public class ItemMagicRodFrozen extends Item
{
    public ItemMagicRodFrozen(int par1)
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

		if(effectStrength < 0.1F)
		{
			return;
		}
		
		if(effectStrength > 1.0F)
		{
			effectStrength = 1.0F;
		}
		
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		EntityFrozenOrb frozenOrb = new EntityFrozenOrb(par2World, par3EntityPlayer, effectStrength);
        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(frozenOrb);
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
	
	public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
	
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        par3List.add("Imbued with Frost");
    }
}
