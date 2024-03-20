package net.cebularz.bismuthcraft.datagen.loot;

import net.cebularz.bismuthcraft.block.ModBlocks;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {


    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        this.dropSelf(ModBlocks.SPRUCE_PANELS.get());
        this.dropSelf(ModBlocks.PINEAPPLE.get());
        this.dropSelf(ModBlocks.MUD_PILLAR.get());
        this.dropSelf(ModBlocks.VERDANT_MUD_LAMP.get());
        this.dropSelf(ModBlocks.OCHRE_MUD_LAMP.get());
        this.dropSelf(ModBlocks.PEARLESCENT_MUD_LAMP.get());
        this.dropSelf(ModBlocks.CHISELED_MUD_BRICKS.get());
        this.dropSelf(ModBlocks.ROTTEN_FLESH_BLOCK.get());

        this.add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(),
                block -> createBismuthOreBlocks(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), ModItems.BISMUTH.get()));
    }


    protected LootTable.Builder createBismuthOreBlocks(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock, (LootPoolEntryContainer.Builder) this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

        @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
