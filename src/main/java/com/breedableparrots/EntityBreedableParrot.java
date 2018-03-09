package com.breedableparrots;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;

import javax.annotation.Nullable;
import java.util.HashSet;

public class EntityBreedableParrot extends EntityParrot {
    static HashSet<Item> breedingItems = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);

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
        return EntityBreedableParrot.breedingItems.contains(stack.getItem());
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
        EntityParrot mate = (EntityParrot) ageable;

        EntityBreedableParrot child = new EntityBreedableParrot(this.world);

        if (this.isTamed()) {
            child.setOwnerId(this.getOwnerId());
            child.setTamed(true);
        }

        int variant;

        int rand = this.rand.nextInt(100);

        if (rand < 40) {
            variant = this.getVariant();
        } else if (rand < 80) {
            variant = mate.getVariant();
        } else {
            variant = this.rand.nextInt(5);
        }

        child.setVariant(variant);

        ParrotTransformer.makeNormal(this);

        if (ageable instanceof EntityBreedableParrot) {
            ParrotTransformer.makeNormal((EntityBreedableParrot)ageable);
        }

        return child;
    }

    @Override
    protected void onGrowingAdult() {
        super.onGrowingAdult();

        ParrotTransformer.makeNormal(this);
    }

    @Override
    public boolean canSitOnShoulder() {
        return false; // TODO: fix this. currently they don't render
    }
}
