package net.minecraft.src;

import java.util.Random;

public class ItemHangGlider extends Item
{
    public ItemHangGlider(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(1177);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		player.motionX *= 0.5F;
		player.motionZ *= 0.5F;

        return itemStack;
    }
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entity;
			if(player.inventory.getCurrentItem() != null)
			{
				Item currentItem = player.inventory.getCurrentItem().getItem();
				if(currentItem == Item.hangGlider && !player.isInWater() && !player.onGround && !player.capabilities.isFlying && !player.isJumping)
				{
					player.motionY *= 0.6F;
					
					if(player.motionX > -0.45F && player.motionX < 0.45F)
					{
						player.motionX *= 1.1F;
					}
					
					if(player.motionZ > -0.45F && player.motionZ < 0.45F)
					{
						player.motionZ *= 1.1F;
					}

					itemStack.damageItem(1, player);
				}
			}
		}
	}

    public int getItemEnchantability()
    {
        return 0;
    }
}
