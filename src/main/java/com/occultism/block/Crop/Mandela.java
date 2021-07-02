package com.occultism.block.Crop;

import com.occultism.item.Items;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IItemProvider;

import javax.annotation.Nonnull;

public class Mandela extends CropsBlock {
    public Mandela() {
        super(Properties.create(Material.PLANTS).sound(SoundType.PLANT));
    }

    @Nonnull
    @Override
    protected IItemProvider getSeedsItem() {
        return Items.mandela_seen.get();
    }
}
