package net.cubealive.cubealivemod.capability.girlfriend.name;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ManaStorage implements Capability.IStorage {
    @Nullable
    @Override
    public INBT writeNBT(Capability capability, Object instance, Direction side) {
        return null;
    }

    @Override
    public void readNBT(Capability capability, Object instance, Direction side, INBT nbt) {

    }
}
