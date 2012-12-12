package net.minecraft.src;

import java.util.List;

public class ItemMagicRodFlame extends Item
{
    public ItemMagicRodFlame(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
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
        par3List.add("Imbued with flame");
    }
}
