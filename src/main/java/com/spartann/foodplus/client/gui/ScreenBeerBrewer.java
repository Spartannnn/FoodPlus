package com.spartann.foodplus.client.gui;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.ContainerBeerBrewer;
import com.spartann.foodplus.common.tile.TileBeerBrewer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenBeerBrewer extends FoodPlusScreen<TileBeerBrewer, ContainerBeerBrewer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/beer_brewer.png");

    public ScreenBeerBrewer(ContainerBeerBrewer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, BACKGROUND_TEXTURE);
        this.xSize = 176;
        this.ySize = 185;
    }


    @Override
    public void backgroundRendering(float partialTicks, int mouseX, int mouseY) {
        this.blit(this.guiLeft + 76, this.guiTop + 38, 176, 14, this.getWorkingProgress(), 17);
    }
}
