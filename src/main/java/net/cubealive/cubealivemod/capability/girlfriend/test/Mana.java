package net.cubealive.cubealivemod.capability.girlfriend.test;

import net.minecraft.entity.player.PlayerEntity;

public class Mana implements IMana {
    private float mana;

    public Mana() {
        mana = 0f;
    }

    @Override
    public float getMana() {
        return mana;
    }

    @Override
    public void setMana(float mana) {
        this.mana = mana;
    }
}