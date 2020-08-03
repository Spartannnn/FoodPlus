package com.spartann.foodplus.client.gui;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.ContainerBurgerMaker;
import com.spartann.foodplus.common.tile.TileBurgerMaker;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenBurgerMaker extends FoodPlusScreen<TileBurgerMaker, ContainerBurgerMaker> {

    public ScreenBurgerMaker(ContainerBurgerMaker screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/burger_maker.png"));
        this.xSize = 176;
        this.ySize = 158;
    }

    @Override
    public void backgroundRendering(float partialTicks, int mouseX, int mouseY) {
        this.blit(this.guiLeft + 77, this.guiTop + 29, 176, 14, this.getWorkingProgress(), 17);
    }
}
