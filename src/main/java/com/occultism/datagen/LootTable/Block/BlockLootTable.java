package com.occultism.datagen.LootTable.Block;

import com.occultism.block.Blocks;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip(Blocks.manarubikcube.get().getBlock());
        dropSelfWithContents(Blocks.getBlocks());
    }
}
