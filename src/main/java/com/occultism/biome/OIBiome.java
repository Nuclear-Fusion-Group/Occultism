package com.occultism.biome;

import com.occultism.block.OIBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

//暂时还用不了
/*
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OIBiome {
    public static ConfiguredFeature<?,?> ORE_COPPER_CONFIG;

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event){
        ORE_COPPER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,"ore_copper",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.orebrass.get().defaultBlockState(),90//最多数量
                        )
                ).range(64).squared().count(100)
        );
    }
}

 */
