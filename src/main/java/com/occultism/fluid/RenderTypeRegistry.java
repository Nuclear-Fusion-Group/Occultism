package com.occultism.fluid;

import com.occultism.Occultism;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * °ëÍ¸Ã÷ÒºÌåÉèÖÃ
 */

@Mod.EventBusSubscriber(modid = Occultism.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(
                () -> {
                    RenderTypeLookup.setRenderLayer(FluidRegister.mana_fluid.get(), RenderType.getGlintTranslucent());
                    RenderTypeLookup.setRenderLayer(FluidRegister.mana_fluid_flowing.get(), RenderType.getGlintTranslucent());
                }
        );
    }
}
