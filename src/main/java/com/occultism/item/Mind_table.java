package com.occultism.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Mind_table extends Item {
    public Mind_table() {
        super(OIItems.defaultBuilder());
    }

    @Override
    public void onUseTick(World p_219972_1_, LivingEntity p_219972_2_, ItemStack p_219972_3_, int p_219972_4_) {
        super.onUseTick(p_219972_1_, p_219972_2_, p_219972_3_, p_219972_4_);
    }
}
