package net.cubealive.cubealivemod.capability.girlfriend.name;

public class Mana implements IMana {
    private int mana = 0;

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int x) {
        this.mana = x;
    }
}
