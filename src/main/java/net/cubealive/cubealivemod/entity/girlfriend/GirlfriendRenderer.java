package net.cubealive.cubealivemod.entity.girlfriend;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.cubealive.cubealivemod.CubealiveMod;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class GirlfriendRenderer extends MobRenderer<GirlfriendEntity, GirlfriendModel<GirlfriendEntity>> {
    //private static ResourceLocation SKIN = new ResourceLocation(CubealiveMod.MODID,"textures/entity/girlfriend/girlfriend.png");

    public GirlfriendRenderer(EntityRendererManager renderManager) {
        //   (renderManager,               girlfriend,     shadowSize);
        super(renderManager, new GirlfriendModel<>(),0.6F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(GirlfriendEntity entity) { return entity.getGirlfriendTypeName(); }

    @Override
    public EntityRendererManager getRenderManager() {
        return super.getRenderManager();
    }

    //public static void setEntityTexture(ResourceLocation resourceLocation){ SKIN = resourceLocation; }

    @Override
    protected void preRenderCallback(GirlfriendEntity entity, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entity, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.9f, 0.9f, 0.9f);
    }
}