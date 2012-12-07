package net.minecraft.src;

import java.util.Random;

public class EntitySuperSheep extends EntitySheep
{
    public EntitySuperSheep(World par1World)
    {
        super(par1World);
    }

    public int getMaxHealth()
    {
        return 5000;
    }

    public static int getRandomFleeceColor(Random par0Random)
    {
        return 10;
    }
}
