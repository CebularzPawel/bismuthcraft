package net.cebularz.bismuthcraft.entity;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.entity.spellprojectiles.Bismuth_Spell;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, bismuthcraft.MOD_ID);

    public static final RegistryObject<EntityType<Bismuth_Spell>> BISMUTH_SPELL =
            ENTITY_TYPES.register("bismuth_spell", () -> EntityType.Builder.<Bismuth_Spell>of(Bismuth_Spell::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("bismuth_spell"));




    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}