package com.occultism.gui;

import com.occultism.Occultism;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGUI {
    public OpenGUI() {
        Minecraft.getInstance().setScreen(new ManaExtractorGUI(new TranslationTextComponent(Occultism.ID, ".test")));
    }
}

