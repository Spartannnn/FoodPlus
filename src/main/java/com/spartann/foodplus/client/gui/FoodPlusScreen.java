package com.spartann.foodplus.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spartann.foodplus.common.container.ContainerFoodPlus;
import com.spartann.foodplus.common.tile.TileContainer;
import com.spartann.foodplus.common.tile.TileRecipe;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class FoodPlusScreen<T extends TileContainer, C extends ContainerFoodPlus<T>> extends ContainerScreen<C> {

    private final ResourceLocation background;
    private final T tile;

    public FoodPlusScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation background) {
        super(screenContainer, inv, titleIn);
        this.background = background;
        this.tile = screenContainer.tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        getMinecraft().getTextureManager().bindTexture(background);
        int startX = this.guiLeft;
        int startY = this.guiTop;
        this.blit(startX, startY, 0, 0, this.xSize, this.ySize);

        this.backgroundRendering(partialTicks, mouseX, mouseY);
    }

    public abstract void backgroundRendering(float partialTicks, int mouseX, int mouseY);

    protected int getWorkingProgress() {
        if (this.tile instanceof TileRecipe) {
            final int workTicks = ((TileRecipe<?>) this.tile).workingTicks;
            final int maxSmeltTime = ((TileRecipe<?>) this.tile).maxWorkTicks;
            if (workTicks <= 0 || maxSmeltTime <= 0)
                return 0;
            return (maxSmeltTime - workTicks) * TileRecipe.DEFAULT_ARROW_WIDTH / maxSmeltTime;
        }
        return 0;
    }

}
