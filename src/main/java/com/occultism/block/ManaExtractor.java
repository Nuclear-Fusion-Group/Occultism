package com.occultism.block;

import com.occultism.block.TileEntity.ManaExtractorTileEntity;
import com.occultism.gui.OpenGUI;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ManaExtractor extends Block {
    public ManaExtractor() {
        super(OIBlocks.defaultBuilder());
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
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (world.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUI::new);
        }
        return super.use(state, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }


}
