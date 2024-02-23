package net.cebularz.bismuthcraft.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.Snowball;

public class czarytestowe {
    private static boolean spellexecuted = false;

    public static InteractionResultHolder spell1(Player player, Level pLevel, InteractionHand pHand) {

        ItemStack $$3 = player.getItemInHand(pHand);
        Snowball $$4 = new Snowball(pLevel, player);
        $$4.setItem($$3);
        $$4.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        pLevel.addFreshEntity($$4);
        player.sendSystemMessage(Component.literal("Czar wykonany"));
        return InteractionResultHolder.sidedSuccess($$3, pLevel.isClientSide());


    }
}
