package net.cubealive.cubealivemod.capability.girlfriend.test;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ManaProvider implements ICapabilitySerializable<CompoundNBT> {
    @CapabilityInject(IMana.class)
    public static final Capability<IMana> MANA_CAPABILITY=null;

    private LazyOptional<IMana> instance = LazyOptional.of(Mana::new);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap != MANA_CAPABILITY){
            return LazyOptional.empty();
        }
        return this.instance.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT)MANA_CAPABILITY.getStorage()
                .writeNBT(MANA_CAPABILITY,
                        instance
                                .orElseThrow(()->new IllegalArgumentException("LazyOptional must not be empty!")),
                        null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        MANA_CAPABILITY.getStorage()
                .readNBT(MANA_CAPABILITY,
                        instance
                                .orElseThrow(()->new IllegalArgumentException("LazyOptional must not be empty!")),
                        null, nbt);
    }
}