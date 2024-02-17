package net.cebularz.bismuthcraft.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public class czarytestowe {
    // Zmienna przechowująca informację o tym, czy czar został już wykonany
    private static boolean czarExecuted = false;

    // Metoda czar1 przyjmująca obiekt gracza jako argument
    public static InteractionResult czar1(Player player) {
        // Sprawdzamy, czy czar został już wykonany
        if (!czarExecuted) {
            // Wykonujemy czar
            // Tutaj możesz umieścić swój kod czaru

            // Wysyłamy wiadomość do gracza
            player.sendSystemMessage(Component.literal("Czar wykonany"));

            // Ustawiamy flagę na true, oznaczającą, że czar został wykonany
            czarExecuted = true;

            // Zwracamy wynik interakcji
            return InteractionResult.SUCCESS;
        } else {
            // Jeśli czar został już wykonany, resetujemy flagę i zwracamy FAIL
            czarExecuted = false;
            return InteractionResult.FAIL;
        }
    }
}
