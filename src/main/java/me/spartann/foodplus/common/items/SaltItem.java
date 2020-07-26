package me.spartann.foodplus.common.items;

import com.google.common.collect.Lists;
import me.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class SaltItem extends FPItem {

    public SaltItem(Properties properties) {
        super(properties);
    }

    @Override
    public List<ITextComponent> getDescription() {
        return Lists.newArrayList(TextComponentUtil.stringTextComponent("Basic crafting resource", TextFormatting.GRAY));
    }
}
