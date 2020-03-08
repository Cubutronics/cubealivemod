package net.cubealive.cubealivemod.capability.girlfriend.test;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ManaStorage implements Capability.IStorage<IMana> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<IMana> capability, IMana instance, Direction side) {
        CompoundNBT tag =new CompoundNBT();
        tag.putFloat("mana", instance.getMana());
        //tag.putString("realm", instance.getRealm().name);
        //tag.putFloat("xiuWei", instance.getXiuWei());
        //tag.putFloat("maxMagic", instance.getMaxMagic());
        return tag;
    }

    @Override
    public void readNBT(Capability<IMana> capability, IMana instance, Direction side, INBT nbt) {
        CompoundNBT tag = (CompoundNBT)nbt;
        instance.setMana(tag.getFloat("mana"));
        //instance.setRealm(Realm.fromName(tag.getString("realm")));
        //instance.setXiuWei(tag.getFloat("xiuWei"));

    }

}