package com.breedableparrots;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BreedableParrotsMod.MODID, name = BreedableParrotsMod.NAME, version = BreedableParrotsMod.VERSION)
public class BreedableParrotsMod
{
    public static final String MODID = "breedableparrotsmod";
    public static final String NAME = "Breedable Parrots Mod";
    public static final String VERSION = "0.1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {}
}
