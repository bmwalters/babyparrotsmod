package com.breedableparrots;

import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

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

        if (item.getItem() != Items.BOOK || world.isRemote) {
            return;
        }

        if (event.getTarget().getClass() == EntityParrot.class) {
            EntityParrot parrot = (EntityParrot) event.getTarget();

            NBTTagCompound foo = new NBTTagCompound();
            parrot.writeEntityToNBT(foo);

            EntityBreedableParrot newParrot = new EntityBreedableParrot(world);
            newParrot.readEntityFromNBT(foo);

            parrot.setInLove(null);

            newParrot.setPositionAndRotation(parrot.posX, parrot.posY, parrot.posZ, parrot.rotationYaw, parrot.rotationPitch);
            // TODO: set attributes that aren't covered by readEntityFromNBT

            world.removeEntity(parrot);
            world.spawnEntity(newParrot);
            newParrot.spawnExplosionParticle();

            event.setCanceled(true);
        } else if (event.getTarget().getClass() == EntityBreedableParrot.class) {
            EntityBreedableParrot parrot = (EntityBreedableParrot)event.getTarget();

            parrot.setInLove(null);
        }
    }
}
