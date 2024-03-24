package net.cebularz.bismuthcraft.block.custom;

import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class UpgradedReinforcedCobblestone extends Block {
    private Block WoodBlock;

    public UpgradedReinforcedCobblestone(Properties pProperties,Block WoodBlock) {
        super(pProperties);
        this.WoodBlock = WoodBlock;
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 8;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 8;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
        BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, this.WoodBlock.defaultBlockState());
        spawnParticles(pLevel,pPos,blockParticleData);
        pLevel.setBlockAndUpdate(pPos, ModBlocks.REINFORCED_COBBLESTONE.get().defaultBlockState());

    }
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos,
                                 Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldItem = player.getItemInHand(hand);


        if (heldItem.getItem() instanceof AxeItem) {

            if (hand == InteractionHand.MAIN_HAND) {
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, this.WoodBlock.defaultBlockState());
                if (!player.getAbilities().instabuild){
                    heldItem.hurtAndBreak(3, player, (entity) -> {
                        entity.broadcastBreakEvent(hand);
                    });}
                spawnParticles(level,blockPos,blockParticleData);
                level.playSound((Player) null, blockPos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(blockPos, ModBlocks.REINFORCED_COBBLESTONE.get().defaultBlockState());
                spawnParticles(level,blockPos,blockParticleData);
                if (!level.isClientSide) {
                    level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(this.WoodBlock)));
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            }}

        return InteractionResult.PASS;

    }
    private void spawnParticles(LevelAccessor world, BlockPos pos,BlockParticleOption blockParticleData) {
        for (int i = 0; i < 45; i++) {
            double d0 = (double)pos.getX() + world.getRandom().nextDouble();
            double d1 = (double)pos.getY() + world.getRandom().nextDouble();
            double d2 = (double)pos.getZ() + world.getRandom().nextDouble();

            world.addParticle(blockParticleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

}
