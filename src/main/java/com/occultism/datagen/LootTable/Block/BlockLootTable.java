package com.occultism.datagen.LootTable.Block;

import com.occultism.block.OIBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BlockLootTable extends BaseBlockLootTables {

    Field[] key = OIBlocks.class.getFields();
    Object fieldVal;
    List<Block> blocks = new ArrayList<>();
    Block block;

    private List<Block> getBlocks() {
        for (Field val : key) {
            //�������������;�������ѭ��
            if (val.getType() != RegistryObject.class)
                continue;
            if (val.getName().equals(OIBlocks.manarubikcube.toString()))
                continue;
            try {
                //��ȡ�ֶε�ֵ
                fieldVal = val.get(val.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            block = ((RegistryObject<Block>) fieldVal).get();
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    protected void addTables() {
        skip(OIBlocks.manarubikcube.get().getBlock());
        dropSelfWithContents(getBlocks());
    }
}
