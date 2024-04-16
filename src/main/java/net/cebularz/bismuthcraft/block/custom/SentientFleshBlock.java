package net.cebularz.bismuthcraft.block.custom;

import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class SentientFleshBlock extends Block {
    public static IntegerProperty power = IntegerProperty.create("power", 0, 100);;

    public SentientFleshBlock(Properties pProperties ) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(power, 0));    }


    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!itemStack.isEmpty() && itemStack.getItem() == Items.BONE_MEAL) {
            // Check if power is already at maximum
            int currentPower = pState.getValue(power);
            if (currentPower < 100) {
                // Increment power level by one, capped at maximum 100
                int newPower = Math.min(currentPower + 1, 100);
                // Update the block state with the new power level
                pLevel.setBlockAndUpdate(pPos, pState.setValue(power, newPower));
                // Consume bone meal from the player's hand
                if (!pPlayer.isCreative()) {
                    itemStack.shrink(1);
                }
                // Play a sound or particle effect if desired
                // Return SUCCESS to indicate the interaction was successful
                return InteractionResult.SUCCESS;
            }
        }
        // If not using bone meal or power already at maximum, pass the interaction to the parent class
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }




    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 75;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 100;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);

        int power2 = pState.getValue(power);
        Random random = new Random();
        // Calculate the chance based on power level, with maximum 50%
        int chance = Math.min(50, 1 + (power2 / 2) * 5);
        int randomNumber = random.nextInt(100);
        if (randomNumber < chance) {
            AreaEffectCloud poisoncloud = new AreaEffectCloud(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D);
            poisoncloud.setPotion(Potions.POISON);
            if (power2>=10){
                poisoncloud.setPotion(Potions.STRONG_POISON);
            }
            poisoncloud.setDuration(7);
            poisoncloud.setRadius(1.5f);
            pLevel.addFreshEntity(poisoncloud);
        }

    }


    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 3)) {
            return;
        }


        BlockState blockstate = this.defaultBlockState();
        int power2 = pState.getValue(power);

        int chance =  1 + (power2 / 5);
        for(int i = 0; i < chance; ++i) {

            BlockPos blockpos = pPos.offset(
                    pRandom.nextInt(3) - 1, // Random offset within [-1, 0, 1]
                    pRandom.nextInt(3) - 1, // Random offset within [-1, 0, 1]
                    pRandom.nextInt(3) - 1  // Random offset within [-1, 0, 1]
            );
            if (pLevel.getBlockState(blockpos).isCollisionShapeFullBlock(pLevel,blockpos)&&!(pLevel.getBlockState(blockpos).is(ModBlocks.FLESH_BLOCK.get()))&&!(pLevel.getBlockState(blockpos).is(ModBlocks.POISON_FLESH_BLOCK.get()))) {
                int chance2 = 20-(power2 / 5);
                int randomNumber = pRandom.nextInt(100);
                if (randomNumber < chance2) {

                    int newPower = Math.min(power2 + 1, 100);
                    pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(power,newPower));

                }
                else {
                    int randomNumber2 = pRandom.nextInt(100);
                    if (randomNumber2<=2&&power2>=5){
                        boolean hasAirAbove = false;
                        for (Direction direction : Direction.values()) {
                            if (direction != Direction.UP) { // Only check the upward direction
                                continue;
                            }
                            BlockPos adjacentPos = blockpos.relative(direction);
                            if (pLevel.isEmptyBlock(adjacentPos)) {
                                hasAirAbove = true;
                                break;
                            }
                        }
                        if (hasAirAbove){
                            BlockState blockstate2 = ModBlocks.POISON_FLESH_BLOCK.get().defaultBlockState();
                            pLevel.setBlockAndUpdate(blockpos, blockstate2.setValue(power,power2));


                        }


                    }
                    else{
                    pLevel.setBlockAndUpdate(blockpos, blockstate.setValue(power,power2));}

                }

            }
        }
    }




    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(power);
    }

}
