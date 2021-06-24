package com.occultism.world;

import com.occultism.block.OIBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AddFlower {
    public static ConfiguredFeature<?, ?> FLOWER;

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        FLOWER = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "flower",
                Feature.RANDOM_PATCH.configured(
                        (new BlockClusterFeatureConfig.Builder(
                                new WeightedBlockStateProvider().add(OIBlocks.moon_flower.get().defaultBlockState(), 2),
                                new SimpleBlockPlacer())
                        ).tries(2).build()
                ).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(new NoPlacementConfig()))
        );
    }
}
