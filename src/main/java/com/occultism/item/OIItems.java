package com.occultism.item;

import com.occultism.Occultism;
import com.occultism.block.OIBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class OIItems {
    //��Ʒע��
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultism.ID);

    public static final RegistryObject<Item> item_1 = ITEMS.register("item_1", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> item_2 = ITEMS.register("item_2", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> item_root_stick = ITEMS.register("item_root_stick", ItemRootStick::new);

    //������Ʒ
    public static final RegistryObject<Item> block_1 = ITEMS.register("block_1", () -> new BlockItem(OIBlocks.block_1.get(), defaultBuilder()));
    //Ͱ
    public static final RegistryObject<Item> bucket = ITEMS.register("fluid_bucket", Bucket::new);
    public static final RegistryObject<Item> mana_bucket = ITEMS.register("mana_fluid_bucket", Mana_bucket::new);
    //����
    public static final RegistryObject<Item> orebrass = ITEMS.register("orebrass", () -> new BlockItem(OIBlocks.orebrass.get(), defaultBuilder()));
    public static final RegistryObject<Item> oremithril = ITEMS.register("oremithril", () -> new BlockItem(OIBlocks.oremithril.get(), defaultBuilder()));
    public static final RegistryObject<Item> oreleadzinc = ITEMS.register("oreleadzinc", () -> new BlockItem(OIBlocks.oreleadzinc.get(), defaultBuilder()));
    public static final RegistryObject<Item> orebauxite = ITEMS.register("orebauxite", () -> new BlockItem(OIBlocks.orebauxite.get(), defaultBuilder()));
    public static final RegistryObject<Item> oretinstone = ITEMS.register("oretinstone", () -> new BlockItem(OIBlocks.oretinstone.get(), defaultBuilder()));
    //���ﶧ
    public static final RegistryObject<Item> ingotcopper = ITEMS.register("ingotcopper", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotsilver = ITEMS.register("ingotsilver", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotlead = ITEMS.register("ingotlead", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotaluminium = ITEMS.register("ingotaluminium", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingottin = ITEMS.register("ingottin", () -> new Item(defaultBuilder()));

    //��ǩҳ
    public static ItemGroup creativeTab = new ItemGroup(Occultism.ID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OIItems.item_1.get());
        }
    };

    /**
     * �Զ����뵽��ǩҳ
     * ��֪��Ϊɶ���õ�tab ������group
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }
}
