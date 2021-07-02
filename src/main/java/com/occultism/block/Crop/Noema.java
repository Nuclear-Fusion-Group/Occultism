package com.occultism.block.Crop;

import com.occultism.item.Items;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Noema extends CropsBlock {
    public Noema() {
        super(Properties.create(Material.PLANTS).sound(SoundType.PLANT));
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return Items.noema_seen.get();
    }
}
