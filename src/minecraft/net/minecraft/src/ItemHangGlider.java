package net.minecraft.src;

import java.util.Random;

public class ItemHangGlider extends Item
{
	private int attackCooldown = 0;
	private int maxAttackCooldown = 400;
	
    public ItemHangGlider(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(1561);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(attackCooldown == 0 && !player.isInWater() && !player.onGround)
		{
			attackCooldown = maxAttackCooldown;
		}

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
					itemStack.damageItem(1, player);
					
					if(attackCooldown > 0)
					{
						player.addVelocity(player.motionX * (attackCooldown / maxAttackCooldown) * 3.0D, 0.0D, player.motionZ * (attackCooldown / maxAttackCooldown) * 3.0D);
						attackCooldown--;
					}
				}
			}
			
			if(player.isInWater() || player.onGround)
			{
				attackCooldown = 0;
			}
		}
	}

    public int getItemEnchantability()
    {
        return 0;
    }
}
