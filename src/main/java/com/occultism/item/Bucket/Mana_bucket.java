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
        //��ȡ������Ʒ��
        ItemStack itemStack = playerEntity.getHeldItem(hand);
        //׼�Ƕ�׼�ķ���
        RayTraceResult rayTraceResult = rayTrace(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY);
        //װ������Ľ��
        ActionResult<ItemStack> result = ForgeEventFactory.onBucketUse(playerEntity, world, itemStack, rayTraceResult);
        if (result != null) {
            return result;
        }
        //û��ָ�򷽿�
        if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
            return ActionResult.resultPass(itemStack);
            //����ָ�򷽿�
        } else {
            //ָ��ķ���
            BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) rayTraceResult;
            //��ȡ����λ��
            BlockPos blockPos = blockRayTraceResult.getPos();
            //��ȡ���鷽��
            Direction direction = blockRayTraceResult.getFace();
            //��Ӧ����ķ�������
            BlockPos blockPos1 = blockPos.offset(direction);
            //���黥������Ʒ����ͬʱ����ʱ
            if (world.isBlockModifiable(playerEntity, blockPos) && playerEntity.canPlayerEdit(blockPos1, direction, itemStack)) {
                //�����¼�
                SoundEvent soundEvent = FluidRegister.mana_fluid.get().getFluid().getAttributes().getFillSound();
                if (soundEvent == null)
                    soundEvent = SoundEvents.ITEM_BUCKET_FILL;
                //��������
                playerEntity.playSound(soundEvent, 1.0F, 1.0F);
                //��Ʒ��
                ItemStack itemStack1 = DrinkHelper.fill(itemStack, playerEntity, Items.bucket.get().getDefaultInstance());
                //�ǿͻ���
                if (!world.isRemote) {
                    CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayerEntity) playerEntity, Items.bucket.get().getDefaultInstance());
                }
                //�������岢����
                world.setBlockState(world.getFluidState(blockPos).isEmpty() ? blockPos1 : blockPos, Blocks.manarubikcube.get().getDefaultState());

                return ActionResult.func_233538_a_(itemStack1, world.isRemote());
            } else {
                return ActionResult.resultFail(itemStack);
            }
        }
    }
}
