package com.spartann.foodplus.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spartann.foodplus.FoodPlusMod;
import com.spartann.foodplus.common.container.JuicerContainer;
import com.spartann.foodplus.common.tile.JuicerTile;
import com.spartann.foodplus.common.tile.RecipeTile;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class JuicerScreen extends ContainerScreen<JuicerContainer> {

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/juicer.png");

    public JuicerScreen(JuicerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 151;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int startX = this.guiLeft;
        int startY = this.guiTop;

        this.blit(startX, startY, 0, 0, this.xSize, this.ySize);

        final JuicerTile tileEntity = container.tile;
        if (tileEntity.workingTicks > 0) {
            int arrowWidth = getWorkingProgress();
            this.blit(startX + 70, startY + 21, 176, 14, arrowWidth, 17);
        }
    }

    private int getWorkingProgress() {
        final JuicerTile tileEntity = this.container.tile;
        final int workTicks = tileEntity.workingTicks;
        final int maxSmeltTime = tileEntity.maxWorkTicks;
        if (workTicks <= 0 || maxSmeltTime <= 0)
            return 0;
        return (maxSmeltTime - workTicks) * RecipeTile.DEFAULT_ARROW_WIDTH / maxSmeltTime;
    }
}
