package net.cebularz.bismuthcraft.mixin;


import net.cebularz.bismuthcraft.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CropBlock.class)
public class MudMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return (pState.is(Blocks.FARMLAND)||pState.is(ModBlocks.MUD_FARMLAND.get()));

    }
}
