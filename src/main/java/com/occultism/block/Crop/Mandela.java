package com.occultism.block.Crop;

import com.occultism.item.OIItems;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Mandela extends CropsBlock {
    public Mandela() {
        super(Properties.of(Material.PLANT).sound(SoundType.CROP));
    }

    @Nonnull
    @Override
    protected IItemProvider getBaseSeedId() {
        return OIItems.mandela_seen.get();
    }
}
