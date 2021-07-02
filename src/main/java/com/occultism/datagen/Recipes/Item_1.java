package com.occultism.datagen.Recipes;

import com.occultism.item.Items;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import java.util.function.Consumer;

public class Item_1 extends ForgeRecipeProvider {
    public Item_1(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(Items.item_1.get())
                .patternLine("III")
                .patternLine(" G ")
                .patternLine(" G ")
                .key('I', net.minecraft.item.Items.IRON_INGOT)
                .key('G', net.minecraft.item.Items.GOLD_INGOT)
                .addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(net.minecraft.item.Items.IRON_INGOT, net.minecraft.item.Items.GOLD_INGOT))
                .build(consumer);
    }
}
