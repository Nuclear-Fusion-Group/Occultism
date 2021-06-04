package com.occultism.world;

import com.occultism.Occultism;
import com.occultism.block.OIBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * range(int)疑似生成后渲染距离
 * count(int)不知道干嘛的 谁知道就在这里标一下
 */
@Mod.EventBusSubscriber(modid = Occultism.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class addOre {
    public static ConfiguredFeature<?, ?> ORE_COPPER_CONFIG;
    public static ConfiguredFeature<?, ?> ORE_SILVER_CONFIG;
    public static ConfiguredFeature<?, ?> ORE_LEAD_CONFIG;
    public static ConfiguredFeature<?, ?> ORE_ALUMINIUM_CONFIG;
    public static ConfiguredFeature<?, ?> ORE_TIN_CONFIG;

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        ORE_COPPER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_copper",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.orebrass.get().defaultBlockState(), 8//最多数量
                        )
                ).range(70).squared().count(20)
        );

        ORE_SILVER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_silver",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.oremithril.get().defaultBlockState(), 8
                        )
                ).range(8).squared().count(20)
        );

        ORE_LEAD_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_lead",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.oreleadzinc.get().defaultBlockState(), 8
                        )
                ).range(16).squared().count(20)
        );

        ORE_ALUMINIUM_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_aluminium",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.orebauxite.get().defaultBlockState(), 8
                        )
                ).range(32).squared().count(20)
        );

        ORE_TIN_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_tin",
                Feature.ORE.configured(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                OIBlocks.oretinstone.get().defaultBlockState(), 8
                        )
                ).range(64).squared().count(20)
        );
    }
}

