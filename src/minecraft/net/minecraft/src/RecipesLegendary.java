package net.minecraft.src;

public class RecipesLegendary
{
    /**
     * Adds the food recipes to the CraftingManager.
     */
    public void addRecipes(CraftingManager craftingManager)
    {
        craftingManager.addShapelessRecipe(new ItemStack(Item.opalEssence, 4), new Object[] {Item.opal});
        craftingManager.addShapelessRecipe(new ItemStack(Item.ruby, 9), new Object[] {Block.blockRuby});
        craftingManager.addShapelessRecipe(new ItemStack(Item.sapphire, 9), new Object[] {Block.blockSapphire});
        craftingManager.addShapelessRecipe(new ItemStack(Item.malachite, 9), new Object[] {Block.blockMalachite});
        craftingManager.addShapelessRecipe(new ItemStack(Item.amethyst, 9), new Object[] {Block.blockAmethyst});
        craftingManager.addRecipe(new ItemStack(Block.blockRuby), new Object[] {"###", "###", "###", '#', new ItemStack(Item.ruby)});
        craftingManager.addRecipe(new ItemStack(Block.blockSapphire), new Object[] {"###", "###", "###", '#', new ItemStack(Item.sapphire)});
        craftingManager.addRecipe(new ItemStack(Block.blockMalachite), new Object[] {"###", "###", "###", '#', new ItemStack(Item.malachite)});
        craftingManager.addRecipe(new ItemStack(Block.blockAmethyst), new Object[] {"###", "###", "###", '#', new ItemStack(Item.amethyst)});
    }
}
