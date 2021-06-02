package com.occultism.biome;

import com.occultism.Occultism;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Occultism.ID)
public class BiomeRegister {

    //¿óÎïÉú³É
    @SubscribeEvent
    public void onBiomeLoading(final BiomeLoadingEvent biome){
        if (biome.getCategory()== Biome.Category.NETHER||biome.getCategory()==Biome.Category.THEEND)
            return;

        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(()-> addOre.ORE_COPPER_CONFIG);
    }
}