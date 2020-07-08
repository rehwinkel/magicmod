package deerangle.magicmod.world;

import deerangle.magicmod.block.BlockRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;

public class WorldGen {

    public static final OreFeatureConfig amethystOreConfig = new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
            BlockRegistry.AMETHYST_ORE.getDefaultState(), 6);
    public static final OreFeatureConfig siltStoneConfig = new OreFeatureConfig(FillerBlockType.NATURAL_STONE,
            BlockRegistry.SILT_STONE.getDefaultState(), 33);
    public static final ReplaceBlockConfig amethystSiltOreConfig = new ReplaceBlockConfig(
            BlockRegistry.SILT_STONE.getDefaultState(), BlockRegistry.AMETHYST_SILT_ORE.getDefaultState());

    public static void addAmethystOre(Biome biome) {
        biome.addFeature(Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(amethystOreConfig)
                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(2, 5, 15, 45))));
        biome.addFeature(Decoration.UNDERGROUND_DECORATION, Feature.EMERALD_ORE.withConfiguration(amethystSiltOreConfig)
                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 80))));
    }

    public static void addSiltStone(Biome biome) {
        biome.addFeature(Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(siltStoneConfig)
                .withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(3, 0, 0, 80))));
    }

}