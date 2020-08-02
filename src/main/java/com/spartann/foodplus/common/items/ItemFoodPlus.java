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
import java.util.function.Supplier;

public class ItemFoodPlus extends Item {

    @Nullable
    private final Supplier<List<ITextComponent>> description;

    public ItemFoodPlus(Properties properties, @Nullable Supplier<List<ITextComponent>> description) {
        super(properties);
        this.description = description;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(this.description == null || this.description.get() == null) return;

        if (KeyboardHelper.isHoldingShift()) {
            tooltip.addAll(this.description.get());
        } else {
            tooltip.add(TextComponentUtil.stringTextComponent(new TextComponentUtil.Message("Hold ", TextFormatting.GRAY), new TextComponentUtil.Message("SHIFT ", TextFormatting.BLUE),
                    new TextComponentUtil.Message("to show more information", TextFormatting.GRAY)));
        }
    }

}
