package net.coffeepowered.subtleforces.datagen;

import net.coffeepowered.subtleforces.SubtleForces;
import net.coffeepowered.subtleforces.block.ModBlocks;
import net.coffeepowered.subtleforces.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SubtleForces.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());

        //basicItem(ModItems.RADISH.get());
        //basicItem(ModItems.STARLIGHT_ASHES.get());
        //basicItem(ModItems.FROSTFIRE_ICE.get());
        //basicItem(ModItems.CHISEL.get());

        buttonItem(ModBlocks.BISMUTH_BUTTON, ModBlocks.BISMUTH_BLOCK);
        fenceItem(ModBlocks.BISMUTH_FENCE, ModBlocks.BISMUTH_BLOCK);
        wallItem(ModBlocks.BISMUTH_WALL, ModBlocks.BISMUTH_BLOCK);

        basicItem(ModBlocks.BISMUTH_DOOR.asItem());

        //basicItem(ModBlocks.RITUAL_FIRE.asItem());
        campfireItem(ModBlocks.RITUAL_FIRE, ModBlocks.BISMUTH_BLOCK);
    }

    public void campfireItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/campfire"))
                .texture("texture",ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}