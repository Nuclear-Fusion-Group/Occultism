package com.occultism.datagen.Recipes;

import com.occultism.item.OIItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class Item_1 extends ForgeRecipeProvider {
    public Item_1(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(OIItems.item_1.get())
                .pattern("III")
                .pattern(" G ")
                .pattern(" G ")
                .define('I', Items.IRON_INGOT)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy("iron_ingot", InventoryChangeTrigger.Instance.hasItems(Items.IRON_INGOT, Items.GOLD_INGOT))
                .save(consumer);
    }
}
