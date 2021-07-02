package com.occultism.entity.api;

import com.occultism.entity.Goal.MobGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class BaseEntity extends MonsterEntity {
    protected BaseEntity(EntityType<? extends MonsterEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.goalSelector.addGoal(2, new MobGoal(this, 1.0D, false));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        //伤害的实体
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        //玩家实体
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.getAttributeManager().getAttributeBaseValue(Attributes.MAX_HEALTH);
        this.getAttributeManager().getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
    }
}
