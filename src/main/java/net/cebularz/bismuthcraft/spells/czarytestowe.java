package net.cebularz.bismuthcraft.spells;

import net.cebularz.bismuthcraft.entity.spellprojectiles.Bismuth_Spell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.GravityProcessor;

public class czarytestowe {
    private static boolean spellexecuted = false;

    public static InteractionResultHolder spell1(Player player, Level pLevel, InteractionHand pHand) {

        ItemStack Item = player.getItemInHand(pHand);
        for (int i = 0; i < 1; i++) {
            Bismuth_Spell Spell = new Bismuth_Spell(pLevel, player);
            Spell.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.2F, 12.0F);
            pLevel.addFreshEntity(Spell);
        }

        if(!pLevel.isClientSide()) {
            player.sendSystemMessage(Component.literal("Czar wykonany"));}
        return InteractionResultHolder.sidedSuccess(Item, pLevel.isClientSide());


    }
}
