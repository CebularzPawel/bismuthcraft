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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class ReinforcedCobblestone extends Block {
    public ReinforcedCobblestone(Properties pProperties) {
        super(pProperties);
    }

    private void spawnParticles(LevelAccessor world, BlockPos pos,BlockParticleOption blockParticleData) {
        for (int i = 0; i < 25; i++) {
            double d0 = (double)pos.getX() + world.getRandom().nextDouble();
            double d1 = (double)pos.getY() + world.getRandom().nextDouble();
            double d2 = (double)pos.getZ() + world.getRandom().nextDouble();

            world.addParticle(blockParticleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
    private boolean addwood(Block wood,Block wood2,ItemStack heldItem, Level level, BlockPos blockPos,Player player){
        if (heldItem.getItem() == wood.asItem()) {
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
            level.playSound((Player) null, blockPos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.setBlockAndUpdate(blockPos, wood2.defaultBlockState());
            BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, wood.defaultBlockState());
            spawnParticles(level,blockPos,blockParticleData);
            return true;
        }
        return false;
    }
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos,
                                 Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack heldItem = player.getItemInHand(hand);

        if (hand == InteractionHand.MAIN_HAND) {
            if(addwood(Blocks.SPRUCE_PLANKS,ModBlocks.SPRUCE_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.OAK_PLANKS,ModBlocks.OAK_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.JUNGLE_PLANKS,ModBlocks.JUNGLE_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.MANGROVE_PLANKS,ModBlocks.MANGROVE_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.WARPED_PLANKS,ModBlocks.WARPED_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.BIRCH_PLANKS,ModBlocks.BIRCH_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);

            }
            if(addwood(Blocks.DARK_OAK_PLANKS,ModBlocks.DARK_OAK_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);

            }
            if(addwood(Blocks.ACACIA_PLANKS,ModBlocks.ACACIA_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);

            }
            if(addwood(Blocks.BAMBOO_PLANKS,ModBlocks.BAMBOO_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.CHERRY_PLANKS,ModBlocks.CHERRY_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            if(addwood(Blocks.CRIMSON_PLANKS,ModBlocks.CRIMSON_PLANKS_REINFORCED_COBBLESTONE.get(),heldItem,level,blockPos,player)){
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return InteractionResult.PASS;

}}
