package com.occultism.TileEntity;

import com.occultism.Occultism;
import com.occultism.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeRegister {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Occultism.ID);

    public static final RegistryObject<TileEntityType<ManaExtractorTileEntity>> manaextractortileentity = TILE_ENTITY.register("mana_extractor_tileentity",()-> TileEntityType.Builder.create(ManaExtractorTileEntity::new, Blocks.mana_extractor.get()).build(null));

}
