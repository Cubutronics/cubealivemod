package net.cubealive.cubealivemod;

import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ObjectHandlerClass {
    @ObjectHolder(CubealiveMod.MODID + ":girlfriend")
    public static EntityType<GirlfriendEntity> GIRLFRIENDENTITY;
}
