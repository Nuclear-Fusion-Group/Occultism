package com.occultism.item;

import com.occultism.fluid.FluidRegister;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;

public class Bucket extends BucketItem {

    //构造函数
    public Bucket() {
        super(() -> Fluids.EMPTY, OIItems.defaultBuilder().stacksTo(16));
    }


    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World world, @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand) {
        //获取手中物品堆
        ItemStack itemStack = playerEntity.getItemInHand(hand);
        //准星对准的方块
        RayTraceResult rayTraceResult = getPlayerPOVHitResult(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY);
        //装起来后的结果
        ActionResult<ItemStack> result = ForgeEventFactory.onBucketUse(playerEntity, world, itemStack, rayTraceResult);
        if (result != null) {
            return result;
        }
        //没有指向方块
        if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemStack);
            //并不指向方块
        } else {
            //指向的方块
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
            //获取方块位置
            BlockPos blockPos = blockRayTraceResult.getBlockPos();
            //获取方块方向
            Direction direction = blockRayTraceResult.getDirection();
            //对应方向的方块坐标
            BlockPos blockPos1 = blockPos.relative(direction);
            //方块互动和物品互动同时成立时
            if (world.mayInteract(playerEntity, blockPos) && playerEntity.mayUseItemAt(blockPos1, direction, itemStack)) {
                //获取世界当中的方块堆
                BlockState blockState = world.getBlockState(blockPos);
                //获取流体
                Fluid fluid = ((IBucketPickupHandler) blockState.getBlock()).takeLiquid(world, blockPos, blockState);
                //声音事件
                SoundEvent soundEvent = FluidRegister.mana_fluid.get().getFluid().getAttributes().getFillSound();
                if (soundEvent == null)
                    soundEvent = SoundEvents.BUCKET_FILL;
                //播放声音
                playerEntity.playSound(soundEvent, 1.0F, 1.0F);
                //物品堆
                ItemStack itemStack1 = DrinkHelper.createFilledResult(itemStack, playerEntity, OIItems.mana_bucket.get().getDefaultInstance());
                //非客户端
                if (!world.isClientSide) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerEntity, OIItems.mana_bucket.get().getDefaultInstance());
                }

                return ActionResult.sidedSuccess(itemStack1, world.isClientSide());
            } else {
                return ActionResult.pass(itemStack);
            }
        }
    }
}


