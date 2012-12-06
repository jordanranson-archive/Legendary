package net.minecraft.src;

import java.util.Random;

public class ItemCrossbow extends Item
{
	private int attackCooldown = 0;
	private int maxAttackCooldown = 10;
	
    public ItemCrossbow(int par1)
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
			player.setItemInUse(itemStack, maxAttackCooldown);
		
			boolean canUseWithoutArrows = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;
			float velocity = 0.8F;
			
			if (canUseWithoutArrows || player.inventory.hasItem(Item.arrow.shiftedIndex))
			{
				if(EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
				{
					EntityArrowFlame arrow = new EntityArrowFlame(world, player, velocity);
					arrow.setDamage(1.5F);
					arrow.setFire(100);
					
					int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
					if (enchantPower > 0)
					{
						arrow.setDamage(arrow.getDamage() + (double)enchantPower * 0.5D + 0.5D);
					}

					int enchantPunch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
					if (enchantPunch > 0)
					{
						arrow.setKnockbackStrength(enchantPunch);
					}

					itemStack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					if (canUseWithoutArrows)
					{
						arrow.canBePickedUp = 2;
					}
					else
					{
						player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(arrow);
						attackCooldown = maxAttackCooldown;
					}
				}
				else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.arrowFrost.effectId, itemStack) > 0)
				{
					EntityArrowFrozen arrow = new EntityArrowFrozen(world, player, velocity);
					arrow.setDamage(1.5F);

					int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
					if (enchantPower > 0)
					{
						arrow.setDamage(arrow.getDamage() + (double)enchantPower * 0.5D + 0.5D);
					}

					int enchantPunch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
					if (enchantPunch > 0)
					{
						arrow.setKnockbackStrength(enchantPunch);
					}

					itemStack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					if (canUseWithoutArrows)
					{
						arrow.canBePickedUp = 2;
					}
					else
					{
						player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(arrow);
						attackCooldown = maxAttackCooldown;
					}
				}
				else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.arrowCursed.effectId, itemStack) > 0)
				{
					EntityArrowCursed arrow = new EntityArrowCursed(world, player, velocity);
					arrow.setDamage(1.5F);

					int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
					if (enchantPower > 0)
					{
						arrow.setDamage(arrow.getDamage() + (double)enchantPower * 0.5D + 0.5D);
					}

					int enchantPunch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
					if (enchantPunch > 0)
					{
						arrow.setKnockbackStrength(enchantPunch);
					}

					itemStack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					if (canUseWithoutArrows)
					{
						arrow.canBePickedUp = 2;
					}
					else
					{
						player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(arrow);
						attackCooldown = maxAttackCooldown;
					}
				}
				else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.arrowCharged.effectId, itemStack) > 0)
				{
					EntityArrowCharged arrow = new EntityArrowCharged(world, player, velocity);
					arrow.setDamage(1.5F);

					int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
					if (enchantPower > 0)
					{
						arrow.setDamage(arrow.getDamage() + (double)enchantPower * 0.5D + 0.5D);
					}

					int enchantPunch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
					if (enchantPunch > 0)
					{
						arrow.setKnockbackStrength(enchantPunch);
					}

					itemStack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					if (canUseWithoutArrows)
					{
						arrow.canBePickedUp = 2;
					}
					else
					{
						player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(arrow);
						attackCooldown = maxAttackCooldown;
					}
				}
				else
				{
					EntityArrow arrow = new EntityArrow(world, player, velocity);
					arrow.setDamage(1.5F);

					int enchantPower = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);
					if (enchantPower > 0)
					{
						arrow.setDamage(arrow.getDamage() + (double)enchantPower * 0.5D + 0.5D);
					}

					int enchantPunch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);
					if (enchantPunch > 0)
					{
						arrow.setKnockbackStrength(enchantPunch);
					}

					itemStack.damageItem(1, player);
					world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					if (canUseWithoutArrows)
					{
						arrow.canBePickedUp = 2;
					}
					else
					{
						player.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
					}

					if (!world.isRemote)
					{
						world.spawnEntityInWorld(arrow);
						attackCooldown = maxAttackCooldown;
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
		}
	}

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
}
