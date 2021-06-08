package com.occultism.fluid;

import com.occultism.Occultism;
import com.occultism.block.OIBlocks;
import com.occultism.item.OIItems;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidRegister {
    public static final ResourceLocation STILL_OIL_TEXTURE = new ResourceLocation("occultism:block/mana_fluid");
    public static final ResourceLocation FLOWING_OIL_TEXTURE = new ResourceLocation("occultism:block/mana_fluid_flow");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Occultism.ID);

    public static RegistryObject<FlowingFluid> mana_fluid = FLUIDS.register("mana_fluid", () -> new ForgeFlowingFluid.Source(FluidRegister.PROPERTIES));
    public static RegistryObject<FlowingFluid> mana_fluid_flowing = FLUIDS.register("mana_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(FluidRegister.PROPERTIES));

    public static ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(mana_fluid, mana_fluid_flowing,
            FluidAttributes.builder(STILL_OIL_TEXTURE, FLOWING_OIL_TEXTURE)//对应材质
                    .color(0xFF420372)//颜色代码 每两位16进制数代表了ARGB的一个分量，顺序分别是alpha, red, green, blue
                    .density(4000)//稠度
                    .viscosity(4000))
            .bucket(OIItems.mana_bucket)//桶
            .block(OIBlocks.manarubikcube)//水源方块
            .slopeFindDistance(1)//水流消失速度
            .explosionResistance(5F);//防爆等级
}
