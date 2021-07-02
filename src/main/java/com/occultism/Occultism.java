package com.occultism;

import com.occultism.TileEntity.TileEntityTypeRegister;
import com.occultism.block.Blocks;
import com.occultism.entity.EntityTypeRegistry;
import com.occultism.fluid.FluidRegister;
import com.occultism.item.Items;
import com.occultism.network.Network;
import com.occultism.world.AddFlower;
import com.occultism.world.addOre;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
    //modid ���㵼��
    public static final String ID = "occultism";
    private static final Logger LOGGER = LogManager.getLogger();

    public Occultism() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        //����ע��
        FluidRegister.FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //��Ʒע��
        Items.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //����ע��
        Blocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        //TileEntityע��
        TileEntityTypeRegister.TILE_ENTITY.register(FMLJavaModLoadingContext.get().getModEventBus());
        //Entityע��
        EntityTypeRegistry.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(Network::registerMessage);
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

    //�һ��¼����� �ú���Ŀ��ΪͰ���ж�
    @SubscribeEvent
    public void onBuckte(PlayerInteractEvent.RightClickBlock event) {

        BlockRayTraceResult result = event.getHitVec();
        BlockPos blockPos = result.getPos();
        Direction direction = result.getFace();
        BlockPos blockPos1 = blockPos.offset(direction);

        World world = event.getWorld();
        PlayerEntity player = event.getPlayer();
        ItemStack itemStack = player.getHeldItem(Hand.MAIN_HAND);
        ItemStack itemStack1 = player.getHeldItem(Hand.OFF_HAND);
        FluidState fluidState;

        //�������������¼�
        SoundEvent soundEvent = SoundEvents.BLOCK_GLASS_BREAK;

        fluidState = event.getWorld().getFluidState(blockPos1);
        //�ж��Ƿ�Ϊƿ��
        if (itemStack.getItem() != Items.bucket.get() && itemStack.getItem() != net.minecraft.item.Items.AIR && itemStack.getItem() != Items.mana_bucket.get()) {
            //�ж�������Ƿ���ͬ
            if (fluidState == Blocks.manarubikcube.get().getFluidState(Blocks.manarubikcube.get().getDefaultState())) {
                //�˴�Ϊ�޸������е�����
                world.setBlockState(blockPos1, net.minecraft.block.Blocks.WATER.getDefaultState());
            }
        } else {
            //�ж��Ƿ�Ϊ�������� �ǵĻ��Ƴ�ƿ�� ���Ҳ��Ų������������¼�
            if (fluidState != Blocks.manarubikcube.get().getFluidState(Blocks.manarubikcube.get().getDefaultState()) && fluidState != Fluids.EMPTY.getDefaultState()) {
                if (itemStack.getItem() == Items.bucket.get()) {
                    itemStack.shrink(1);
                    player.playSound(soundEvent, 1.0F, 1.0F);
                } else if (itemStack1.getItem() == Items.bucket.get()) {
                    itemStack1.shrink(1);
                    player.playSound(soundEvent, 1.0F, 1.0F);
                }
            }
        }
    }


    //��������
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
        biome.getGeneration().getFeatures(GenerationStage.Decoration.TOP_LAYER_MODIFICATION)
                .add(()-> AddFlower.FLOWER);
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
