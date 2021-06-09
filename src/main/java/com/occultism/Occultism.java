package com.occultism;

import com.occultism.block.OIBlocks;
import com.occultism.fluid.FluidRegister;
import com.occultism.item.OIItems;
import com.occultism.network.OINetwork;
import com.occultism.world.addOre;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Occultism.ID)
public class Occultism {
    //modid 方便导入
    public static final String ID = "occultism";
    private static final Logger LOGGER = LogManager.getLogger();

    public Occultism() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        //流体注册
        FluidRegister.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //物品注册
        OIItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //方块注册
        OIBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(OINetwork::registerMessage);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    //右击事件方块 该函数目的为桶的判断
    @SubscribeEvent
    public void onBuckte(PlayerInteractEvent.RightClickBlock event) {

        BlockRayTraceResult result = event.getHitVec();
        BlockPos blockPos = result.getBlockPos();
        Direction direction = result.getDirection();
        BlockPos blockPos1 = blockPos.relative(direction);

        World world = event.getWorld();
        PlayerEntity player = event.getPlayer();
        ItemStack itemStack = player.getItemInHand(Hand.MAIN_HAND);
        ItemStack itemStack1 = player.getItemInHand(Hand.OFF_HAND);
        FluidState fluidState;

        //玻璃破碎声音事件
        SoundEvent soundEvent = SoundEvents.GLASS_BREAK;

        fluidState = event.getWorld().getFluidState(blockPos1);
        //判断是否为瓶子
        if (itemStack.getItem() != OIItems.bucket.get() && itemStack.getItem() != Items.AIR && itemStack.getItem() != OIItems.mana_bucket.get()) {
            //判断流体堆是否相同
            if (fluidState == OIBlocks.manarubikcube.get().getFluidState(OIBlocks.manarubikcube.get().defaultBlockState())) {
                //此处为修改世界中的流体
                world.setBlockAndUpdate(blockPos1, Blocks.WATER.defaultBlockState());
            }
        } else {
            //判断是否为其他流体 是的话移除瓶子 并且播放玻璃破碎声音事件
            if (fluidState != OIBlocks.manarubikcube.get().getFluidState(OIBlocks.manarubikcube.get().defaultBlockState()) && fluidState != Fluids.EMPTY.defaultFluidState()) {
                if (itemStack.getItem() == OIItems.bucket.get()) {
                    player.setItemInHand(Hand.MAIN_HAND, Items.AIR.getDefaultInstance());
                    player.playSound(soundEvent, 1.0F, 1.0F);
                } else if (itemStack1.getItem() == OIItems.bucket.get()) {
                    player.setItemInHand(Hand.OFF_HAND, Items.AIR.getDefaultInstance());
                    player.playSound(soundEvent, 1.0F, 1.0F);
                }
            }
        }
    }


    //矿物生成
    @SubscribeEvent
    public void onBiomeLoading(final BiomeLoadingEvent biome) {
        if (biome.getCategory() == Biome.Category.NETHER || biome.getCategory() == Biome.Category.THEEND)
            return;

        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> addOre.ORE_COPPER_CONFIG);
        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> addOre.ORE_SILVER_CONFIG);
        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> addOre.ORE_LEAD_CONFIG);
        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> addOre.ORE_ALUMINIUM_CONFIG);
        biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
                .add(() -> addOre.ORE_TIN_CONFIG);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {

        }
    }
}
