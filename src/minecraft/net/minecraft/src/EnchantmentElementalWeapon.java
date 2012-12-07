package net.minecraft.src;

public class EnchantmentElementalWeapon extends Enchantment
{
    public EnchantmentElementalWeapon(int id, int weight, EnumEnchantmentType enchantmentType, String name)
    {
        super(id, weight, enchantmentType);
		this.setName(name);
    }
	
    public int getMinEnchantability(int par1)
    {
        return 10 + 20 * (par1 - 1);
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel()
    {
        return 2;
    }
}