package net.cebularz.bismuthcraft.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.cebularz.bismuthcraft.spells.czarytestowe;
import net.minecraft.world.level.Level;

public class BismuthBall extends Item {
    public BismuthBall(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player player, InteractionHand pHand) {;
        InteractionResultHolder wynik = czarytestowe.spell1(player,pLevel,pHand);
        return wynik;

        }

}
