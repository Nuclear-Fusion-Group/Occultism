package com.occultism.network.message_packs;


import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Transferred from Boson 1.16 Modding Tutorial
 * https://boson.v2mcdev.com/networking/custompack.html
 * 在客户端-服务端传递信息的“快递”包，常用作数据同步
 * 与教程中的代码相同，这是个传递string信息的包,
 * 因为不知道做什么就把这个肯定会用上的东西写了
 */
public class ExamplePack {
    private final String message;
    private static final Logger LOGGER = LogManager.getLogger();

    //  反序列化
//  数据逐行读取，与toBytes相对称
    public ExamplePack(PacketBuffer buffer) {
        message = buffer.readUtf(Short.MAX_VALUE);
    }

    //  输入要传递的讯息
    public ExamplePack(String message) {
        this.message = message;
    }

    //  序列化
//  数据逐行序列化
    public void toBytes(PacketBuffer buf) {
        buf.writeUtf(this.message);
    }

    //  代码执行
    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (ctx.get().getDirection().getOriginationSide().isClient()) {
                ServerPlayerEntity player = ctx.get().getSender();
                if (player != null) {
                    player.displayClientMessage(new StringTextComponent(message), true);
                }
            } else {
                LOGGER.info(this.message);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
