package com.occultism.fluid;

import com.occultism.Occultism;
import com.occultism.item.OIItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * ∑¢…‰∆˜∑≈÷√ƒß¡¶
 */
@Mod.EventBusSubscriber(modid = Occultism.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DispenserRegister {
    @SubscribeEvent
    public static void onDispenserRegister(FMLCommonSetupEvent event) {
        DispenserBlock.registerBehavior(OIItems.mana_bucket.get(), new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            public ItemStack dispenserStack(IBlockSource source, ItemStack itemStack) {

                BucketItem bucketItem = (BucketItem) itemStack.getItem();
                BlockPos blockPos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                World world = source.getLevel();

                if (bucketItem.emptyBucket(null, world, blockPos, null)) {
                    bucketItem.checkExtraContent(world, itemStack, blockPos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(source, itemStack);
                }
            }
        });
    }
}
