package com.occultism.datagen.LootTable.Block;

import com.occultism.block.Blocks;

public class BlockLootTable extends BaseBlockLootTables {

    @Override
    protected void addTables() {
        skip(Blocks.manarubikcube.get().getBlock(),Blocks.crystal_stone.get());
        dropSelfWithContents(Blocks.getBlocks());
    }
}
