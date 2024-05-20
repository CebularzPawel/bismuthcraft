package net.cebularz.bismuthcraft.effect;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, bismuthcraft.MOD_ID);

    public static final RegistryObject<MobEffect> MUDDY = MOB_EFFECTS.register("muddy",
            ()-> new MuddyEffect(MobEffectCategory.HARMFUL,1396919).addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL));
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
