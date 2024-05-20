package net.cebularz.bismuthcraft.block.custom;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Iterator;

@Mod.EventBusSubscriber(modid = bismuthcraft.MOD_ID)
public class MudFarmLand extends Block {

    protected static final VoxelShape SHAPE;
    public static final int MAX_MOISTURE = 7;
    public MudFarmLand(Properties pProperties) {
        super(pProperties);
    }
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing == Direction.UP && !pState.canSurvive(pLevel, pCurrentPos)) {
            pLevel.scheduleTick(pCurrentPos, this, 1);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        return !blockstate.isSolid() || blockstate.getBlock() instanceof FenceGateBlock || blockstate.getBlock() instanceof MovingPistonBlock;
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return !this.defaultBlockState().canSurvive(pContext.getLevel(), pContext.getClickedPos()) ? Blocks.MUD.defaultBlockState() : super.getStateForPlacement(pContext);
    }

    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pState.canSurvive(pLevel, pPos)) {
            turnToDirt((Entity)null, pState, pLevel, pPos);
        }

    }



    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        if (!pLevel.isClientSide && ForgeHooks.onFarmlandTrample(pLevel, pPos, Blocks.MUD.defaultBlockState(), pFallDistance, pEntity)) {
            turnToDirt(pEntity, pState, pLevel, pPos);
        }

        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }

    public static void turnToDirt(@Nullable Entity pEntity, BlockState pState, Level pLevel, BlockPos pPos) {
        BlockState blockstate = pushEntitiesUp(pState, Blocks.MUD.defaultBlockState(), pLevel, pPos);
        pLevel.setBlockAndUpdate(pPos, blockstate);
        pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pEntity, blockstate));
    }

    private static boolean shouldMaintainFarmland(BlockGetter pLevel, BlockPos pPos) {
        BlockState plant = pLevel.getBlockState(pPos.above());
        BlockState state = pLevel.getBlockState(pPos);
        return plant.getBlock() instanceof IPlantable && state.canSustainPlant(pLevel, pPos, Direction.UP, (IPlantable)plant.getBlock());
    }
    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.PlantType plantType = plantable.getPlantType(world, pos.relative(facing));
        return plantType == PlantType.CROP || plantType == PlantType.PLAINS;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        for (int i = 0; i < 15; i++) {
            double d0 = (double)pPos.getX() + pLevel.getRandom().nextDouble();
            double d1 = (double)pPos.getY() + pLevel.getRandom().nextDouble();
            double d2 = (double)pPos.getZ() + pLevel.getRandom().nextDouble();
            BlockParticleOption blockParticleData = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUD.defaultBlockState());

            pLevel.addParticle(blockParticleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        turnToDirt(pEntity, pState, pLevel, pPos);
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    private static boolean isNearWater(LevelReader pLevel, BlockPos pPos) {
        BlockState state = pLevel.getBlockState(pPos);
        Iterator var3 = BlockPos.betweenClosed(pPos.offset(-4, 0, -4), pPos.offset(4, 1, 4)).iterator();

        BlockPos blockpos;
        do {
            if (!var3.hasNext()) {
                return FarmlandWaterManager.hasBlockWaterTicket(pLevel, pPos);
            }

            blockpos = (BlockPos)var3.next();
        } while(!state.canBeHydrated(pLevel, pPos, pLevel.getFluidState(blockpos), blockpos));

        return true;
    }



    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    static {
        SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    }

    @SubscribeEvent
    public static void onCursedEarthClickedWithHoe(BlockEvent.BlockToolModificationEvent event) {
        if (event.isCanceled() || ! event.getToolAction().equals(ToolActions.HOE_TILL))
            return;

        LevelAccessor world = event.getContext().getLevel();
        BlockPos pos = event.getPos();
        BlockState farmblock = world.getBlockState(event.getContext().getClickedPos());
        BlockState newBlock = ModBlocks.MUD_FARMLAND.get().defaultBlockState();

        if (event.getToolAction() == ToolActions.HOE_TILL && (farmblock.is(Blocks.MUD))) {
            if (newBlock.canSurvive(world, pos)) {
                event.setFinalState(ModBlocks.MUD_FARMLAND.get().defaultBlockState());
            }
        }
    }
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pLevel.dimensionType().ultraWarm()) {
            pLevel.setBlock(pPos, ModBlocks.DRIED_MUD.get().defaultBlockState(), 3);
            pLevel.levelEvent(2009, pPos, 0);
            pLevel.playSound((Player)null, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + pLevel.getRandom().nextFloat() * 0.2F) * 0.7F);
        }

    }
}

