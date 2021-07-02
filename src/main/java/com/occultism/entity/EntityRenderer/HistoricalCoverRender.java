package com.occultism.entity.EntityRenderer;

import com.occultism.Occultism;
import com.occultism.entity.EntityModel.HistoricalCoverModel;
import com.occultism.entity.HistoricalCover;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class HistoricalCoverRender extends MobRenderer<HistoricalCover,HistoricalCoverModel> {

    //第三个参数是影子大小
    public HistoricalCoverRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HistoricalCoverModel(), 1F);
    }

    public HistoricalCoverRender(EntityRendererManager renderManagerIn, HistoricalCoverModel entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(HistoricalCover p_110775_1_) {
        return new ResourceLocation(Occultism.ID,"textures/entity/historical_cover.png");
    }

}
