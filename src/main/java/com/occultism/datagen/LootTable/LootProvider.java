package com.occultism.datagen.LootTable;

import com.occultism.Occultism;
import com.occultism.datagen.LootTable.Block.BaseBlockLootTables;
import com.occultism.datagen.LootTable.Block.BlockLootTable;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class LootProvider extends BaseLootProvider {
    public LootProvider(DataGenerator gen) {
        super(gen, Occultism.ID);
    }

    @Override
    protected BaseBlockLootTables getBlockLootTable() {
        return new BlockLootTable();
    }

    //���ε����õ���Ϣ
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {

    }
}
