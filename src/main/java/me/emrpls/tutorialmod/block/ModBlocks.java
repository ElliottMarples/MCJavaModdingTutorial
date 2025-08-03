package me.emrpls.tutorialmod.block;

import me.emrpls.tutorialmod.TutorialMod;
import me.emrpls.tutorialmod.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TutorialMod.MODID);
    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .requiresCorrectToolForDrops()
                    .strength(4.0f)
    );
    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f)
                    .sound(SoundType.AMETHYST)
    );
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block", MagicBlock::new,
            BlockBehaviour.Properties.of()
    );

    private static DeferredBlock<Block> registerBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, Block::new, properties);
    }

    private static DeferredBlock<Block> registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFunc, BlockBehaviour.Properties properties) {
        DeferredBlock<Block> block = BLOCKS.registerBlock(name, blockFunc, properties);
        ModItems.ITEMS.registerSimpleBlockItem(block);

        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        TutorialMod.LOGGER.info("Registered Mod Blocks");
    }
}
