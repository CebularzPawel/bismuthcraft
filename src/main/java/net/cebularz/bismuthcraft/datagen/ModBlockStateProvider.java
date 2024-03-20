package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        blockWithItem(ModBlocks.CHISELED_MUD_BRICKS);
        blockWithItem(ModBlocks.ROTTEN_FLESH_BLOCK);
        Top_Bottom_Side_Block(ModBlocks.REINFORCED_COBBLESTONE,"block/reinforced_cobblestone_side","block/reinforced_cobblestone_end","block/reinforced_cobblestone_end");
        Top_Bottom_Side_Block(ModBlocks.PINEAPPLE,"block/pineapple","block/pineapple_bottom","block/pineapple_top");
        axisBlock(((RotatedPillarBlock) ModBlocks.MUD_PILLAR.get()), blockTexture(ModBlocks.MUD_PILLAR.get()),
                new ResourceLocation(bismuthcraft.MOD_ID, "block/mud_pillar_end"));
        blockItem(ModBlocks.MUD_PILLAR);
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
