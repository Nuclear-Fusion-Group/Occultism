package com.occultism.plugin.jei;

import com.occultism.Occultism;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class jei implements IModPlugin {
    public void register(){

    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Occultism.ID,Occultism.ID);
    }
}
