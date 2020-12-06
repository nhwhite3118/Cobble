package com.nhwhite3118.cobbler.structures;

import com.nhwhite3118.cobbler.Cobbler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ConfiguredStructures {
    // Static instance of our structure so we can reference it and add it to biomes easily.
    public static StructureFeature<?, ?> CONFIGURED_SHULKER_FACTORY = Structures.SHULKER_FACTORY.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    /*
     * Registers the configured structure which is what gets added to the biomes. Noticed we are not using a forge registry because there is none for configured
     * structures
     */
    public static void registerConfiguredStructures() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new ResourceLocation(Cobbler.MODID, "configured_shulker_factory"), CONFIGURED_SHULKER_FACTORY);

        // Ok so, this part may be hard to grasp but basically, just add your structure to this to
        // prevent any sort of crash or issue with other mod's custom ChunkGenerators. If they use
        // FlatGenerationSettings.STRUCTURES in it and you don't add your structure to it, the game
        // could crash later when you attempt to add the StructureSeparationSettings to the dimension.

        // (It would also crash with superflat worldtype if you omit the below line
        // and attempt to add the structure's StructureSeparationSettings to the world)
        //
        // Note: If you want your structure to spawn in superflat, remove the FlatChunkGenerator check
        // in StructureTutorialMain.addDimensionalSpacing and then create a superflat world, exit it,
        // and re-enter it and your structures will be spawning. I could not figure out why it needs
        // the restart but honestly, superflat is really buggy and shouldn't be you main focus in my opinion.
        FlatGenerationSettings.STRUCTURES.put(Structures.SHULKER_FACTORY, CONFIGURED_SHULKER_FACTORY);
    }
}