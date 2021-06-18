package com.occultism.datagen;

import com.occultism.Occultism;
import com.occultism.datagen.LootTable.LootProvider;
import com.occultism.datagen.Recipes.Item_1;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Occultism.ID)
public class DataGenEvent {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        if (event.includeClient())
        if (event.includeServer())
            event.getGenerator().addProvider(new Item_1(event.getGenerator()));
            event.getGenerator().addProvider(new LootProvider(event.getGenerator()));
    }
}
