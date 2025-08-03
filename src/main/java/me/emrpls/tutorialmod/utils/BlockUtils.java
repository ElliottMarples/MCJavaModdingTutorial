package me.emrpls.tutorialmod.utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class BlockUtils {
    private static <T extends Comparable<T>> BlockState copyProperty(Property<T> property, BlockState from, BlockState to) {
        return to.setValue(property, from.getValue(property));
    }

    public static BlockState copyBlockState(BlockState oldState) {
        return copyBlockState(oldState.getBlock(), oldState);
    }

    public static BlockState copyBlockState(Block block, BlockState oldState) {
        BlockState newState = block.defaultBlockState();

        for (Property<?> property: oldState.getProperties()) {
            if (newState.hasProperty(property)) {
                newState = copyProperty(property, oldState, newState);
            }
        }

        return newState;
    }
}
