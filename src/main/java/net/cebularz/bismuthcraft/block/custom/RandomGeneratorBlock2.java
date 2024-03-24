package net.cebularz.bismuthcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGeneratorBlock2 extends DirectionalBlock {

    // Define BooleanProperties
    public static final BooleanProperty LIT_1 = BooleanProperty.create("lit_1");
    public static final BooleanProperty LIT_2 = BooleanProperty.create("lit_2");
    public static final BooleanProperty LIT_3 = BooleanProperty.create("lit_3");
    public static final BooleanProperty LIT_4 = BooleanProperty.create("lit_4");
    public static final BooleanProperty LIT_5 = BooleanProperty.create("lit_5");
    public static final BooleanProperty LIT_6 = BooleanProperty.create("lit_6");
    public static final BooleanProperty LIT_7 = BooleanProperty.create("lit_7");
    public static final BooleanProperty LIT_8 = BooleanProperty.create("lit_8");
    public static final BooleanProperty LIT_9 = BooleanProperty.create("lit_9");

    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    private final List<BooleanProperty> litProperties;

    public RandomGeneratorBlock2(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.EAST)
                .setValue(LIT_1, false)
                .setValue(LIT_2, false)
                .setValue(LIT_3, false)
                .setValue(LIT_4, false)
                .setValue(LIT_5, false)
                .setValue(LIT_6, false)
                .setValue(LIT_7, false)
                .setValue(LIT_8, false)
                .setValue(LIT_9, false));


        this.litProperties = new ArrayList<>();
        litProperties.add(LIT_1);
        litProperties.add(LIT_2);
        litProperties.add(LIT_3);
        litProperties.add(LIT_4);
        litProperties.add(LIT_5);
        litProperties.add(LIT_6);
        litProperties.add(LIT_7);
        litProperties.add(LIT_8);
        litProperties.add(LIT_9);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT_1, LIT_2, LIT_3, LIT_4, LIT_5, LIT_6, LIT_7, LIT_8, LIT_9);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pPlayer.getItemInHand(pHand).isEmpty() && pHand == InteractionHand.MAIN_HAND) {
            int litCount = 0;
            for (BooleanProperty litProperty : litProperties) {
                if (pState.getValue(litProperty)) {
                    litCount++;
                }
            }
            if (litCount!=0){
            // Set random state for each lamp independently
            pLevel.setBlockAndUpdate(pPos, pState
                    .setValue(LIT_1, false)
                    .setValue(LIT_2, false)
                    .setValue(LIT_3, false)
                    .setValue(LIT_4, false)
                    .setValue(LIT_5, false)
                    .setValue(LIT_6, false)
                    .setValue(LIT_7, false)
                    .setValue(LIT_8, false)
                    .setValue(LIT_9, false));

            // Notify neighboring blocks of state change
            pLevel.gameEvent(null, GameEvent.BLOCK_CHANGE, pPos);

            return InteractionResult.SUCCESS;}
        }
        return InteractionResult.PASS;
    }


    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClientSide && !isFacingDirection(fromPos, pos, state)) {
            if (world.hasNeighborSignal(pos)) {
                Random random = new Random();

                // Set random state for each lamp independently
                world.setBlockAndUpdate(pos, state
                        .setValue(LIT_1, random.nextBoolean())
                        .setValue(LIT_2, random.nextBoolean())
                        .setValue(LIT_3, random.nextBoolean())
                        .setValue(LIT_4, random.nextBoolean())
                        .setValue(LIT_5, random.nextBoolean())
                        .setValue(LIT_6, random.nextBoolean())
                        .setValue(LIT_7, random.nextBoolean())
                        .setValue(LIT_8, random.nextBoolean())
                        .setValue(LIT_9, random.nextBoolean()));

                world.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
            }
        }
    }


    private boolean isFacingDirection(BlockPos fromPos, BlockPos toPos, BlockState state) {
        Direction facing = state.getValue(FACING);
        Direction direction = Direction.getNearest(fromPos.getX() - toPos.getX(), fromPos.getY() - toPos.getY(), fromPos.getZ() - toPos.getZ());

        return direction == facing;
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        Direction facing = blockState.getValue(FACING);

        // Check if the provided side matches the facing direction
        if (side == facing.getOpposite()) {
            int litCount = 0;
            for (BooleanProperty litProperty : litProperties) {
                if (blockState.getValue(litProperty)) {
                    litCount++;
                }
            }

            // Return redstone signal strength equal to the number of lit properties
            return litCount;
        }
        return 0; // No redstone signal output if the side does not match the facing direction
    }

}
