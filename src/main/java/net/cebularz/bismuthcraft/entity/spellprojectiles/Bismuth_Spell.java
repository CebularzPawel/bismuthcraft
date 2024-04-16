package net.cebularz.bismuthcraft.entity.spellprojectiles;

import net.cebularz.bismuthcraft.entity.ModEntities;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;


public class Bismuth_Spell extends ThrowableItemProjectile {
    public Bismuth_Spell(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    public Bismuth_Spell(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.BISMUTH_SPELL.get(), pShooter, pLevel);
    }

    public Bismuth_Spell(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntities.BISMUTH_SPELL.get(), pX, pY, pZ, pLevel);
    }
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int damage = 3;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)damage);
    }
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            AreaEffectCloud chmura = new AreaEffectCloud(this.level(), this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D);
            chmura.setDuration(5);
            chmura.setRadius(2);
            chmura.setParticle(ParticleTypes.FLAME);
            this.level().addFreshEntity(chmura);

            // Damage entities within the radius of the cloud
            List<Entity> entities = this.level().getEntitiesOfClass(Entity.class, chmura.getBoundingBox().inflate(1));
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    livingEntity.setSecondsOnFire(3);
                    livingEntity.hurt(this.damageSources().magic(), 2); // Adjust the damage amount as needed
                    // You can add additional effects or modifications here if needed
                }
            }

            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BISMUTH_BALL.get();
    }
}