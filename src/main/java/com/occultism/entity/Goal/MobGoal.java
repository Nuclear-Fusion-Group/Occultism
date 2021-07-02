package com.occultism.entity.Goal;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class MobGoal extends MeleeAttackGoal {
    private final CreatureEntity historicalCover;
    private int raiseArmTicks;

    public MobGoal(CreatureEntity entity, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(entity, speedModifier, followingTargetEvenIfNotSeen);
        this.historicalCover = entity;
    }


    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    @Override
    public void resetTask() {
        super.resetTask();
        this.historicalCover.setAggroed(false);
    }


    @Override
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getSwingCooldown() < this.func_234042_k_() / 2) {
            this.historicalCover.setAggroed(true);
        } else {
            this.historicalCover.setAggroed(false);
        }

    }
}
