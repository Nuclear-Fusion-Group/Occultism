package com.occultism.item;

import com.occultism.fluid.FluidRegister;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
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
        super(() -> Fluids.EMPTY, OIItems.defaultBuilder().stacksTo(1));
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
            BlockPos blockPos2;
            //���黥������Ʒ����ͬʱ����ʱ
            if (world.mayInteract(playerEntity, blockPos) && playerEntity.mayUseItemAt(blockPos1, direction, itemStack)) {
                FluidState fluidState = world.getFluidState(blockPos1);
                //�����¼�
                SoundEvent soundEvent = FluidRegister.mana_fluid.get().getFluid().getAttributes().getFillSound();
                if (soundEvent == null)
                    soundEvent = SoundEvents.BUCKET_FILL;
                //��������
                playerEntity.playSound(soundEvent, 1.0F, 1.0F);
                playerEntity.setItemInHand(hand, OIItems.mana_bucket.get().getDefaultInstance());
                blockPos2 = fluidState.isEmpty() ? blockPos : blockPos1;
                world.setBlockAndUpdate(blockPos2, Blocks.AIR.defaultBlockState());
            } else {
                return ActionResult.fail(itemStack);
            }
            return ActionResult.fail(itemStack);
        }
    }
}


