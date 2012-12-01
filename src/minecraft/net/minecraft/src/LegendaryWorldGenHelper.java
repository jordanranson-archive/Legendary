package net.minecraft.src;

import java.util.Random;

public class LegendaryWorldGenHelper 
{
	public static void growLichen(World world, int blockX, int blockY, int blockZ, int offsetY, int frequency, Random random)
	{
		if (random.nextInt(frequency) == 0 && world.isAirBlock(blockX - 1, blockY + offsetY, blockZ))
		{
			world.setBlockAndMetadata(blockX - 1, blockY + offsetY, blockZ, Block.lichen.blockID, 8);
		}

		if (random.nextInt(frequency) == 0 && world.isAirBlock(blockX + 1, blockY + offsetY, blockZ))
		{
			world.setBlockAndMetadata(blockX + 1, blockY + offsetY, blockZ, Block.lichen.blockID, 2);
		}

		if (random.nextInt(frequency) == 0 && world.isAirBlock(blockX, blockY + offsetY, blockZ - 1))
		{
			world.setBlockAndMetadata(blockX, blockY + offsetY, blockZ - 1, Block.lichen.blockID, 1);
		}

		if (random.nextInt(frequency) == 0 && world.isAirBlock(blockX, blockY + offsetY, blockZ + 1))
		{
			world.setBlockAndMetadata(blockX, blockY + offsetY, blockZ + 1, Block.lichen.blockID, 4);
		}
	}
}