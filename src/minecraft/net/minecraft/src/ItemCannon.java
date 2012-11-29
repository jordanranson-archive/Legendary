package net.minecraft.src;

import java.util.Random;

public class ItemCannon extends Item
{
	private int attackCooldown = 0;
	private int maxAttackCooldown = 100;
	
    public ItemCannon(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if(attackCooldown == 0) 
		{
			player.setItemInUse(itemStack, 10);
		
			boolean canUseWithoutArrows = player.capabilities.isCreativeMode;
			float velocity = 1.0F;
			
			if (canUseWithoutArrows || player.inventory.hasItem(Item.cannonball.shiftedIndex))
			{
				itemStack.damageItem(1, player);
				
				for (int i = 0; i < 4; ++i)
				{
					world.spawnParticle("explode", player.posX, player.posY, player.posZ, 0.0D, 0.0D, 0.0D);
				}
				world.playSoundAtEntity(player, "random.explode", 1.0F, 1.0F);

				if (!canUseWithoutArrows)
				{
					player.inventory.consumeInventoryItem(Item.cannonball.shiftedIndex);
				}

				if (!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityCannonball(world, player));
					attackCooldown = maxAttackCooldown;
				}
			}
		}
		else 
		{
			world.playSoundAtEntity(player, "random.click", 1.0F, 1.0F);
		}

        return itemStack;
    }
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(attackCooldown > 0)
		{
			attackCooldown--;
		}
	}

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}
