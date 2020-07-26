package me.spartann.foodplus.common.items.juicer;

import com.google.common.collect.Lists;
import me.spartann.foodplus.common.items.FPItem;
import me.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class JuicerPlateItem extends FPItem {

    public JuicerPlateItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 50;
    }

    @Override
    public List<ITextComponent> getDescription() {
        return Lists.newArrayList(TextComponentUtil.stringTextComponent("Use this to make juice", TextFormatting.GRAY));
    }


}
