package com.occultism.block.Flower;

import com.occultism.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;

/**
 * 该类参考了AOA3
 */
public class Flowers extends FlowerBlock {
    public final DyeColor color;

    public Flowers(DyeColor color, Properties builder) {
        super(effectForFlower(color), 4, builder);
        this.color = color;
    }

    public Flowers(DyeColor color){
        this(color, Blocks.defaultBuilder(Material.PLANTS).sound(SoundType.PLANT).notSolid());
    }


    private static Effect effectForFlower(DyeColor color) {
        switch (color) {
            case WHITE:
                return Effects.SPEED;
            case ORANGE:
                return Effects.FIRE_RESISTANCE;
            case MAGENTA:
                return Effects.MINING_FATIGUE;
            case LIGHT_BLUE:
                return Effects.JUMP_BOOST;
            case YELLOW:
                return Effects.ABSORPTION;
            case LIME:
                return Effects.POISON;
            case PINK:
                return Effects.REGENERATION;
            case GRAY:
                return Effects.RESISTANCE;
            case LIGHT_GRAY:
                return Effects.WEAKNESS;
            case CYAN:
                return Effects.WATER_BREATHING;
            case PURPLE:
                return Effects.NAUSEA;
            case BLUE:
                return Effects.NIGHT_VISION;
            case BROWN:
                return Effects.WITHER;
            case GREEN:
                return Effects.HUNGER;
            case RED:
                return Effects.STRENGTH;
            case BLACK:
                return Effects.BLINDNESS;
        }
        return Effects.REGENERATION;
    }
}
