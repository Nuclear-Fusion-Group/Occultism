package com.occultism.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.occultism.Occultism;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.SlotItemHandler;

/**
 * ��ע�⣬��GUI�У�X���Ǵ����Ͻ����£�Y���Ǵ����Ͻ����ҡ�
 * ��Ⱦ����blit(MatrixStack matrixStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight)
 */
public class ManaExtractorGUI extends Screen {
    SlotItemHandler itemHandler;
    ResourceLocation GUI = new ResourceLocation(Occultism.ID,"textures/gui/mana_extractor_gui.png");

    protected ManaExtractorGUI(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init(){
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(GUI);
        this.blit(matrixStack,this.width/2-150,10,0,0,300,200,1,1);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
