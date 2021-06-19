package com.occultism.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class BlockUtil {
    public static class CompactProperties {
        private final AbstractBlock.Properties properties;

        public CompactProperties(Material material, Function<BlockState, MaterialColor> mapColours) {
            this.properties = AbstractBlock.Properties.of(material, mapColours).sound(approximateSound(material));
        }

        public CompactProperties(Material material, MaterialColor mapColour) {
            this(material, state -> mapColour);
        }

        public CompactProperties stats(float hardness) {
            return stats(hardness, hardness);
        }

        public CompactProperties stats(float hardness, float resistance) {
            this.properties.strength(hardness, resistance);

            return this;
        }

        public CompactProperties sound(SoundType sound) {
            this.properties.sound(sound);

            return this;
        }

        public CompactProperties light(int light) {
            return light(state -> light);
        }

        public CompactProperties light(ToIntFunction<BlockState> light) {
            this.properties.lightLevel(light);

            return this;
        }

        public CompactProperties noClip() {
            this.properties.noCollission();
            coversScreen((state, world, pos) -> false);

            return this;
        }

        public CompactProperties coversScreen(AbstractBlock.IPositionPredicate when) {
            this.properties.isViewBlocking(when);

            return this;
        }

        public AbstractBlock.Properties get() {
            return this.properties;
        }

        private SoundType approximateSound(Material material) {
            if (material == Material.WOOD) {
                return SoundType.WOOD;
            } else if (material == Material.GLASS) {
                return SoundType.GLASS;
            } else if (material == Material.DIRT) {
                return SoundType.GRAVEL;
            } else if (material == Material.PLANT || material == Material.GRASS) {
                return SoundType.GRASS;
            } else if (material == Material.TOP_SNOW || material == Material.SNOW) {
                return SoundType.SNOW;
            } else if (material == Material.SAND) {
                return SoundType.SAND;
            } else if (material == Material.WOOL) {
                return SoundType.WOOL;
            } else {
                return SoundType.STONE;
            }
        }
    }
}
