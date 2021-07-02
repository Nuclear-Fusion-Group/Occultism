package com.occultism.entity;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributesSetEvent {
    @SubscribeEvent
    public static void setupAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityTypeRegistry.historical_cover.get(), MobEntity.registerAttributes()
                .createMutableAttribute(Attributes.MAX_HEALTH, 10.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE,3.0D)
                .create()
        );
    }
}
