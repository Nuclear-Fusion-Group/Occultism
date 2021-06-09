package com.occultism.block.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public class ManaExtractorTileEntity extends TileEntity implements ITickableTileEntity {
    private int mana = 0;
    private CompoundNBT mana_nbt;

    public ManaExtractorTileEntity() {
        super(TileEntityTypeRegister.manaextractortileentity.get());
    }

    //加载数据
    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        mana = nbt.getInt("mana");
        mana_nbt = nbt.copy();
    }

    //WARN 该保存有bug
    //保存数据
    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt.putInt("mana", mana);
        mana_nbt = nbt.copy();
        if (!super.level.isEmptyBlock(super.getBlockPos()))
            return mana_nbt;
        super.level.getChunkAt(super.getBlockPos()).markUnsaved();
        return mana_nbt;
    }

    @Override
    public void tick() {
        if (mana < 1000)
            mana++;
        else
            mana = 1000;
    }

    @Nonnull
    @Override
    public CompoundNBT getUpdateTag() {
        return mana_nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        mana_nbt = tag.copy();
    }

}
