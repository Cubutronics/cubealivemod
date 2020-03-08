package net.cubealive.cubealivemod.server;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;

public class WorldGirlfriend extends WorldSavedData {
    public WorldGirlfriend(String name) {
        super(name);
    }

    @Override
    public void read(CompoundNBT nbt) {

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        return null;
    }
}
