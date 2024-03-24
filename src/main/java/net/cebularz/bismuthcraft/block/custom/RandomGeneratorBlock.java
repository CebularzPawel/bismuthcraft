package net.cebularz.bismuthcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import java.util.Random;

public class RandomGeneratorBlock extends Block {
    public static BooleanProperty LIT_1 = BooleanProperty.create("lit_1");
    public static BooleanProperty LIT_2 = BooleanProperty.create("lit_2");
    public static BooleanProperty LIT_3 = BooleanProperty.create("lit_3");
    public static BooleanProperty LIT_4 = BooleanProperty.create("lit_4");
    public static BooleanProperty LIT_5 = BooleanProperty.create("lit_5");
    public static BooleanProperty LIT_6 = BooleanProperty.create("lit_6");
    public static BooleanProperty LIT_7 = BooleanProperty.create("lit_7");
    public static BooleanProperty LIT_8 = BooleanProperty.create("lit_8");
    public static BooleanProperty LIT_9 = BooleanProperty.create("lit_9");

    public RandomGeneratorBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(LIT_1, false)
                .setValue(LIT_2, false)
                .setValue(LIT_3, false)
                .setValue(LIT_4, false)
                .setValue(LIT_5, false)
                .setValue(LIT_6, false)
                .setValue(LIT_7, false)
                .setValue(LIT_8, false)
                .setValue(LIT_9, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT_1, LIT_2, LIT_3, LIT_4, LIT_5, LIT_6, LIT_7, LIT_8, LIT_9);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pPlayer.getItemInHand(pHand).isEmpty()) {
            Random random = new Random();

            // Set random state for each lamp independently
            pLevel.setBlockAndUpdate(pPos, pState
                    .setValue(LIT_1, random.nextBoolean())
                    .setValue(LIT_2, random.nextBoolean())
                    .setValue(LIT_3, random.nextBoolean())
                    .setValue(LIT_4, random.nextBoolean())
                    .setValue(LIT_5, random.nextBoolean())
                    .setValue(LIT_6, random.nextBoolean())
                    .setValue(LIT_7, random.nextBoolean())
                    .setValue(LIT_8, random.nextBoolean())
                    .setValue(LIT_9, random.nextBoolean()));

            // Notify neighboring blocks of state change
            pLevel.gameEvent(null, GameEvent.BLOCK_CHANGE, pPos);

        }
        if(pPlayer.getItemInHand(pHand).isEmpty()){
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}