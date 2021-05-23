package com.occultism.item;

import com.occultism.network.OINetwork;

import com.occultism.network.message_packs.ExamplePack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
/**
 * Transferred from Boson 1.16 Modding Tutorial
 * https://boson.v2mcdev.com/networking/custompack.html
 * 测试用小木棍
 * 因为不知道做什么就把这个肯定会用上的东西写了
 */
public class ItemRootStick extends Item {
    public ItemRootStick() {
        super(OIItems.defaultBuilder());
    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isClientSide) {
//          向服务器发包
            OINetwork.INSTANCE.sendToServer(new ExamplePack("From the Client"));
        }
        if (!worldIn.isClientSide) {
            OINetwork.INSTANCE.send(
//                  向玩家发包
                    PacketDistributor.PLAYER.with(
                            () -> (ServerPlayerEntity) playerIn
                    ),
                    new ExamplePack("From Server"));
        }
        return super.use(worldIn, playerIn, handIn);
    }
}
