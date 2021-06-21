package com.occultism.item;

import com.occultism.Occultism;
import com.occultism.block.OIBlocks;
import com.occultism.item.Bucket.Bucket;
import com.occultism.item.Bucket.Mana_bucket;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class OIItems {
    //物品注册
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Occultism.ID);

    public static final RegistryObject<Item> item_1 = ITEMS.register("item_1", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> item_2 = ITEMS.register("item_2", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> item_root_stick = ITEMS.register("item_root_stick", ItemRootStick::new);

    //测试用品
    public static final RegistryObject<Item> block_1 = ITEMS.register("block_1", () -> new BlockItem(OIBlocks.block_1.get(), defaultBuilder()));
    //桶
    public static final RegistryObject<Item> bucket = ITEMS.register("fluid_bucket", Bucket::new);
    public static final RegistryObject<Item> mana_bucket = ITEMS.register("mana_fluid_bucket", Mana_bucket::new);
    //矿物
    public static final RegistryObject<Item> orebrass = ITEMS.register("orebrass", () -> new BlockItem(OIBlocks.orebrass.get(), defaultBuilder()));
    public static final RegistryObject<Item> oremithril = ITEMS.register("oremithril", () -> new BlockItem(OIBlocks.oremithril.get(), defaultBuilder()));
    public static final RegistryObject<Item> oreleadzinc = ITEMS.register("oreleadzinc", () -> new BlockItem(OIBlocks.oreleadzinc.get(), defaultBuilder()));
    public static final RegistryObject<Item> orebauxite = ITEMS.register("orebauxite", () -> new BlockItem(OIBlocks.orebauxite.get(), defaultBuilder()));
    public static final RegistryObject<Item> oretinstone = ITEMS.register("oretinstone", () -> new BlockItem(OIBlocks.oretinstone.get(), defaultBuilder()));
    public static final RegistryObject<Item> crystal_stone = ITEMS.register("crystal_stone", () -> new BlockItem(OIBlocks.crystal_stone.get(), defaultBuilder()));
    //矿物锭
    public static final RegistryObject<Item> ingotcopper = ITEMS.register("ingotcopper", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotsilver = ITEMS.register("ingotsilver", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotlead = ITEMS.register("ingotlead", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingotaluminium = ITEMS.register("ingotaluminium", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> ingottin = ITEMS.register("ingottin", () -> new Item(defaultBuilder()));
    //九色水晶
    public static final RegistryObject<Item> crystal = ITEMS.register("crystal", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_1 = ITEMS.register("crystal_1", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_2 = ITEMS.register("crystal_2", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_3 = ITEMS.register("crystal_3", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_4 = ITEMS.register("crystal_4", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_5 = ITEMS.register("crystal_5", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_6 = ITEMS.register("crystal_6", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_7 = ITEMS.register("crystal_7", () -> new Item(defaultBuilder()));
    public static final RegistryObject<Item> crystal_8 = ITEMS.register("crystal_8", () -> new Item(defaultBuilder()));
    //功能方块
    public static final RegistryObject<Item> mana_extractor = ITEMS.register("mana_extractor", () -> new BlockItem(OIBlocks.mana_extractor.get(), defaultBuilder()));
    //心智表
    public static final RegistryObject<Item> mind_table = ITEMS.register("mind_table", Mind_table::new);
    //花
    public static final RegistryObject<Item> moon_flower = ITEMS.register("moon_flower", () -> new BlockItem(OIBlocks.moon_flower.get(), defaultBuilder()));
    public static final RegistryObject<Item> chamomile = ITEMS.register("chamomile", () -> new BlockItem(OIBlocks.chamomile.get(), defaultBuilder()));
    public static final RegistryObject<Item> marigold = ITEMS.register("marigold", () -> new BlockItem(OIBlocks.marigold.get(), defaultBuilder()));
    public static final RegistryObject<Item> manjushahua = ITEMS.register("manjushahua", () -> new BlockItem(OIBlocks.manjushahua.get(), defaultBuilder()));
    public static final RegistryObject<Item> mandela_grass = ITEMS.register("mandela_grass", () -> new BlockItem(OIBlocks.mandela_grass.get(), defaultBuilder()));
    public static final RegistryObject<Item> cicuta = ITEMS.register("cicuta", () -> new BlockItem(OIBlocks.cicuta.get(), defaultBuilder()));
    public static final RegistryObject<Item> datura = ITEMS.register("datura", () -> new BlockItem(OIBlocks.datura.get(), defaultBuilder()));
    public static final RegistryObject<Item> sage = ITEMS.register("sage", () -> new BlockItem(OIBlocks.sage.get(), defaultBuilder()));
    //作物种子
    public static final RegistryObject<Item> mandela_seen = ITEMS.register("mandela_seen", () -> new BlockItem(OIBlocks.mandela.get(), defaultBuilder()));
    public static final RegistryObject<Item> noema_seen = ITEMS.register("noema_seen", () -> new BlockItem(OIBlocks.noema.get(), defaultBuilder()));
    public static final RegistryObject<Item> star_fantasy_fruit_seen = ITEMS.register("star_fantasy_fruit_seen", () -> new BlockItem(OIBlocks.star_fantasy_fruit.get(), defaultBuilder()));

    //标签页
    public static ItemGroup creativeTab = new ItemGroup(Occultism.ID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(OIItems.item_1.get());
        }
    };

    /**
     * 自动导入到标签页
     * 不知道为啥是用的tab 而不是group
     */
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().tab(creativeTab);
    }
}
