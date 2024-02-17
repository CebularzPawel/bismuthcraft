package net.cebularz.bismuthcraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.cebularz.bismuthcraft.spells.czarytestowe;
public class BismuthBall extends Item {
    public BismuthBall(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Player player = pContext.getPlayer();
        // Wywołujemy funkcję czar1() z klasy czarytestowe
        InteractionResult result = czarytestowe.czar1(player);
        // Możesz tutaj wykonywać inne operacje na podstawie wyniku działania czaru
        return result;
        }

}
