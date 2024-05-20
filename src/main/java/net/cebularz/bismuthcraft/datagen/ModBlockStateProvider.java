package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, bismuthcraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_BISMUTH_ORE);
        blockWithItem(ModBlocks.SPRUCE_PANELS);

        axisBlock(((RotatedPillarBlock) ModBlocks.MUD_PILLAR.get()), blockTexture(ModBlocks.MUD_PILLAR.get()),
                new ResourceLocation(bismuthcraft.MOD_ID, "block/mud_pillar_end"));
        blockItem(ModBlocks.MUD_PILLAR);
        blockWithItem(ModBlocks.CHISELED_MUD_BRICKS);
        blockWithItem(ModBlocks.ROTTEN_FLESH_BLOCK);
        blockWithItem(ModBlocks.FLESH_BLOCK);
        blockWithItem(ModBlocks.POISON_FLESH_BLOCK);
        blockWithItem(ModBlocks.POLISHED_PACKED_MUD);
        blockWithItem(ModBlocks.DRIED_MUD);
        blockWithItem(ModBlocks.DRIED_MUD_BRICKS);
        blockWithItem(ModBlocks.POLISHED_DRIED_MUD);
        blockWithItem(ModBlocks.CHISELED_DRIED_MUD_BRICKS);
        axisBlock(((RotatedPillarBlock) ModBlocks.DRIED_MUD_PILLAR.get()), blockTexture(ModBlocks.DRIED_MUD_PILLAR.get()),
                new ResourceLocation(bismuthcraft.MOD_ID, "block/dried_mud_pillar_end"));
        blockItem(ModBlocks.DRIED_MUD_PILLAR);

        stairsBlock(((StairBlock) ModBlocks.DRIED_MUD_BRICKS_STAIRS.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.DRIED_MUD_BRICKS_SLAB.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.DRIED_MUD_BRICKS_WALL.get()),blockTexture(ModBlocks.DRIED_MUD_BRICKS.get()));



        Top_Bottom_Side_Block(ModBlocks.DARK_OAK_PLANKS_REINFORCED_COBBLESTONE,"block/dark_oak_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.BIRCH_PLANKS_REINFORCED_COBBLESTONE,"block/birch_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.ACACIA_PLANKS_REINFORCED_COBBLESTONE,"block/acacia_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.BAMBOO_PLANKS_REINFORCED_COBBLESTONE,"block/bamboo_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.CHERRY_PLANKS_REINFORCED_COBBLESTONE,"block/cherry_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.CRIMSON_PLANKS_REINFORCED_COBBLESTONE,"block/crimson_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.OAK_PLANKS_REINFORCED_COBBLESTONE,"block/oak_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.JUNGLE_PLANKS_REINFORCED_COBBLESTONE,"block/jungle_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.MANGROVE_PLANKS_REINFORCED_COBBLESTONE,"block/mangrove_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.WARPED_PLANKS_REINFORCED_COBBLESTONE,"block/warped_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");

        Top_Bottom_Side_Block(ModBlocks.SPRUCE_PLANKS_REINFORCED_COBBLESTONE,"block/spruce_planks_reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.REINFORCED_COBBLESTONE,"block/reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.PINEAPPLE,"block/pineapple","block/pineapple_bottom","block/pineapple_top");

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }
    private void Top_Bottom_Side_Block(RegistryObject<Block> blockRegistryObject, String sideTexture, String bottomTexture, String topTexture) {
        ResourceLocation side = modLoc(sideTexture);
        ResourceLocation bottom = modLoc(bottomTexture);
        ResourceLocation top = modLoc(topTexture);
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeBottomTop(blockRegistryObject.getId().getPath(), side, bottom, top));

    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(bismuthcraft.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void Pillar_Block(RegistryObject<Block> blockRegistryObject, String sideTexture, String endTexture) {
        ResourceLocation side = modLoc(sideTexture);
        ResourceLocation end = modLoc(endTexture);
        simpleBlockWithItem(blockRegistryObject.get(), models().cubeColumnHorizontal(blockRegistryObject.getId().getPath(),side,end));
        BlockModelBuilder model1 = models().cubeColumn(blockRegistryObject.getId().getPath(),side,end);
        BlockModelBuilder model2 = models().cubeColumnHorizontal(blockRegistryObject.getId().getPath(),side,end);


    }
}
