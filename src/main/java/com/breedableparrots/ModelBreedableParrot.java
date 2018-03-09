package com.breedableparrots;

import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

@SideOnly(Side.CLIENT)
public class ModelBreedableParrot extends ModelParrot
{
    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer wingLeft;
    ModelRenderer wingRight;
    ModelRenderer head;
    ModelRenderer head2;
    ModelRenderer beak1;
    ModelRenderer beak2;
    ModelRenderer feather;
    ModelRenderer legLeft;
    ModelRenderer legRight;

    private ModelRenderer readComponentRenderer(String name) {
        try {
            Class<?> clazz = getClass().getSuperclass();
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return (ModelRenderer)field.get(this);
        } catch (Exception e) {
            BreedableParrotsMod.logger.error("Failed to read private ModelParrot property");
            return null;
        }
    }

    public ModelBreedableParrot() {
        super();

        this.body = this.readComponentRenderer("body");
        this.tail = this.readComponentRenderer("tail");
        this.wingLeft = this.readComponentRenderer("wingLeft");
        this.wingRight = this.readComponentRenderer("wingRight");
        this.head = this.readComponentRenderer("head");
        this.head2 = this.readComponentRenderer("head2");
        this.beak1 = this.readComponentRenderer("beak1");
        this.beak2 = this.readComponentRenderer("beak2");
        this.feather = this.readComponentRenderer("feather");
        this.legLeft = this.readComponentRenderer("legLeft");
        this.legRight = this.readComponentRenderer("legRight");
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * scale, 2.0F * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.tail.render(scale);
            this.body.render(scale);
            this.legRight.render(scale);
            this.legLeft.render(scale);
            this.wingRight.render(scale);
            this.wingLeft.render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            this.head.render(scale);
            this.tail.render(scale);
            this.body.render(scale);
            this.legRight.render(scale);
            this.legLeft.render(scale);
            this.wingRight.render(scale);
            this.wingLeft.render(scale);
        }
    }
}
