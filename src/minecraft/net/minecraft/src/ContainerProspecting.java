package net.minecraft.src;

import java.util.Random;

public class ContainerProspecting extends Container
{
    /** The crafting matrix inventory (3x3). */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 1, 1);
    public IInventory craftResult = new InventoryCraftResult();
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
	private boolean canProspect = false;
	public int[] lootList = new int[2];

    public ContainerProspecting(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5)
    {
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
		// result
        this.addSlotToContainer(new SlotProspecting(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 141, 35));
        int var6;
        int var7;
		
		// input
		this.addSlotToContainer(new Slot(this.craftMatrix, 0, 19, 35));

        for (var6 = 0; var6 < 3; ++var6)
        {
            for (var7 = 0; var7 < 9; ++var7)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
            }
        }

        for (var6 = 0; var6 < 9; ++var6)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
	public void onCraftMatrixChanged(IInventory inventory)
    {
		ItemStack itemSlotInput = this.craftMatrix.getStackInSlot(0);
		ItemStack output = this.craftResult.getStackInSlot(0);
		
		if (itemSlotInput != null && output == null && this.isItemProspectable(itemSlotInput))
		{
			this.setProspect(true);
		}
		else
		{
			this.setProspect(false);
		}
		
		/*this.setLootResults();
		
		ItemStack input = this.craftMatrix.getStackInSlot(0);
		ItemStack output = this.craftResult.getStackInSlot(0);

		if (input != null && output == null && this.isItemProspectable(input))
		{
			if(Item.itemsList[this.lootList[0]] != null)
			{
				this.craftResult.setInventorySlotContents(0, new ItemStack(Item.itemsList[this.lootList[0]], this.lootList[1]));
			}
			
			this.onCraftMatrixChanged(this.craftMatrix);
		}*/
    }
	
	public boolean prospectItem(int itemId, int quantity)
	{
		ItemStack input = this.craftMatrix.getStackInSlot(0);
		ItemStack output = this.craftResult.getStackInSlot(0);

		if (input != null && output == null && this.isItemProspectable(input))
		{
			if(!this.worldObj.isRemote)
			{
				this.craftMatrix.decrStackSize(0, 1);
				this.onCraftMatrixChanged(this.craftMatrix);
			}

			if(Item.itemsList[itemId] != null)
			{
				this.craftResult.setInventorySlotContents(0, new ItemStack(Item.itemsList[itemId], quantity));
			}

			return true;
		}
		else
		{
			return false;
		}
	}
	
	private int nextRange(int min, int max)
	{
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	
	public void setLootResults()
	{
		ItemStack input = this.craftMatrix.getStackInSlot(0);
		this.lootList[0] = 0;
		this.lootList[1] = 0;
		int nextRange;
		
		if(input != null)
		{
			if(input.getItem() == Item.dustyRelic)
			{
				nextRange = nextRange(0, 20);
				if(nextRange == 0) {
					this.lootList[0] = Item.swordDiamond.shiftedIndex;
					this.lootList[1] = 1;
				}
				if(nextRange > 0 && nextRange <= 7) {
					this.lootList[0] = Item.opal.shiftedIndex;
					this.lootList[1] = nextRange(1, 2);
				}
				if(nextRange > 7) {
					int nextGem = nextRange(0, 3);
					this.lootList[0] = nextGem == 0 ? Item.ruby.shiftedIndex : (
									   nextGem == 1 ? Item.sapphire.shiftedIndex : (
									   nextGem == 2 ? Item.malachite.shiftedIndex :
													  Item.amethyst.shiftedIndex));
					this.lootList[1] = nextRange(1, 3);
				}
			}
			else if(input.getItem() == Item.hugeGeode)
			{
				nextRange = nextRange(0, 5);
				if(nextRange == 0) {
					this.lootList[0] = Item.opal.shiftedIndex;
					this.lootList[1] = nextRange(1, 2);
				}
				if(nextRange > 0) {
					int nextGem = nextRange(0, 3);
					this.lootList[0] = nextGem == 0 ? Item.ruby.shiftedIndex : (
									   nextGem == 1 ? Item.sapphire.shiftedIndex : (
									   nextGem == 2 ? Item.malachite.shiftedIndex :
													  Item.amethyst.shiftedIndex));
					this.lootList[1] = nextRange(1, 2);
				}
			}
			else 
			{
				nextRange = nextRange(0, 20);
				if(nextRange == 0) {
					this.lootList[0] = Item.opal.shiftedIndex;
					this.lootList[1] = 1;
				}
				if(nextRange > 0 && nextRange <= 5) {
					int nextGem = nextRange(0, 3);
					this.lootList[0] = nextGem == 0 ? Item.ruby.shiftedIndex : (
									   nextGem == 1 ? Item.sapphire.shiftedIndex : (
									   nextGem == 2 ? Item.malachite.shiftedIndex :
													  Item.amethyst.shiftedIndex));
					this.lootList[1] = 1;
				}
				if(nextRange > 5) {
					this.lootList[0] = nextRange(0, 1) == 0 ? Block.sand.blockID : Block.gravel.blockID;
					this.lootList[1] = nextRange(1, 3);
				}
			}
		}
	}

    /**
     * Callback for when the crafting gui is closed.
     */
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);

        if (!this.worldObj.isRemote)
        {
			ItemStack var3 = this.craftMatrix.getStackInSlotOnClosing(0);
			ItemStack var4 = this.craftResult.getStackInSlotOnClosing(0);

			if (var3 != null)
			{
				par1EntityPlayer.dropPlayerItem(var3);
			}
			if (var4 != null)
			{
				par1EntityPlayer.dropPlayerItem(var4);
			}
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockId(this.posX, this.posY, this.posZ) != Block.prospectingTable.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.posX + 0.5D, (double)this.posY + 0.5D, (double)this.posZ + 0.5D) <= 64.0D;
    }

   /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStackCopy = slot.getStack();
            itemStack = itemStackCopy.copy();

            if (slotNumber == 0)
            {
                if (!this.mergeItemStack(itemStackCopy, 2, 38, true))
                {
                    return null;
                }

                slot.onSlotChange(itemStackCopy, itemStack);
            }
			else if (slotNumber == 1)
            {
                if (!this.mergeItemStack(itemStackCopy, 2, 29, true))
                {
					if (!this.mergeItemStack(itemStackCopy, 2, 38, true))
					{
						return null;
					}
				}

                slot.onSlotChange(itemStackCopy, itemStack);
            }
            else if (slotNumber >= 2 && slotNumber < 38)
            {
                if (!this.mergeItemStack(itemStackCopy, 1, 2, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemStackCopy, 2, 38, false))
            {
                return null;
            }

            if (itemStackCopy.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemStackCopy.stackSize == itemStack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemStackCopy);
        }

        return null;
    }
	
	private boolean isItemProspectable(ItemStack item)
	{
		return item.getItem() instanceof ItemArtifact;
	}
	
	public void setProspect(boolean result)
	{
		this.canProspect = result;
	}
	
	public boolean getProspect()
	{
		return this.canProspect;
	}
}
