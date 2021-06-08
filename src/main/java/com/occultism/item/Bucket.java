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

    //���캯��
    public Bucket() {
        super(() -> Fluids.EMPTY, OIItems.defaultBuilder().stacksTo(16));
    }


    @Nonnull
    @Override
    public ActionResult<ItemStack> use(@Nonnull World world, @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand) {
        //��ȡ������Ʒ��
        ItemStack itemStack = playerEntity.getItemInHand(hand);
        //׼�Ƕ�׼�ķ���
        RayTraceResult rayTraceResult = getPlayerPOVHitResult(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY);
        //װ������Ľ��
        ActionResult<ItemStack> result = ForgeEventFactory.onBucketUse(playerEntity, world, itemStack, rayTraceResult);
        if (result != null) {
            return result;
        }
        //û��ָ�򷽿�
        if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.pass(itemStack);
            //����ָ�򷽿�
        } else {
            //ָ��ķ���
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
            //��ȡ����λ��
            BlockPos blockPos = blockRayTraceResult.getBlockPos();
            //��ȡ���鷽��
            Direction direction = blockRayTraceResult.getDirection();
            //��Ӧ����ķ�������
            BlockPos blockPos1 = blockPos.relative(direction);
            //���黥������Ʒ����ͬʱ����ʱ
            if (world.mayInteract(playerEntity, blockPos) && playerEntity.mayUseItemAt(blockPos1, direction, itemStack)) {
                //��ȡ���統�еķ����
                BlockState blockState = world.getBlockState(blockPos);
                //��ȡ����
                Fluid fluid = ((IBucketPickupHandler) blockState.getBlock()).takeLiquid(world, blockPos, blockState);
                //�����¼�
                SoundEvent soundEvent = FluidRegister.mana_fluid.get().getFluid().getAttributes().getFillSound();
                if (soundEvent == null)
                    soundEvent = SoundEvents.BUCKET_FILL;
                //��������
                playerEntity.playSound(soundEvent, 1.0F, 1.0F);
                //��Ʒ��
                ItemStack itemStack1 = DrinkHelper.createFilledResult(itemStack, playerEntity, OIItems.mana_bucket.get().getDefaultInstance());
                //�ǿͻ���
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


