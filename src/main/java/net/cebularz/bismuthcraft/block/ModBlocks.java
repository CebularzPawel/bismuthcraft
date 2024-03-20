package net.cebularz.bismuthcraft.block;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.custom.MudLampBlock;
import net.cebularz.bismuthcraft.block.custom.RottenFleshBlock;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(12).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PINEAPPLE = registerBlock("pineapple",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MELON).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MUD_PILLAR = registerBlock("mud_pillar",
            ()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> VERDANT_MUD_LAMP = registerBlock("verdant_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> OCHRE_MUD_LAMP = registerBlock("ochre_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PEARLESCENT_MUD_LAMP = registerBlock("pearlescent_mud_lamp",
            ()-> new MudLampBlock(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).lightLevel(state -> state.getValue(MudLampBlock.LIT) ? 15 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_MUD_BRICKS = registerBlock("chiseled_mud_bricks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.MUD_BRICKS).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ROTTEN_FLESH_BLOCK = registerBlock("rotten_flesh_block",
            ()-> new RottenFleshBlock(BlockBehaviour.Properties.copy(Blocks.MUD).strength(0.2f)));


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
