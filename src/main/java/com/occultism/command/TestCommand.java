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

public class TestCommand implements Command<CommandSource> {
    public static TestCommand instance = new TestCommand();

    private ItemStack item;

    @Override
    public int run(CommandContext<CommandSource> context) {

        try {
            item = context.getSource().asPlayer().getHeldItem(Hand.MAIN_HAND);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        CompoundNBT nbt = item.getOrCreateTag();
        Iterator<String> iterator = nbt.keySet().iterator();
        String key;

        context.getSource().sendFeedback(new TranslationTextComponent("��Ʒid:" + item.getTranslationKey()), false);
        context.getSource().sendFeedback(new TranslationTextComponent("��Ʒtags����:" + item.getItem().getTags()), false);
        context.getSource().sendFeedback(new TranslationTextComponent("��Ʒnbt����:"), false);

        while (true) {
            if (iterator.hasNext()) {
                key = iterator.next();
                context.getSource().sendFeedback(new TranslationTextComponent(key + ":" + nbt.get(key)), false);
            } else {
                break;
            }
        }
        return 0;
    }

}
