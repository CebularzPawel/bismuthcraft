package net.cebularz.bismuthcraft.block.custom;

import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.crafting.conditions.TrueCondition;

import javax.annotation.Nullable;

public class MudLampBlock extends Block {
    public static BooleanProperty LIT = BooleanProperty.create("lit");



    public MudLampBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));

    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos,
                                 Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldItem = player.getItemInHand(hand);
        boolean isLit = state.getValue(LIT);

        if (isLit) {
            if (heldItem.isEmpty()) {

                if (hand == InteractionHand.MAIN_HAND && player.getAbilities().mayBuild) {
                    spawnParticles(level,blockPos);
                    level.playSound((Player) null, blockPos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.setBlock(blockPos, state.cycle(LIT), 3);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            } else {

                return InteractionResult.PASS;
            }
        } else {
            if (heldItem.getItem() == Items.FLINT_AND_STEEL) {

                if (hand == InteractionHand.MAIN_HAND) {
                    if (!player.getAbilities().instabuild){
                        heldItem.hurtAndBreak(1, player, (entity) -> {
                            entity.broadcastBreakEvent(hand);
                    });}
                    level.playSound((Player) null, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    level.setBlock(blockPos, state.cycle(LIT), 3);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            } else {
                if (heldItem.getItem() == Items.FIRE_CHARGE) {
                    if (hand == InteractionHand.MAIN_HAND) {
                        if (!player.getAbilities().instabuild){
                            heldItem.shrink(1);}
                        level.playSound((Player) null, blockPos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                        level.setBlock(blockPos, state.cycle(LIT), 3);
                        level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }

                }else{

                    return InteractionResult.PASS;}
            }
        }

        return InteractionResult.PASS;
    }




    private void spawnParticles(LevelAccessor world, BlockPos pos) {
        for (int i = 0; i < 10; i++) {
            double d0 = (double)pos.getX() + world.getRandom().nextDouble();
            double d1 = (double)pos.getY() + world.getRandom().nextDouble();
            double d2 = (double)pos.getZ() + world.getRandom().nextDouble();
            world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }

}
