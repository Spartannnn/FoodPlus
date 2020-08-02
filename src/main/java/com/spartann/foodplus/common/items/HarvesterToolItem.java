package com.spartann.foodplus.common.items;

import com.spartann.foodplus.common.registries.ModBlocks;
import com.spartann.foodplus.common.util.TextComponentUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class HarvesterToolItem extends ToolItem {

    public HarvesterToolItem(Properties builder) {
        super(1.0F, 1.2F, ItemTier.IRON, ModBlocks.getAllLeaves(), builder);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 150;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextComponentUtil.stringTextComponent("Use this tool to harvest the fruits", TextFormatting.AQUA));
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.LEAVES ? super.getDestroySpeed(stack, state) : this.efficiency;
    }
}
