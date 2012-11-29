package net.minecraft.src;

import java.util.Random;
import java.util.ArrayList;

public class ItemCannon extends Item
{
	private int attackCooldown = 0;
	private int maxAttackCooldown = 75;
	
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
			
			if (canUseWithoutArrows || hasAmmuntion(player))
			{
				Item ammo;
				itemStack.damageItem(3, player);
				
				for (int i = 0; i < 4; ++i)
				{
					world.spawnParticle("explode", player.posX + (double)(Math.random() * 0.25 - 0.125), player.posY + 0.15D, player.posZ + (double)(Math.random() * 0.25 - 0.125), 0.0D, 0.0D, 0.0D);
				}
				for (int i = 0; i < 3; ++i)
				{
					world.spawnParticle("smoke", player.posX + (double)(Math.random() * 0.25 - 0.125), player.posY + 0.15D, player.posZ + (double)(Math.random() * 0.25 - 0.125), 0.0D, 0.0D, 0.0D);
				}
				world.playSoundAtEntity(player, "random.explode", 1.0F, 1.0F);

				if (!world.isRemote)
				{
					ammo = getAmmunition(player, canUseWithoutArrows);
				
					if(ammo == Item.cannonball)
					{
						world.spawnEntityInWorld(new EntityCannonball(world, player));
					}
					
					else if(ammo == Item.cannonballHeavy)
					{
						world.spawnEntityInWorld(new EntityCannonballHeavy(world, player));
					}
					
					else if(ammo == Item.cannonballExplosive)
					{
						world.spawnEntityInWorld(new EntityCannonballExplosive(world, player));
					}
					
					attackCooldown = maxAttackCooldown;
					
					if (!canUseWithoutArrows)
					{
						player.inventory.consumeInventoryItem(ammo.shiftedIndex);
					}
				}
			}
		}

        return itemStack;
    }
	
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) 
	{
		if(attackCooldown > 0)
		{
			attackCooldown--;
			world.spawnParticle("smoke", entity.posX + (double)(Math.random() * 0.25 - 0.125), entity.posY + 0.15D, entity.posZ + (double)(Math.random() * 0.25 - 0.125), 0.0D, 0.005D, 0.0D);
		}
	}
	
	private boolean hasAmmuntion(EntityPlayer player)
	{
		return player.inventory.hasItem(Item.cannonball.shiftedIndex) || 
			   player.inventory.hasItem(Item.cannonballHeavy.shiftedIndex) ||
			   player.inventory.hasItem(Item.cannonballExplosive.shiftedIndex);
	}
	
	private Item getAmmunition(EntityPlayer player, boolean canUseWithoutArrows) 
	{
		if(!canUseWithoutArrows)
		{
			ArrayList<Item> ammo = new ArrayList<Item>();
			Random random = new Random();
			
			if(player.inventory.hasItem(Item.cannonball.shiftedIndex))
			{
				ammo.add(Item.cannonball);
			}
			
			if(player.inventory.hasItem(Item.cannonballHeavy.shiftedIndex))
			{
				ammo.add(Item.cannonballHeavy);
			}
			
			if(player.inventory.hasItem(Item.cannonballExplosive.shiftedIndex))
			{
				ammo.add(Item.cannonballExplosive);
			}

			return ammo.get(random.nextInt(ammo.size()));
		}
		else
		{
			return Item.cannonball;
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
