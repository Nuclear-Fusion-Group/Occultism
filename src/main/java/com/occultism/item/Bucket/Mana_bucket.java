package com.occultism.item.Bucket;

import com.occultism.block.Blocks;
import com.occultism.fluid.FluidRegister;
import com.occultism.item.Items;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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

public class Mana_bucket extends BucketItem {
    public Mana_bucket() {
        super(() -> FluidRegister.mana_fluid.get(), Items.defaultBuilder().maxStackSize(1));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand) {
        //获取手中物品堆
        ItemStack itemStack = playerEntity.getHeldItem(hand);
        //准星对准的方块
        RayTraceResult rayTraceResult = rayTrace(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY);
        //装起来后的结果
        ActionResult<ItemStack> result = ForgeEventFactory.onBucketUse(playerEntity, world, itemStack, rayTraceResult);
        if (result != null) {
            return result;
        }
        //没有指向方块
        if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.resultPass(itemStack);
            //并不指向方块
        } else {
            //指向的方块
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
            //获取方块位置
            BlockPos blockPos = blockRayTraceResult.getPos();
            //获取方块方向
            Direction direction = blockRayTraceResult.getFace();
            //对应方向的方块坐标
            BlockPos blockPos1 = blockPos.offset(direction);
            //方块互动和物品互动同时成立时
            if (world.isBlockModifiable(playerEntity, blockPos) && playerEntity.canPlayerEdit(blockPos1, direction, itemStack)) {
                //声音事件
                SoundEvent soundEvent = FluidRegister.mana_fluid.get().getFluid().getAttributes().getFillSound();
                if (soundEvent == null)
                    soundEvent = SoundEvents.ITEM_BUCKET_FILL;
                //播放声音
                playerEntity.playSound(soundEvent, 1.0F, 1.0F);
                //物品堆
                ItemStack itemStack1 = DrinkHelper.fill(itemStack, playerEntity, Items.bucket.get().getDefaultInstance());
                //非客户端
                if (!world.isRemote) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerEntity, Items.bucket.get().getDefaultInstance());
                }
                //设置流体并更新
                world.setBlockState(world.getFluidState(blockPos).isEmpty() ? blockPos1 : blockPos, Blocks.manarubikcube.get().getDefaultState());

                return ActionResult.func_233538_a_(itemStack1, world.isRemote());
            } else {
                return ActionResult.resultFail(itemStack);
            }
        }
    }
}
