package net.cebularz.bismuthcraft.entity.custom;

import net.cebularz.bismuthcraft.block.ModBlocks;
import net.cebularz.bismuthcraft.entity.ModEntities;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;


public class MudBallProjectile extends ThrowableItemProjectile {
    public MudBallProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private void spawnParticles(BlockPos pos)  {
        if(!this.level().isClientSide()){
            for (int i = 0; i < 10; i++) { // Spawn 10 particles
                double x = pos.getX() + this.random.nextFloat();
                double y = pos.getY() + this.random.nextFloat();
                double z = pos.getZ() + this.random.nextFloat();
                double velocityX = this.random.nextGaussian() * 0.02D;
                double velocityY = this.random.nextGaussian() * 0.02D;
                double velocityZ = this.random.nextGaussian() * 0.02D;
                BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUD.defaultBlockState());
                this.level().addParticle(blockParticleData, x, y, z, velocityX, velocityY, velocityZ);
            }
        }
    }
    public MudBallProjectile(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.MUD_BALL_PROJECTILE_ENTITY.get(), pShooter, pLevel);
    }

    public MudBallProjectile(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntities.MUD_BALL_PROJECTILE_ENTITY.get(), pX, pY, pZ, pLevel);
    }
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int damage = 1;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)damage);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            spawnParticles(pResult.getBlockPos());
            this.discard();

            
        }

        if (!this.level().isClientSide) {
            BlockPos pos = pResult.getBlockPos();
            Direction direction = pResult.getDirection();
            BlockPos finalPos = pos.relative(direction);
            BlockState state = level().getBlockState(finalPos);

            if ((state.isAir() || state.canBeReplaced())) {
                BlockState setState;
                setState = ModBlocks.MUD_SPLASH.get().defaultBlockState().setValue(MultifaceBlock.getFaceProperty(pResult.getDirection().getOpposite()), true);
                if (state.is(ModBlocks.MUD_SPLASH.get())) {
                    setState = state.setValue(MultifaceBlock.getFaceProperty(pResult.getDirection().getOpposite()),true);
                }
                if (setState.canSurvive(level(), finalPos)) {
                    level().setBlock(finalPos, setState, 2);
                    playSound(setState.getSoundType().getPlaceSound());
                    discard();
                    return;

                }
            }

        }

        super.onHitBlock(pResult);


    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        for (int i = 0; i < 10; i++) { // Spawn 10 particles
            double velocityX = this.random.nextGaussian() * 0.02D;
            double velocityY = this.random.nextGaussian() * 0.02D;
            double velocityZ = this.random.nextGaussian() * 0.02D;
            BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUD.defaultBlockState());
            this.level().addParticle(blockParticleData, this.getX() , this.getY() + 0.7D, this.getZ(), velocityX, velocityY, velocityZ);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BISMUTH_BALL.get();
    }

    @Override
    protected float getGravity() {
        return 0.03f;
    }
}