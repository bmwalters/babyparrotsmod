package com.breedableparrots;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;

import javax.annotation.Nullable;

public class EntityBreedableParrot extends EntityParrot {
    public EntityBreedableParrot(World worldIn) {
        super(worldIn);
    }

    public static EntityEntry entry() {
        return new EntityEntry(EntityBreedableParrot.class, "BreedableParrot")
                .setRegistryName("BreedableParrot");
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS).contains(stack.getItem());
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (otherAnimal.getClass() != this.getClass())
        {
            return false;
        }
        else
        {
            return this.isInLove() && otherAnimal.isInLove();
        }
    }

    @Nullable
    @Override
    public EntityBreedableParrot createChild(EntityAgeable ageable) {
        return new EntityBreedableParrot(this.world);
    }
}
