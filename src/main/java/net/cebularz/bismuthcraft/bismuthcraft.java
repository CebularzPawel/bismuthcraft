package net.cebularz.bismuthcraft;

import com.mojang.logging.LogUtils;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.cebularz.bismuthcraft.effect.ModEffects;
import net.cebularz.bismuthcraft.entity.ModEntities;
import net.cebularz.bismuthcraft.entity.custom.MudBallProjectile;
import net.cebularz.bismuthcraft.item.ModCreativeModTabs;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.Util;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(bismuthcraft.MOD_ID)
public class bismuthcraft
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "bismuthcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public bismuthcraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register((modEventBus));
        ModEffects.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

        DispenserBlock.registerBehavior(ModItems.MUD_BALL.get(), new AbstractProjectileDispenseBehavior() {
            protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
                return (Projectile) Util.make(new MudBallProjectile(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
                    p_123474_.setItem(p_123478_);
                });
            }
        });

        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey()== CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.BISMUTH_BALL);
        }
        if(event.getTabKey()== CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModBlocks.PINEAPPLE);
            event.accept(ModItems.PINEAPPLE_SLICE);
        }
        if(event.getTabKey()== CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.DEEPSLATE_BISMUTH_ORE);
            event.accept(ModBlocks.BISMUTH_BLOCK);
            event.accept(ModBlocks.SPRUCE_PANELS);
        }

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(EntityRenderersEvent.RegisterRenderers event) {
            EntityRenderers.register(ModEntities.BISMUTH_SPELL.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.MUD_BALL_PROJECTILE_ENTITY.get(), ThrownItemRenderer::new);


            }
        }
}
