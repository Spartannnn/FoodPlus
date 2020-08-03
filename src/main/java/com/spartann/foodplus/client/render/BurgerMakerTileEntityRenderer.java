package com.spartann.foodplus.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spartann.foodplus.common.blocks.BlockBurgerMaker;
import com.spartann.foodplus.common.tile.TileBurgerMaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BurgerMakerTileEntityRenderer extends TileEntityRenderer<TileBurgerMaker> {

    public BurgerMakerTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileBurgerMaker tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack[] stacks = tileEntityIn.getInventoryInputItems();
        for(ItemStack stack : stacks) {
            if(stack.isEmpty())
                continue;

            Direction direction = BlockBurgerMaker.getDirection(tileEntityIn.getBlockState());
            Quaternion quaternion = new Quaternion(45, 0, direction == Direction.NORTH || direction == Direction.SOUTH ? 0 : 90, true);

            matrixStackIn.push();
            matrixStackIn.translate(0.2, 1.0, 0.2);
            matrixStackIn.scale(0.6F, 0.6F, 0.6F);
            matrixStackIn.rotate(quaternion);

            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);

            matrixStackIn.pop();

        }
    }
}