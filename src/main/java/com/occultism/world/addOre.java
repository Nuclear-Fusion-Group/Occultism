package com.occultism.world;

import com.occultism.Occultism;
import com.occultism.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * range(int) 0到int都生成
 * count(int)不知道干嘛的 谁知道就在这里标一下 疑似权重值
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
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER,
                                Blocks.orebrass.get().getDefaultState(), 8//最多数量
                        )
                ).range(70).square().count(20)
        );

        ORE_SILVER_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_silver",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER,
                                Blocks.oremithril.get().getDefaultState(), 8
                        )
                ).range(8).square().count(20)
        );

        ORE_LEAD_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_lead",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER,
                                Blocks.oreleadzinc.get().getDefaultState(), 8
                        )
                ).range(16).square().count(20)
        );

        ORE_ALUMINIUM_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_aluminium",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER,
                                Blocks.orebauxite.get().getDefaultState(), 8
                        )
                ).range(32).square().count(20)
        );

        ORE_TIN_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_tin",
                Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_NETHER,
                                Blocks.oretinstone.get().getDefaultState(), 8
                        )
                ).range(64).square().count(20)
        );
    }
}

