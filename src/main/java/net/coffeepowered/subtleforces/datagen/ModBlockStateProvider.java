package net.coffeepowered.subtleforces.datagen;

import net.coffeepowered.subtleforces.SubtleForces;
import net.coffeepowered.subtleforces.block.ModBlocks;
import net.coffeepowered.subtleforces.block.custom.BismuthLampBlock;
import net.coffeepowered.subtleforces.block.custom.RitualFire;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SubtleForces.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BISMUTH_BLOCK);

        //blockWithItem(ModBlocks.BISMUTH_ORE);
        //blockWithItem(ModBlocks.BISMUTH_DEEPSLATE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlock(ModBlocks.BISMUTH_STAIRS.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        slabBlock(ModBlocks.BISMUTH_SLAB.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        buttonBlock(ModBlocks.BISMUTH_BUTTON.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        pressurePlateBlock(ModBlocks.BISMUTH_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        fenceBlock(ModBlocks.BISMUTH_FENCE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        fenceGateBlock(ModBlocks.BISMUTH_FENCE_GATE.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));
        wallBlock(ModBlocks.BISMUTH_WALL.get(), blockTexture(ModBlocks.BISMUTH_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.BISMUTH_DOOR.get(), modLoc("block/bismuth_door_bottom"), modLoc("block/bismuth_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BISMUTH_TRAPDOOR.get(), modLoc("block/bismuth_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BISMUTH_STAIRS);
        blockItem(ModBlocks.BISMUTH_SLAB);
        blockItem(ModBlocks.BISMUTH_PRESSURE_PLATE);
        blockItem(ModBlocks.BISMUTH_FENCE_GATE);
        blockItem(ModBlocks.BISMUTH_TRAPDOOR, "_bottom");

        customLamp();
        customCampfire();

        blockItem(ModBlocks.RITUAL_FIRE);
    }

    private void customCampfire() {
        getVariantBuilder(ModBlocks.RITUAL_FIRE.get()).forAllStates(state -> {
            if(state.getValue(RitualFire.LIT)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "bismuth_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("bismuth_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "bismuth_lamp_off")))};
            }
        });

        simpleBlockItem(ModBlocks.RITUAL_FIRE.get(), models().cubeAll("bismuth_lamp_on",
                ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "bismuth_lamp_on")));
    }



    private void customLamp() {
        getVariantBuilder(ModBlocks.BISMUTH_LAMP.get()).forAllStates(state -> {
            if(state.getValue(BismuthLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("ritual_fire_lit",
                        ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "ritual_fire_lit")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("ritual_fire_unlit",
                        ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "ritual_fire_unlit")))};
            }
        });

        simpleBlockItem(ModBlocks.BISMUTH_LAMP.get(), models().cubeAll("bismuth_lamp_on",
                ResourceLocation.fromNamespaceAndPath(SubtleForces.MOD_ID, "block/" + "bismuth_lamp_on")));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("subtleforces:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("subtleforces:block/" + deferredBlock.getId().getPath() + appendix));
    }
}