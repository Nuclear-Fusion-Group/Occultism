package com.occultism.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class TestCommand implements Command<CommandSource> {
    public static TestCommand instance = new TestCommand();
    private ItemStack item;
    private Set strings;
    private CompoundNBT nbt;
    private Iterator iterator;

    private String key;

    //warn：该指令只在调试环境下有效
    @Override
    public int run(CommandContext<CommandSource> context) {
        item = context.getSource().getLevel().getPlayerByUUID(UUID.fromString("380df991-f603-344c-a090-369bad2a924a")).getItemInHand(Hand.MAIN_HAND);
        nbt = item.getOrCreateTag();
        strings = nbt.getAllKeys();
        iterator = strings.iterator();

        context.getSource().sendSuccess(new TranslationTextComponent("物品id:" + item.getDescriptionId()), false);
        context.getSource().sendSuccess(new TranslationTextComponent("物品tags内容:" + item.getItem().getTags()), false);
        context.getSource().sendSuccess(new TranslationTextComponent("物品nbt内容:"), false);

        while (true) {
            if (iterator.hasNext()) {
                key = iterator.next().toString();
                context.getSource().sendSuccess(new TranslationTextComponent(key + ":" + nbt.get(key)), false);
            }else {
                break;
            }
        }
        return 0;
    }

}
