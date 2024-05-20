package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, bismuthcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BISMUTH);
        simpleItem(ModItems.BISMUTH_BALL);
        simpleItem(ModItems.PINEAPPLE_SLICE);
        simpleItem(ModItems.PACKED_MUD_BALL);
        simpleItem(ModItems.SINKING_MUD_BUCKET);
        wallItem(ModBlocks.DRIED_MUD_BRICKS_WALL, ModBlocks.DRIED_MUD_BRICKS);
        evenSimplerBlockItem(ModBlocks.DRIED_MUD_BRICKS_STAIRS);
        evenSimplerBlockItem(ModBlocks.DRIED_MUD_BRICKS_SLAB);

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(bismuthcraft.MOD_ID,"item/" + item.getId().getPath()));
    }
    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(bismuthcraft.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(bismuthcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

}
