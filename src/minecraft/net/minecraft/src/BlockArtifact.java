package net.minecraft.src;

import java.util.Random;
import java.util.ArrayList;

public class BlockArtifact extends Block
{
    public BlockArtifact(int par1, int par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random random, int par3)
    {
		ArrayList<Item> lootTable = new ArrayList<Item>();
		
		lootTable.add(Item.roughStone);
		lootTable.add(Item.roughStone);
		lootTable.add(Item.hugeGeode);
		
        if(this.blockID == Block.artifactFossil.blockID)
		{
			lootTable.add(Item.roughStone);
			lootTable.add(Item.bone);
			lootTable.add(Item.bone);
		}
		
		else if(this.blockID == Block.artifactSparkling.blockID)
		{
			lootTable.add(Item.hugeGeode);
			lootTable.add(Item.dustyRelic);
		}
		
		return lootTable.get(random.nextInt(lootTable.size())).shiftedIndex;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1 + random.nextInt(4);
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && this.blockID != this.idDropped(0, par2Random, par1))
        {
            int var3 = par2Random.nextInt(par1 + 2) - 1;

            if (var3 < 0)
            {
                var3 = 0;
            }

            return this.quantityDropped(par2Random) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }
}
