package net.cubealive.cubealivemod;

import net.cubealive.cubealivemod.capability.girlfriend.test.CapabilityHandler;
import net.cubealive.cubealivemod.capability.girlfriend.test.IMana;
import net.cubealive.cubealivemod.capability.girlfriend.test.Mana;
import net.cubealive.cubealivemod.capability.girlfriend.test.ManaStorage;
import net.cubealive.cubealivemod.entity.girlfriend.RenderGirlfriendFactory;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("cubealivemod")
public class CubealiveMod
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "cubealivemod";

    @ObjectHolder(MODID + ":girlfriend")
    public static EntityType<GirlfriendEntity> GIRLFRIENDENTITY;

    public CubealiveMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana::new);
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
        RenderingRegistry.registerEntityRenderingHandler(GIRLFRIENDENTITY, RenderGirlfriendFactory.INSTANCE);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("cubealivemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().registerAll(EntityType.Builder.create(GirlfriendEntity::new, EntityClassification.CREATURE)
                    .build("girlfriend")
                    .setRegistryName(MODID, "girlfriend"));
        }
    }
}