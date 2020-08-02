package com.spartann.foodplus.common.util;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.commons.lang3.Validate;

public class TextComponentUtil {

    public static StringTextComponent stringTextComponent(String string, TextFormatting... formattings) {
        StringTextComponent stringTextComponent = new StringTextComponent(string);
        stringTextComponent.applyTextStyles(formattings);
        return stringTextComponent;
    }

    public static StringTextComponent stringTextComponent(Message... messages) {
        Validate.noNullElements(messages, "Message objects can not be null");
        if(messages.length == 0)
            return new StringTextComponent("No messages found");
        StringTextComponent stringTextComponent = new StringTextComponent(messages[0].text);
        stringTextComponent.applyTextStyle(messages[0].color);
        if(messages.length > 1)
            for(Message m : messages) {
                stringTextComponent.appendText(m.text);
                stringTextComponent.applyTextStyle(m.color);
            }
        return stringTextComponent;
    }

    public static TranslationTextComponent translationTextComponent(String code) {
        return new TranslationTextComponent(FoodPlusMod.MOD_ID + "." + code);
    }

    public static class Message {

        private final String text;
        private final TextFormatting color;

        public Message(String text, TextFormatting color) {
            this.text = text;
            this.color = color;
        }

    }

}
