package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityFallingLantern extends EntityFallingSand
{
	public EntityFallingLantern(World par1World)
    {
        super(par1World);
    }

    public EntityFallingLantern(World par1World, double par2, double par4, double par6, int par8)
    {
        super(par1World, par2, par4, par6, par8);
    }

    public EntityFallingLantern(World par1World, double par2, double par4, double par6, int par8, int par9)
    {
        super(par1World, par2, par4, par6, par8, par9);
    }

    public void onUpdate()
    {
        if (this.blockID == 0)
        {
            this.setDead();
        }
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            ++this.fallTime;
            this.motionY -= 0.03999999910593033D;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;

            if (!this.worldObj.isRemote)
            {
                int var1 = MathHelper.floor_double(this.posX);
                int var2 = MathHelper.floor_double(this.posY);
                int var3 = MathHelper.floor_double(this.posZ);

                if (this.fallTime == 1)
                {
                    if (this.fallTime == 1 && this.worldObj.getBlockId(var1, var2, var3) == this.blockID)
                    {
                        this.worldObj.setBlockWithNotify(var1, var2, var3, 0);
                    }
                    else
                    {
                        this.setDead();
                    }
                }

                if (this.onGround)
                {
                    this.motionX *= 0.699999988079071D;
                    this.motionZ *= 0.699999988079071D;
                    this.motionY *= -0.5D;

                    if (this.worldObj.getBlockId(var1, var2, var3) != Block.pistonMoving.blockID)
                    {
                        this.setDead();
						if (this.shouldDropItem)
                        {
                            this.entityDropItem(new ItemStack(this.blockID, 1, 0), 0.0F);
                        }
                    }
                }
                else if (this.fallTime > 100 && !this.worldObj.isRemote && (var2 < 1 || var2 > 256) || this.fallTime > 600)
                {
                    if (this.shouldDropItem)
                    {
                        this.entityDropItem(new ItemStack(this.blockID, 1, 0), 0.0F);
                    }

                    this.setDead();
                }
            }
        }
    }

    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setByte("Tile", (byte)this.blockID);
        par1NBTTagCompound.setByte("Data", (byte)this.metadata);
        par1NBTTagCompound.setByte("Time", (byte)this.fallTime);
        par1NBTTagCompound.setBoolean("DropItem", this.shouldDropItem);
    }

    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.blockID = par1NBTTagCompound.getByte("Tile") & 255;
        this.metadata = par1NBTTagCompound.getByte("Data") & 255;
        this.fallTime = par1NBTTagCompound.getByte("Time") & 255;

        if (par1NBTTagCompound.hasKey("DropItem"))
        {
            this.shouldDropItem = par1NBTTagCompound.getBoolean("DropItem");
        }

        if (this.blockID == 0)
        {
            this.blockID = Block.lantern.blockID;
        }
    }
}
