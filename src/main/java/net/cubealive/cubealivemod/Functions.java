package net.cubealive.cubealivemod;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

//TODO: Figure out how to move this to my own library without crashing when
// the functions are called.

public class Functions {
    public static Direction getDirectionAwayFromEntity(BlockPos testingBlockPosition, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (testingBlockPosition.getX() - entity.prevPosX),
                (float) (testingBlockPosition.getY() - entity.prevPosY),
                (float) (testingBlockPosition.getZ() - entity.prevPosZ)
        );
    }

    public static Direction getDirectionTowardsEntity(BlockPos testingBlockPosition, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (entity.prevPosX - testingBlockPosition.getX()),
                (float) (entity.prevPosY - testingBlockPosition.getY()),
                (float) (entity.prevPosZ - testingBlockPosition.getZ())
        );
    }

    public static Direction getHorizontalDirectionAwayFromEntity(BlockPos testingBlockPosition, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (testingBlockPosition.getX() - entity.prevPosX),
                0.0,
                (float) (testingBlockPosition.getZ() - entity.prevPosZ)
        );
    }

    public static Direction getHorizontalDirectionTowardsEntity(BlockPos testingBlockPosition, LivingEntity entity) {
        return Direction.getFacingFromVector(
                (float) (entity.prevPosX - testingBlockPosition.getX()),
                0.0,
                (float) (entity.prevPosZ - testingBlockPosition.getZ())
        );
    }

    public static BlockState cycleBackwards(BlockState state, IProperty property) {
        //There's probably a better way to do this..
        BlockState newState = state.cycle(property);

        while (newState.cycle(property).get(property) != state.get(property)) {
            newState = newState.cycle(property);
        }

        state = newState;

        return state;
    }

    public static void setRotationOffset(ModelRenderer renderer, float x, float y, float z) {
        renderer.rotateAngleX = x;
        renderer.rotateAngleY = y;
        renderer.rotateAngleZ = z;
    }
}