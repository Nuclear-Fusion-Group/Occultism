package com.occultism.block;

import com.occultism.Occultism;
import com.occultism.block.Crop.Mandela;
import com.occultism.block.Crop.Noema;
import com.occultism.block.Crop.StarFantasyFruit;
import com.occultism.block.Flower.Flowers;
import com.occultism.fluid.FluidRegister;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Occultism.ID);

    public static final RegistryObject<Block> block_1 = BLOCKS.register("block_1", () -> new Block(defaultBuilder()));
    public static final RegistryObject<Block> mana_extractor = BLOCKS.register("mana_extractor", ManaExtractor::new);
    /**
     * 矿物
     * harvestLevel(int) 设置挖掘等级
     * harvestTool(ToolType) 设置挖掘工具
     * friction(float) 设置光滑度
     * hardnessAndResistance(float) 设置硬度
     */
    public static final RegistryObject<Block> orebrass = BLOCKS.register("orebrass", () -> new Block(defaultBuilder().harvestLevel(2)));
    public static final RegistryObject<Block> oremithril = BLOCKS.register("oremithril", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oreleadzinc = BLOCKS.register("oreleadzinc", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> orebauxite = BLOCKS.register("orebauxite", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oretinstone = BLOCKS.register("oretinstone", () -> new Block(defaultBuilder().harvestLevel(2)));
    public static final RegistryObject<Block> crystal_stone = BLOCKS.register("crystal_stone", () -> new Block(defaultBuilder().harvestLevel(3)));
    //流体
    public static final RegistryObject<FlowingFluidBlock> manarubikcube = BLOCKS.register("mana_fluid", () ->
            new FlowingFluidBlock(FluidRegister.mana_fluid,
                    defaultBuilder(Material.WATER)
                            .notSolid()
                            .hardnessAndResistance(100.0F)
                            .noDrops()));

    //花
    public static final RegistryObject<Block> moon_flower = BLOCKS.register("moon_flower", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> chamomile = BLOCKS.register("chamomile", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> marigold = BLOCKS.register("marigold", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> manjushahua = BLOCKS.register("manjushahua", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> mandela_grass = BLOCKS.register("mandela_grass", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> cicuta = BLOCKS.register("cicuta", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> datura = BLOCKS.register("datura", () -> new Flowers(DyeColor.BLUE));
    public static final RegistryObject<Block> sage = BLOCKS.register("sage", () -> new Flowers(DyeColor.BLUE));
    //作物
    public static final RegistryObject<CropsBlock> mandela = BLOCKS.register("mandela", Mandela::new);
    public static final RegistryObject<CropsBlock> noema = BLOCKS.register("noema", Noema::new);
    public static final RegistryObject<CropsBlock> star_fantasy_fruit = BLOCKS.register("star_fantasy_fruit", StarFantasyFruit::new);

    public static ArrayList<RegistryObject<Block>> getBlocks() {
        return new ArrayList<>(BLOCKS.getEntries());
    }

    /**
     * 方块构造方法
     */
    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.create(material);
    }

    /**
     * 矿物构造方法
     */
    public static AbstractBlock.Properties defaultBuilder() {
        return AbstractBlock.Properties.create(Material.STRUCTURE_VOID)
                .harvestTool(ToolType.PICKAXE)
                .hardnessAndResistance(3F);
    }
}
