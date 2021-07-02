package com.occultism.entity;

import com.occultism.Occultism;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Occultism.ID);

    public static final RegistryObject<EntityType<HistoricalCover>> historical_cover =
            ENTITY_TYPES.register("historical_cover",
                    () -> EntityType.Builder.create(HistoricalCover::new, EntityClassification.MISC)
                            //Åö×²Ïä´óÐ¡
                            .size(1, 1)
                            .build("historical_cover"));
}
