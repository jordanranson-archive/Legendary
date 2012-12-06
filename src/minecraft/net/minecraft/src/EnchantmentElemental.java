package net.minecraft.src;

public class EnchantmentElemental extends Enchantment
{
    public EnchantmentElemental(int id, int weight, EnumEnchantmentType enchantmentType, String name)
    {
        super(id, weight, enchantmentType);
		this.setName(name);
    }
	
    public int getMinEnchantability(int par1)
    {
        return 20;
    }

    public int getMaxEnchantability(int par1)
    {
        return 50;
    }

    public int getMaxLevel()
    {
        return 1;
    }
}