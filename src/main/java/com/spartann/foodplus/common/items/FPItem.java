package com.spartann.foodplus.common.items;

import com.spartann.foodplus.common.util.TextComponentUtil;
import com.spartann.foodplus.common.util.helper.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class FPItem extends Item {

    public FPItem(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(KeyboardHelper.isHoldingShift()) {
            tooltip.addAll(this.getDescription());
        } else {
            tooltip.add(TextComponentUtil.stringTextComponent(new TextComponentUtil.Message("Hold ", TextFormatting.GRAY), new TextComponentUtil.Message("SHIFT ", TextFormatting.BLUE),
                    new TextComponentUtil.Message("to show more information", TextFormatting.GRAY)));
        }
    }

    public abstract List<ITextComponent> getDescription();
}
