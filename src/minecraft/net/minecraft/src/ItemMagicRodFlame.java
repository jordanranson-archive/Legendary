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
	
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entity)
    {
		List targetsHit = LegendaryEnchantmentHelper.getEntitiesInRange(entity, 2.0D, 1.5D);
		EntityLiving target;
		int i;
		
		for (i = 0; i < targetsHit.size(); i++)
		{
			System.out.println(targetsHit.get(i));
			if(targetsHit.get(i) instanceof EntityLiving)
			{
				target = (EntityLiving)targetsHit.get(i);
				target.setFire(1);
			}
		}
		
		double x;
		double z;
		
		for(i = 0; i < 24; i++)
		{
			x = ((double)(world.rand.nextFloat() * 2.5F) + 1.0F) * Math.cos(Math.toRadians((double)entity.rotationYaw + ((world.rand.nextFloat() * 40.0F) - 20.0F) + 90.0D));
			z = ((double)(world.rand.nextFloat() * 2.5F) + 1.0F) * Math.sin(Math.toRadians((double)entity.rotationYaw + ((world.rand.nextFloat() * 40.0F) - 20.0F) + 90.0D));
		
			world.spawnParticle(
				"flameJet",
				entity.posX + x + ((double)entity.width / 2.0D),
				entity.posY - ((double)entity.height - 1.4D) + (double)((world.rand.nextFloat() * 0.5F) - 0.25F), 
				entity.posZ + z + ((double)entity.width / 2.0D),
				entity.motionX, 0.1D, entity.motionZ
			);
		}
		
		for(i = 0; i < 8; i++)
		{
			x = ((double)(world.rand.nextFloat() * 1.5F) + 0.5F) * Math.cos(Math.toRadians((double)entity.rotationYaw + ((world.rand.nextFloat() * 30.0F) - 15.0F) + 90.0D));
			z = ((double)(world.rand.nextFloat() * 1.5F) + 0.5F) * Math.sin(Math.toRadians((double)entity.rotationYaw + ((world.rand.nextFloat() * 30.0F) - 15.0F) + 90.0D));
		
			world.spawnParticle(
				"flameJet2",
				entity.posX + x + ((double)entity.width / 2.0D),
				entity.posY - ((double)entity.height - 1.4D) + (double)((world.rand.nextFloat() * 0.5F) - 0.25F), 
				entity.posZ + z + ((double)entity.width / 2.0D),
				entity.motionX, 0.1D, entity.motionZ
			);
		}

		x = 0.5D * Math.cos(Math.toRadians((double)entity.rotationYaw + 90.0D));
		z = 0.5D * Math.sin(Math.toRadians((double)entity.rotationYaw + 90.0D));
		
        return itemStack;
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
