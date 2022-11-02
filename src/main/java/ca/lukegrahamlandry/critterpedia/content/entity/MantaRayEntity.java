package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MantaRayEntity extends AbstractFish implements IAnimatable {
    public MantaRayEntity(EntityType<MantaRayEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Parrot.createAttributes().add(Attributes.MAX_HEALTH, 6.0D);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInWaterOrBubble()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.manta_ray.swim", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.manta_ray.OnLand", true));
        }

        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }
}
