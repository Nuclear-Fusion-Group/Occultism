package com.occultism.item;

import com.occultism.network.Network;

import com.occultism.network.message_packs.ExamplePack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nonnull;

/**
 * Transferred from Boson 1.16 Modding Tutorial
 * https://boson.v2mcdev.com/networking/custompack.html
 * 测试用小木棍
 * 因为不知道做什么就把这个肯定会用上的东西写了
 */
public class ItemRootStick extends Item {
    public ItemRootStick() {
        super(Items.defaultBuilder());
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, @Nonnull PlayerEntity playerIn,@Nonnull Hand handIn) {
        if (worldIn.isRemote) {
//          向服务器发包
            Network.INSTANCE.sendToServer(new ExamplePack("From the Client"));
        }
        if (!worldIn.isRemote) {
            Network.INSTANCE.send(
//                  向玩家发包
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) playerIn
                    ),
                    new ExamplePack("From Server"));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
