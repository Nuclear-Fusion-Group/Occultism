package com.occultism.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Iterator;
import java.util.Set;

public class TestCommand implements Command<CommandSource> {
    public static TestCommand instance = new TestCommand();
    private ItemStack item;

    @Override
    public int run(CommandContext<CommandSource> context) {

        try {
            item = context.getSource().getPlayerOrException().getItemInHand(Hand.MAIN_HAND);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        CompoundNBT nbt = item.getOrCreateTag();
        Set<String> strings = nbt.getAllKeys();
        Iterator<String> iterator = strings.iterator();
        String key;

        context.getSource().sendSuccess(new TranslationTextComponent("物品id:" + item.getDescriptionId()), false);
        context.getSource().sendSuccess(new TranslationTextComponent("物品tags内容:" + item.getItem().getTags()), false);
        context.getSource().sendSuccess(new TranslationTextComponent("物品nbt内容:"), false);

        while (true) {
            if (iterator.hasNext()) {
                key = iterator.next();
                context.getSource().sendSuccess(new TranslationTextComponent(key + ":" + nbt.get(key)), false);
            }else {
                break;
            }
        }
        return 0;
    }

}
