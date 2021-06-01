package com.occultism.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
//ÓÃ²»ÁË
/*
public class BiomeRegister {
    @SubscribeEvent
    public void onBiomeLoading(final BiomeLoadingEvent biome){
        if (biome.getCategory()== Biome.Category.NETHER||biome.getCategory()==Biome.Category.THEEND)
            return;

        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(()->OIBiome.ORE_COPPER_CONFIG);
    }
}


 */