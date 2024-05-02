package net.cebularz.bismuthcraft.item;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, bismuthcraft.MOD_ID);
    public static final RegistryObject<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BISMUTH_BALL = ITEMS.register("bismuth_ball",
            () -> new BismuthBall(new Item.Properties()));
    public static final RegistryObject<Item> PINEAPPLE_SLICE = ITEMS.register("pineapple_slice",
            () -> new Item(new Item.Properties().food(ModFoods.PINEAPPLE)));
    public static final RegistryObject<MudBall> MUD_BALL = ITEMS.register("mud_ball",
            () -> new MudBall(new Item.Properties()));
    public static final RegistryObject<Item> PACKED_MUD_BALL = ITEMS.register("packed_mud_ball",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SINKING_MUD_BUCKET = ITEMS.register("sinking_mud_bucket",
            () -> new SolidBucketItem(ModBlocks.SINKING_MUD.get(),SoundEvents.BUCKET_EMPTY_POWDER_SNOW, (new Item.Properties()).stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
