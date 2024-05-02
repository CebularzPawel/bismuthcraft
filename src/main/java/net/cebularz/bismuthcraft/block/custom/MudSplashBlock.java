package net.cebularz.bismuthcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.Random;

public class MudSplashBlock extends MultifaceBlock { public MudSplashBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);

    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        if(pLevel.isRainingAt(pPos.above())){
            pLevel.setBlockAndUpdate(pPos,Blocks.AIR.defaultBlockState());}
            Random random = new Random();

            for (int i = 0; i < 10; i++) { // Spawn 10 particles

                    double velocityX = random.nextGaussian() * 0.02D;
                    double velocityY = random.nextGaussian() * 0.02D;
                    double velocityZ = random.nextGaussian() * 0.02D;
                    BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUD.defaultBlockState());
                    pLevel.addParticle(blockParticleData, pPos.getX() , pPos.getY() + 0.7D, pPos.getZ(), velocityX, velocityY, velocityZ);
                }
        }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.getFluidState().isEmpty();
    }
    @Override
    public MultifaceSpreader getSpreader() {
        return null;
    }
}
