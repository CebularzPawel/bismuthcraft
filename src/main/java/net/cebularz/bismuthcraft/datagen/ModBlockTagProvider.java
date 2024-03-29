package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, bismuthcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(),
                        ModBlocks.BISMUTH_BLOCK.get()
                );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(),
                    ModBlocks.BISMUTH_BLOCK.get(),
                        ModBlocks.MUD_PILLAR.get(),
                        ModBlocks.VERDANT_MUD_LAMP.get(),
                        ModBlocks.OCHRE_MUD_LAMP.get(),
                        ModBlocks.PEARLESCENT_MUD_LAMP.get(),
                        ModBlocks.CHISELED_MUD_BRICKS.get(),
                        ModBlocks.REINFORCED_COBBLESTONE.get()
                );


        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SPRUCE_PLANKS_REINFORCED_COBBLESTONE.get(),
                    ModBlocks.BIRCH_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.DARK_OAK_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.ACACIA_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.BAMBOO_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.CHERRY_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.CRIMSON_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.OAK_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.JUNGLE_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.MANGROVE_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.WARPED_PLANKS_REINFORCED_COBBLESTONE.get(),
                        ModBlocks.PINEAPPLE.get()

                );
    }
}
