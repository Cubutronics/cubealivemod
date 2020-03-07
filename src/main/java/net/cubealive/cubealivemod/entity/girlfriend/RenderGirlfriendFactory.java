package net.cubealive.cubealivemod.entity.girlfriend;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class RenderGirlfriendFactory implements IRenderFactory<GirlfriendEntity> {
    public static final RenderGirlfriendFactory INSTANCE = new RenderGirlfriendFactory();

    @Override
    public EntityRenderer<? super GirlfriendEntity> createRenderFor(EntityRendererManager manager) {
        if (FMLEnvironment.dist.isDedicatedServer())
            throw new IllegalStateException("Only run this on client!");

        return new GirlfriendRenderer(manager);
    }
}