package com.occultism.datagen.LootTable;

import com.occultism.Occultism;
import com.occultism.datagen.LootTable.Block.BaseBlockLootTables;
import com.occultism.datagen.LootTable.Block.BlockLootTable;
import net.minecraft.data.DataGenerator;

public class LootProvider extends BaseLootProvider {
    public LootProvider(DataGenerator gen) {
        super(gen, Occultism.ID);
    }

    @Override
    protected BaseBlockLootTables getBlockLootTable() {
        return new BlockLootTable();
    }

}
