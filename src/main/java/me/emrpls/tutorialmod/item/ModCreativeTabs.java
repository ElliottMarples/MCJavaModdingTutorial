package me.emrpls.tutorialmod.item;

import me.emrpls.tutorialmod.TutorialMod;
import me.emrpls.tutorialmod.block.ModBlocks;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MODID);

    public static final Supplier<CreativeModeTab> BISMUTH_TAB = TABS.register("bismuth_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativetab.tutorialmod.bismuth"))
                    .icon(ModItems.BISMUTH::toStack)
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_BISMUTH);
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModBlocks.MAGIC_BLOCK);
                        output.accept(ModItems.CRUMPET);
                    })
                    .build()
    );

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
        TutorialMod.LOGGER.info("Registered Mod Creative Tabs");
    }
}
