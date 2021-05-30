package com.occultism.block;

import com.occultism.Occultism;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OIBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Occultism.ID);

    public static final RegistryObject<Block> block_1 = BLOCKS.register("block_1", () -> new Block(defaultBuilder(Material.AIR)));

    /**
     * 方块默认构造
     */
    public static AbstractBlock.Properties defaultBuilder(Material material) {
        return AbstractBlock.Properties.of(material);
    }
}
