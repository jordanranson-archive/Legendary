package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class EntityChainLightningBolt extends Entity
{
	private int boltLivingTime = 10;
	public EntityLiving entityStart;
	public EntityLiving entityEnd;
	public ArrayList<Vector3> lineVectors;
	
    public EntityChainLightningBolt(World par1World, EntityLiving entityStart, EntityLiving entityEnd)
    {
        super(par1World);
		this.entityStart = entityStart;
		this.entityEnd = entityEnd;
        this.setLocationAndAngles(entityStart.posX, entityStart.posY, entityStart.posZ, 0.0F, 0.0F);
		
		this.lineVectors = new ArrayList<Vector3>();
		Random random = new Random();

		double x1 = this.entityStart.posX;
		double y1 = this.entityStart.posY + (this.entityStart.height / 2.0D);
		double z1 = this.entityStart.posZ;
		double x2 = this.entityEnd.posX;
		double y2 = this.entityEnd.posY + (this.entityEnd.height / 2.0D);
		double z2 = this.entityEnd.posZ;
		double x = x2 - x1;
		double y = y2 - y1;
		double z = z2 - z1;
		double segments = 5;
		double distance = Math.sqrt(x * x + z * z + y * y);
		double width = distance / segments;
		double prevX = x1;
		double prevY = y1;
		double prevZ = z1;
		double vertexWidth = 0.1D;
		Vector3 lineStart;
		Vector3 lineEnd;
		
		for(int i = 0; i <= segments; i++) 
		{
			double magnitude = (width * i) / distance;

			double x3 = magnitude * x2 + (1 - magnitude) * x1;
			double y3 = magnitude * y2 + (1 - magnitude) * y1;
			double z3 = magnitude * z2 + (1 - magnitude) * z1;
			
			/*if(i !== 0 && i !== segments) {
				x3 += (random.nextDouble() * width) - (width / 2);
				y3 += (random.nextDouble() * width) - (width / 2);
				z3 += (random.nextDouble() * width) - (width / 2);
			}*/
			
			entityStart.worldObj.spawnParticle("reddust", x3, y3, z3, 0.0D, 0.0D, 0.0D);
			
			lineStart = new Vector3(prevX, prevY, prevZ);
			lineEnd = new Vector3(x3, y3, z3);
			this.lineVectors.add(lineStart);
			this.lineVectors.add(lineEnd);
			
			prevX = x3;
			prevY = y3;
			prevZ = z3;
		}
		
		
		System.out.println("WHAT");
    }

    public void onUpdate()
    {
        super.onUpdate();
		
		if(this.boltLivingTime-- <= 0)
		{
			this.setDead();
		}
    }
	
	protected void entityInit() {}
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
}
