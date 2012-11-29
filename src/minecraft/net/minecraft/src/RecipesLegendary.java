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
		craftingManager.addRecipe(new ItemStack(Block.prospectingTable), new Object[] {"CGC", "###", 'C', Block.cloth, 'G', Block.glass, '#', Block.cobblestone});
		craftingManager.addRecipe(new ItemStack(Item.redstoneSprocket), new Object[] {" # ", "#R#", " # ", '#', Item.ingotIron, 'R', Item.redstone});
		craftingManager.addRecipe(new ItemStack(Item.crossbow), new Object[] {"B", "#", "S", 'B', Item.bow, '#', Item.redstoneSprocket, 'S', Item.stick});
		craftingManager.addRecipe(new ItemStack(Item.cannonball, 8), new Object[] {" I ", "I#I", " I ", 'I', Item.ingotIron, '#', Block.cobblestone});
		craftingManager.addRecipe(new ItemStack(Item.cannonball, 8), new Object[] {" I ", "I#I", " I ", 'I', Item.ingotIron, '#', Block.blockSteel});
		craftingManager.addRecipe(new ItemStack(Item.cannonball, 8), new Object[] {" I ", "I#I", " I ", 'I', Item.ingotIron, '#', Block.tnt});
		craftingManager.addRecipe(new ItemStack(Item.cannon), new Object[] {"  I", "P# ", "S I", 'I', Item.ingotIron, '#', Item.redstoneSprocket, 'S', Item.stick, 'P', Block.planks});
    }
}
