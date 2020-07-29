package me.spartann.foodplus.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import me.spartann.foodplus.FoodPlusMod;
import me.spartann.foodplus.common.container.JuicerContainer;
import me.spartann.foodplus.common.tile.JuicerBlockTile;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class JuicerScreen extends ContainerScreen<JuicerContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(FoodPlusMod.MOD_ID, "textures/gui/container/juicer.png");


    public JuicerScreen(JuicerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 176;
        this.ySize = 151;
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);

        int l = JuicerBlockTile.getWorkProgress(this.container.getTile());
        this.blit(i + 70, j + 20, 176, 14, l + 1, 16);

    }
}
