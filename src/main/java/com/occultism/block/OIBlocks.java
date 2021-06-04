package com.occultism.block;

import com.occultism.Occultism;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OIBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Occultism.ID);

    public static final RegistryObject<Block> block_1 = BLOCKS.register("block_1", () -> new Block(defaultBuilder(Material.AIR)));
    /**矿物
     * harvestLevel(int) 设置挖掘等级
     * harvestTool(ToolType) 设置挖掘工具
     */
    public static final RegistryObject<Block> orebrass = BLOCKS.register("orebrass", () -> new Block(defaultBuilder().harvestLevel(2)));
    public static final RegistryObject<Block> oremithril = BLOCKS.register("oremithril", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oreleadzinc = BLOCKS.register("oreleadzinc", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> orebauxite = BLOCKS.register("orebauxite", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oretinstone = BLOCKS.register("oretinstone", () -> new Block(defaultBuilder().harvestLevel(2)));


    /**
     * 方块构造方法
     */
    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.of(material);
    }

    /**
     * 矿物构造方法
     */
    public static AbstractBlock.Properties defaultBuilder() {
        return AbstractBlock.Properties.of(Material.STONE)
                .harvestTool(ToolType.PICKAXE);
    }
}
