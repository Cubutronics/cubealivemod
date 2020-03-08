package net.cubealive.cubealivemod.capability.girlfriend.name;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaProvider implements ICapabilitySerializable {

    @CapabilityInject(IMana.class)
    public static final Capability<IMana> MANA = null;

    private IMana instance = MANA.getDefaultInstance();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return null;
    }

    @Override
    public INBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(INBT nbt) {

    }
}
