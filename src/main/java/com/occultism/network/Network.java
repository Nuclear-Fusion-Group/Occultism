package com.occultism.network;

import com.occultism.Occultism;
import com.occultism.network.message_packs.ExamplePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Transferred from Boson 1.16 Modding Tutorial
 * https://boson.v2mcdev.com/networking/custompack.html
 * 客户端-服务端通讯中心，用来互相发包
 * 因为不知道做什么就把这个肯定会用上的东西写了
 * 虽然代码来自Boson教程，不过可能存在引用不规范的问题，望前辈们指导改正
 */
public class Network {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    //  注册通讯包
    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Occultism.ID, "main_channel"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
//      按照这一格式注册通讯包，参数具体作用见Boson教程
        INSTANCE.messageBuilder(ExamplePack.class, nextID())
                .encoder(ExamplePack::toBytes)
                .decoder(ExamplePack::new)
                .consumer(ExamplePack::handler)
                .add();
    }
}
