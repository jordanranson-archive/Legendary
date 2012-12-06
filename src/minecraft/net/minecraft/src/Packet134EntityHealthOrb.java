package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet134EntityHealthOrb extends Packet
{
    /** Entity ID for the XP Orb */
    public int entityId;
    public int posX;
    public int posY;
    public int posZ;

    /** The Orbs Experience points value. */
    public int xpValue;

    public Packet134EntityHealthOrb() {}

    public Packet134EntityHealthOrb(EntityHealthOrb par1EntityHealthOrb)
    {
        this.entityId = par1EntityHealthOrb.entityId;
        this.posX = MathHelper.floor_double(par1EntityHealthOrb.posX * 32.0D);
        this.posY = MathHelper.floor_double(par1EntityHealthOrb.posY * 32.0D);
        this.posZ = MathHelper.floor_double(par1EntityHealthOrb.posZ * 32.0D);
        this.xpValue = par1EntityHealthOrb.getXpValue();
    }

    /**
     * Abstract. Reads the raw packet data from the data stream.
     */
    public void readPacketData(DataInputStream par1DataInputStream) throws IOException
    {
        this.entityId = par1DataInputStream.readInt();
        this.posX = par1DataInputStream.readInt();
        this.posY = par1DataInputStream.readInt();
        this.posZ = par1DataInputStream.readInt();
        this.xpValue = par1DataInputStream.readShort();
    }

    /**
     * Abstract. Writes the raw packet data to the data stream.
     */
    public void writePacketData(DataOutputStream par1DataOutputStream) throws IOException
    {
        par1DataOutputStream.writeInt(this.entityId);
        par1DataOutputStream.writeInt(this.posX);
        par1DataOutputStream.writeInt(this.posY);
        par1DataOutputStream.writeInt(this.posZ);
        par1DataOutputStream.writeShort(this.xpValue);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(NetHandler par1NetHandler)
    {
        par1NetHandler.handleEntityHealthOrb(this);
    }

    /**
     * Abstract. Return the size of the packet (not counting the header).
     */
    public int getPacketSize()
    {
        return 18;
    }
}
