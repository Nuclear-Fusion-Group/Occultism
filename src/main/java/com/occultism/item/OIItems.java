package com.occultism.item;

import com.occultism.Occultism;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class OIItems {
    //物品注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultism.ID);

    public static final RegistryObject<Item> item_1 = ITEMS.register("item_1", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> item_2 = ITEMS.register("item_2", () -> new Item(defaultBuilder()));

    //标签页
    public static ItemGroup creativeTab = new ItemGroup(Occultism.ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OIItems.item_1.get());
        }
    };

    /**自动导入到标签页
     * 不知道为啥是用的tab 而不是group
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }
}
