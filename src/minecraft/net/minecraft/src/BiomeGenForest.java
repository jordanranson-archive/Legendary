package net.minecraft.src;

import java.util.Random;

public class BiomeGenForest extends BiomeGenBase
{
    public BiomeGenForest(int par1)
    {
        super(par1);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
        this.theBiomeDecorator.treesPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 2;
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
		/** Legendary Mod */
        return (WorldGenerator)(par1Random.nextInt(5) == 0 ? this.worldGeneratorForest : 
		(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : 
		(par1Random.nextInt(24) == 0 ? new WorldGenTrees(false, 4, 0, 0, 3) : 
		this.worldGeneratorTrees)));
		/** end Legendary Mod */
    }
}
