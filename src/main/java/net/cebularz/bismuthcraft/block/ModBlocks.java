package net.cebularz.bismuthcraft.block;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.custom.*;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS=
            DeferredRegister.create(ForgeRegistries.BLOCKS, bismuthcraft.MOD_ID);
    public static final RegistryObject<Block> DEEPSLATE_BISMUTH_ORE = registerBlock("deepslate_bismuth_ore",
        ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SPRUCE_PANELS = registerBlock("spruce_panels",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> REINFORCED_COBBLESTONE = registerBlock("reinforced_cobblestone",
            ()-> new ReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(10).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SPRUCE_PLANKS_REINFORCED_COBBLESTONE = registerBlock("spruce_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.SPRUCE_PLANKS));
    public static final RegistryObject<Block> BIRCH_PLANKS_REINFORCED_COBBLESTONE = registerBlock("birch_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.BIRCH_PLANKS));
    public static final RegistryObject<Block> DARK_OAK_PLANKS_REINFORCED_COBBLESTONE = registerBlock("dark_oak_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.DARK_OAK_PLANKS));
    public static final RegistryObject<Block> ACACIA_PLANKS_REINFORCED_COBBLESTONE = registerBlock("acacia_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.ACACIA_PLANKS));
    public static final RegistryObject<Block> BAMBOO_PLANKS_REINFORCED_COBBLESTONE = registerBlock("bamboo_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.BAMBOO_PLANKS));
    public static final RegistryObject<Block> CHERRY_PLANKS_REINFORCED_COBBLESTONE = registerBlock("cherry_planks_reinforced_cobblestone",
            ()-> new UpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.CHERRY_PLANKS));
    public static final RegistryObject<Block> CRIMSON_PLANKS_REINFORCED_COBBLESTONE = registerBlock("crimson_planks_reinforced_cobblestone",
            ()-> new NetherUpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.CRIMSON_PLANKS));
    public static final RegistryObject<Block> WARPED_PLANKS_REINFORCED_COBBLESTONE = registerBlock("warped_planks_reinforced_cobblestone",
            ()-> new NetherUpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.WARPED_PLANKS));
    public static final RegistryObject<Block> OAK_PLANKS_REINFORCED_COBBLESTONE = registerBlock("oak_planks_reinforced_cobblestone",
            ()-> new NetherUpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.OAK_PLANKS));
    public static final RegistryObject<Block> JUNGLE_PLANKS_REINFORCED_COBBLESTONE = registerBlock("jungle_planks_reinforced_cobblestone",
            ()-> new NetherUpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.JUNGLE_PLANKS));
    public static final RegistryObject<Block> MANGROVE_PLANKS_REINFORCED_COBBLESTONE = registerBlock("mangrove_planks_reinforced_cobblestone",
            ()-> new NetherUpgradedReinforcedCobblestone(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(1).requiresCorrectToolForDrops(),Blocks.MANGROVE_PLANKS));
    public static final RegistryObject<Block> RANDOMGENERATOR = registerBlock("randomgenerator",
            ()-> new RandomGeneratorBlock2(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PINEAPPLE = registerBlock("pineapple",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MELON).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MUD_PILLAR = registerBlock("mud_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));


    public static final RegistryObject<Block> MUD_FARMLAND = registerBlock("mud_farmland",
            ()-> new MudFarmLand(BlockBehaviour.Properties.copy(Blocks.FARMLAND).sound(SoundType.MUD).mapColor(MapColor.COLOR_GRAY)));
    public static final RegistryObject<Block> SINKING_MUD = registerBlock("sinking_mud",
            ()-> new SinkingMudBlock(BlockBehaviour.Properties.copy(Blocks.MUD)));
    public static final RegistryObject<Block> DRIED_MUD = registerBlock("dried_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE).strength(0.5f)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS = registerBlock("dried_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_STAIRS = registerBlock("dried_mud_bricks_stairs",
            ()-> new StairBlock(()-> ModBlocks.DRIED_MUD_BRICKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_SLAB = registerBlock("dried_mud_bricks_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_BRICKS_WALL = registerBlock("dried_mud_bricks_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS)));
    public static final RegistryObject<Block> DRIED_MUD_PILLAR = registerBlock("dried_mud_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(ModBlocks.MUD_PILLAR.get())));
    public static final RegistryObject<Block> POLISHED_DRIED_MUD = registerBlock("polished_dried_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MUD_SPLASH = registerBlock("mud_splash",
            ()-> new MudSplashBlock(BlockBehaviour.Properties.copy(Blocks.MUD).mapColor(MapColor.GLOW_LICHEN).replaceable().noCollission().strength(0.2F)));
    public static final RegistryObject<Block> POLISHED_PACKED_MUD = registerBlock("polished_packed_mud",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_DRIED_MUD_BRICKS = registerBlock("chiseled_dried_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_MUD_LAMP = registerBlock("verdant_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OCHRE_MUD_LAMP = registerBlock("ochre_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLESCENT_MUD_LAMP = registerBlock("pearlescent_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> VERDANT_DRIED_MUD_LAMP = registerBlock("verdant_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OCHRE_DRIED_MUD_LAMP = registerBlock("ochre_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLESCENT_DRIED_MUD_LAMP = registerBlock("pearlescent_dried_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));



    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ROTTEN_FLESH_BLOCK = registerBlock("rotten_flesh_block",
            ()-> new RottenFleshBlock(BlockBehaviour.Properties.copy(Blocks.MUD).strength(0.2f)));
    public static final RegistryObject<Block> FLESH_BLOCK = registerBlock("flesh_block",
            ()-> new SentientFleshBlock(BlockBehaviour.Properties.copy(Blocks.MUD).strength(0.3f).randomTicks()));
    public static final RegistryObject<Block> POISON_FLESH_BLOCK = registerBlock("poison_flesh_block",
            ()-> new SentientFleshBlockPoisonBlock(BlockBehaviour.Properties.copy(Blocks.MUD).strength(0.3f).randomTicks()));

    public static final RegistryObject<Block> EYE_OF_FLESH = registerBlock("eye_of_flesh",
            ()-> new SentientFleshEye(BlockBehaviour.Properties.copy(Blocks.MUD).strength(0.3f).randomTicks()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
       RegistryObject<T> toReturn = BLOCKS.register(name,block);
       registerBlockitem(name,toReturn);
       return toReturn;
   }
    private static <T extends Block> RegistryObject<Item> registerBlockitem(String name,RegistryObject<T>block){
        return ModItems.ITEMS.register(name,()->new BlockItem(block.get(),new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
