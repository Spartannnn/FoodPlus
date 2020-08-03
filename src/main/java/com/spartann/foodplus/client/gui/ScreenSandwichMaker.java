package com.spartann.foodplus.client.gui;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.ContainerSandwichMaker;
import com.spartann.foodplus.common.tile.TileSandwichMaker;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenSandwichMaker extends FoodPlusScreen<TileSandwichMaker, ContainerSandwichMaker> {

    public ScreenSandwichMaker(ContainerSandwichMaker screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/sandwich_maker.png"));
    }

    @Override
    public void backgroundRendering(float partialTicks, int mouseX, int mouseY) {

    }
}
