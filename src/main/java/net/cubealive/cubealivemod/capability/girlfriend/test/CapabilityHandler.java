package net.cubealive.cubealivemod.capability.girlfriend.test;

import net.cubealive.cubealivemod.CubealiveMod;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Capability handler
 *
 * This class is responsible for attaching our capabilities
 */
public class CapabilityHandler
{
    public static final ResourceLocation MANA_CAP = new ResourceLocation(CubealiveMod.MODID, "mana");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof GirlfriendEntity)) return;
        event.addCapability(MANA_CAP, new ManaProvider());
    }
}