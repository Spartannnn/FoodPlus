package com.spartann.foodplus.client.gui;

import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.ContainerJuicer;
import com.spartann.foodplus.common.tile.TileJuicer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ScreenJuicer extends FoodPlusScreen<TileJuicer, ContainerJuicer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/juicer.png");

    public ScreenJuicer(ContainerJuicer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn, BACKGROUND_TEXTURE);
        this.xSize = 176;
        this.ySize = 151;
    }


    @Override
    public void backgroundRendering(float partialTicks, int mouseX, int mouseY) {
        this.blit(this.guiLeft + 70, this.guiTop + 21, 176, 14, this.getWorkingProgress(), 17);
    }
}
