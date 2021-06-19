package com.occultism.block;

import com.occultism.TileEntity.ManaExtractorTileEntity;
import com.occultism.api.IHasTileEntity;
import com.occultism.gui.OpenGUI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;

public class ManaExtractor extends Block implements IHasTileEntity {
    public ManaExtractor() {
        super(OIBlocks.defaultBuilder());
    }

    @Override
    public TileEntityType<?> getTileType() {
        return new ManaExtractorTileEntity().getType();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ManaExtractorTileEntity();
    }

    //ÓÒ»÷·½¿éÊÂ¼þ
    @Nonnull
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUI::new);
        }
        return super.use(state, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }


}
