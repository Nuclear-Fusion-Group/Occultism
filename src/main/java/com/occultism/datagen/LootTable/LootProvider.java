package com.occultism.datagen.LootTable;

import com.occultism.Occultism;
import net.minecraft.data.DataGenerator;

import javax.annotation.Nonnull;

public class LootProvider extends BaseLootProvider {
    public LootProvider(DataGenerator gen) {
        super(gen, Occultism.ID);
    }

    @Override
    protected BaseBlockLootTables getBlockLootTable() {
        return new BlockLootTable();
    }

    @Nonnull
    @Override
    protected BaseEntityLootTables getEntityLootTable() {
        return new EntityLootTables();
    }
}
