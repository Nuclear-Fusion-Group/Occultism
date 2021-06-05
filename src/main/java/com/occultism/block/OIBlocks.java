package com.occultism.block;

import com.occultism.Occultism;
import com.occultism.fluid.FluidRegister;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OIBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Occultism.ID);

    public static final RegistryObject<Block> block_1 = BLOCKS.register("block_1", () -> new Block(defaultBuilder(Material.AIR)));
    /**
     * ����
     * harvestLevel(int) �����ھ�ȼ�
     * harvestTool(ToolType) �����ھ򹤾�
     * friction(float) ���ù⻬��
     * strength(float) ����Ӳ��
     */
    public static final RegistryObject<Block> orebrass = BLOCKS.register("orebrass", () -> new Block(defaultBuilder().harvestLevel(2)));
    public static final RegistryObject<Block> oremithril = BLOCKS.register("oremithril", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oreleadzinc = BLOCKS.register("oreleadzinc", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> orebauxite = BLOCKS.register("orebauxite", () -> new Block(defaultBuilder().harvestLevel(3)));
    public static final RegistryObject<Block> oretinstone = BLOCKS.register("oretinstone", () -> new Block(defaultBuilder().harvestLevel(2)));
    //����
    public static final RegistryObject<FlowingFluidBlock> manarubikcube = BLOCKS.register("mana_fluid", () ->
            new FlowingFluidBlock(FluidRegister.mana_fluid,
                    defaultBuilder(Material.WATER)
                            .noCollission()
                            .strength(100.0F)
                            .noDrops()));

    /**
     * DIG_SPEED ��ɫ
     */
    public static final RegistryObject<Block> flower = BLOCKS.register("flower", () -> new FlowerBlock(Effects.NIGHT_VISION, 4, defaultBuilder()));

    /**
     * ���鹹�췽��
     */
    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.of(material);
    }

    /**
     * ���ﹹ�췽��
     */
    public static AbstractBlock.Properties defaultBuilder() {
        return AbstractBlock.Properties.of(Material.STONE)
                .harvestTool(ToolType.PICKAXE)
                .strength(3F);
    }
}
