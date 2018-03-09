package com.breedableparrots;

import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public class ParrotUpgradeHandler {
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(EntityBreedableParrot.entry());
    }

    @SubscribeEvent
    public static void handleInteraction(PlayerInteractEvent.EntityInteract event) {
        ItemStack item = event.getEntityPlayer().getHeldItem(event.getHand());
        World world = event.getEntityPlayer().world;

        if (world.isRemote) {
            return;
        }

        if (event.getTarget().getClass() == EntityParrot.class) {
            EntityParrot parrot = (EntityParrot) event.getTarget();

            if (parrot.isTamed() && EntityBreedableParrot.breedingItems.contains(item.getItem())) {
                ParrotTransformer.makeBreedable(parrot);

                event.setCanceled(true);
            }
        }
    }
}
