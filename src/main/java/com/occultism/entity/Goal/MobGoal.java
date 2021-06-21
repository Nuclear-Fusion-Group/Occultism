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
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.historicalCover.setAggressive(false);
    }


    @Override
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.historicalCover.setAggressive(true);
        } else {
            this.historicalCover.setAggressive(false);
        }

    }
}
