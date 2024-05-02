package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.block.ModBlocks;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.POLISHED_PACKED_MUD.get().asItem(),Blocks.PACKED_MUD);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.CHISELED_MUD_BRICKS.get().asItem(),Blocks.PACKED_MUD);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,Blocks.MUD_BRICKS.asItem(),Blocks.PACKED_MUD);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.MUD_PILLAR.get().asItem(),Blocks.PACKED_MUD);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.POLISHED_PACKED_MUD.get().asItem(),Blocks.MUD_BRICKS);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.CHISELED_MUD_BRICKS.get().asItem(),Blocks.MUD_BRICKS);

        stonecutterResultFromBase(consumer,RecipeCategory.MISC,ModBlocks.MUD_PILLAR.get().asItem(),Blocks.MUD_BRICKS);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_MUD_BRICKS.get())
                .pattern("M  ")
                .pattern("M  ")
                .pattern("   ")
                .define('M',Blocks.MUD_BRICK_SLAB)
                .unlockedBy(getHasName(Blocks.MUD_BRICK_SLAB), has(Blocks.MUD_BRICK_SLAB))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B',ModItems.BISMUTH.get())
                .unlockedBy(getHasName(ModItems.BISMUTH.get()), has(ModItems.BISMUTH.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BISMUTH_BALL.get())
                .pattern(" B ")
                .pattern("BBB")
                .pattern(" B ")
                .define('B',ModItems.BISMUTH.get())
                .unlockedBy(getHasName(ModItems.BISMUTH.get()), has(ModItems.BISMUTH.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VERDANT_MUD_LAMP.get(),1)
                .pattern(" M ")
                .pattern(" G ")
                .pattern(" M ")
                .define('G', Blocks.VERDANT_FROGLIGHT)
                .define('M', Blocks.MUD_BRICK_SLAB)
                .unlockedBy(getHasName(Blocks.VERDANT_FROGLIGHT), has(Blocks.VERDANT_FROGLIGHT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OCHRE_MUD_LAMP.get(),1)
                .pattern(" M ")
                .pattern(" G ")
                .pattern(" M ")
                .define('G', Blocks.OCHRE_FROGLIGHT)
                .define('M', Blocks.MUD_BRICK_SLAB)
                .unlockedBy(getHasName(Blocks.OCHRE_FROGLIGHT), has(Blocks.OCHRE_FROGLIGHT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEARLESCENT_MUD_LAMP.get(),1)
                .pattern(" M ")
                .pattern(" G ")
                .pattern(" M ")
                .define('G', Blocks.PEARLESCENT_FROGLIGHT)
                .define('M', Blocks.MUD_BRICK_SLAB)
                .unlockedBy(getHasName(Blocks.PEARLESCENT_FROGLIGHT), has(Blocks.PEARLESCENT_FROGLIGHT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.MUD)
                .pattern("MM ")
                .pattern("MM ")
                .pattern("   ")
                .define('M',ModItems.MUD_BALL.get())
                .unlockedBy(getHasName(ModItems.MUD_BALL.get()), has(ModItems.MUD_BALL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PACKED_MUD)
                .pattern("MM ")
                .pattern("MM ")
                .pattern("   ")
                .define('M',ModItems.PACKED_MUD_BALL.get())
                .unlockedBy(getHasName(ModItems.PACKED_MUD_BALL.get()), has(ModItems.PACKED_MUD_BALL.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MUD_PILLAR.get(),2)
                .pattern("M  ")
                .pattern("M  ")
                .pattern("   ")
                .define('M',Items.PACKED_MUD)
                .unlockedBy(getHasName(Items.PACKED_MUD), has(Items.PACKED_MUD))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.BISMUTH.get(),9)
                .requires(ModBlocks.BISMUTH_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK.get()),has(ModBlocks.BISMUTH_BLOCK.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PINEAPPLE_SLICE.get(),4)
                .requires(ModBlocks.PINEAPPLE.get())
                .unlockedBy(getHasName(ModBlocks.PINEAPPLE.get()),has(ModBlocks.PINEAPPLE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.PACKED_MUD_BALL.get(),4)
                .requires(ModItems.MUD_BALL.get(),4).requires(Items.WHEAT,1)
                .unlockedBy(getHasName(ModItems.MUD_BALL.get()),has(ModItems.MUD_BALL.get()))
                .save(consumer);
    }
}
