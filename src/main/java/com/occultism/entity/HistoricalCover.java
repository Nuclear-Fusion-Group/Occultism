package com.occultism.entity;

import com.occultism.entity.api.BaseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HistoricalCover extends BaseEntity {
    private Logger logger = LogManager.getLogger();
    private static final DataParameter<Integer> COUNTER = EntityDataManager.createKey(HistoricalCover.class, DataSerializers.VARINT);

    protected HistoricalCover(EntityType<? extends MonsterEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

}
