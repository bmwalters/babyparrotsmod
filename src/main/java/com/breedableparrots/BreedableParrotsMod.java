package com.breedableparrots;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(modid = BreedableParrotsMod.MODID, name = BreedableParrotsMod.NAME, version = BreedableParrotsMod.VERSION)
public class BreedableParrotsMod
{
    public static final String MODID = "breedableparrotsmod";
    public static final String NAME = "Breedable Parrots Mod";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        RenderingRegistry.registerEntityRenderingHandler(EntityBreedableParrot.class, new RenderBreedableParrot.Factory());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {}
}
