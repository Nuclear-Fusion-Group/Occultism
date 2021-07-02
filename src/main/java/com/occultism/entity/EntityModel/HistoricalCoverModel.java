package com.occultism.entity.EntityModel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.occultism.entity.HistoricalCover;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

/**
 * �ο���boson1.16.5�̳�
 * https://boson.v2mcdev.com/entity/scratchentity.html
 * <p>
 * ǰ���ǿռ�λ�ã��������Ǵ�С
 * addBox(x,y,z,x,y,z,͸����,�Ƿ���)
 */
public class HistoricalCoverModel extends EntityModel<HistoricalCover> {
    private final ModelRenderer body;

    public HistoricalCoverModel() {
        textureHeight = 64;
        textureWidth = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(8.0F, 24.0F, -8.0F);
        //UVλ��
        body.setTextureOffset(16, 16)
                .addBox(-16.0F, -16.0F, 0.0F, 16.0F, 10.0F, 16.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(HistoricalCover historicalCover, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder builderIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        body.render(matrixStack, builderIn, packedLightIn, packedOverlayIn);
    }
}
