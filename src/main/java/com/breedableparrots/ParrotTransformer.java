package com.breedableparrots;

import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ParrotTransformer {
    public static EntityBreedableParrot makeBreedable(EntityParrot parrot) {
        World world = parrot.world;

        NBTTagCompound foo = new NBTTagCompound();
        parrot.writeEntityToNBT(foo);

        EntityBreedableParrot newParrot = new EntityBreedableParrot(world);
        newParrot.readEntityFromNBT(foo);

        newParrot.setPositionAndRotation(parrot.posX, parrot.posY, parrot.posZ, parrot.rotationYaw, parrot.rotationPitch);
        // TODO: set attributes that aren't covered by readEntityFromNBT

        world.removeEntity(parrot);
        world.spawnEntity(newParrot);
        newParrot.spawnExplosionParticle();

        return newParrot;
    }

    public static EntityParrot makeNormal(EntityBreedableParrot parrot) {
        World world = parrot.world;

        NBTTagCompound foo = new NBTTagCompound();
        parrot.writeEntityToNBT(foo);

        EntityParrot newParrot = new EntityParrot(world);
        newParrot.readEntityFromNBT(foo);

        newParrot.setPositionAndRotation(parrot.posX, parrot.posY, parrot.posZ, parrot.rotationYaw, parrot.rotationPitch);
        // TODO: set attributes that aren't covered by readEntityFromNBT

        world.removeEntity(parrot);
        world.spawnEntity(newParrot);
        newParrot.spawnExplosionParticle();

        return newParrot;
    }
}
