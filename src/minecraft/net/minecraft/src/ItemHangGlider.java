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
					entity.motionY *= 0.6F;
					
					if(entity.motionX > -0.495F && entity.motionX < 0.495F)
					{
						entity.motionX *= 1.065F;
					}
					
					if(entity.motionZ > -0.495F && entity.motionZ < 0.495F)
					{
						entity.motionZ *= 1.065F;
					}
					
					if(itemStack.getItemDamage() + 1 >= this.getMaxDamage())
					{
						itemStack.damageItem(1, player);
						world.playSoundAtEntity(player, "random.break", 1.0F, 1.0F);
						player.inventory.decrStackSize(player.inventory.currentItem, 1);
					}
					else
					{
						itemStack.damageItem(1, player);
					}
				}
			}
		}
	}

    public int getItemEnchantability()
    {
        return 0;
    }
}
