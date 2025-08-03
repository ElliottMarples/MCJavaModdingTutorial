package me.emrpls.tutorialmod.item;

import me.emrpls.tutorialmod.TutorialMod;
import me.emrpls.tutorialmod.utils.BlockUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Consumer;

public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties
                .durability(512)
        );
    }

    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.COBBLESTONE, Blocks.MOSSY_COBBLESTONE,
            Blocks.COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS,
            Blocks.COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB,
            Blocks.COBBLESTONE_WALL, Blocks.MOSSY_COBBLESTONE_WALL,
            Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS,
            Blocks.STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS,
            Blocks.STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB,
            Blocks.STONE_BRICK_WALL, Blocks.MOSSY_STONE_BRICK_WALL,
            Blocks.INFESTED_STONE_BRICKS, Blocks.INFESTED_MOSSY_STONE_BRICKS
    );

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockState blockState = level.getBlockState(context.getClickedPos());
        Block block = blockState.getBlock();

//        TutorialMod.LOGGER.info(blockState.getProperties().toString());
//        TutorialMod.LOGGER.info(block.toString());

        if (!level.isClientSide()) {
            if (CHISEL_MAP.containsKey(block)) {
                BlockState newState = BlockUtils.copyBlockState(CHISEL_MAP.get(block), blockState);
                level.setBlockAndUpdate(context.getClickedPos(), newState);
                if (context.getPlayer() != null) {
                    context.getItemInHand().hurtAndBreak(1, context.getPlayer(), InteractionHand.MAIN_HAND);
                }
                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltipAdder.accept(Component.translatable("item.tutorialmod.chisel.tooltip"));
        }
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}
