package com.occultism.gui.hub;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.occultism.Occultism;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class HUB extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private MatrixStack matrixStack;
    private final ResourceLocation HUD = new ResourceLocation(Occultism.ID, "textures/gui/hud.png");

    public HUB(MatrixStack matrixStack) {
        this.width = Minecraft.getInstance().getMainRenderTarget().width;
        this.height = Minecraft.getInstance().getMainRenderTarget().height;
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }

    public void setMatrixStack(MatrixStack stack) {
        this.matrixStack = stack;
    }

    public void render() {
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(HUD);
        blit(matrixStack, 4, 4, 0, 0, 16, 64, 16, 64);
    }
}
