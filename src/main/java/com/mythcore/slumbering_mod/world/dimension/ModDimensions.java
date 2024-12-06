package com.mythcore.slumbering_mod.world.dimension;

import com.mythcore.slumbering_mod.SlumberingMod;
import net.minecraft.registry.BootstrapContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.int_provider.ConstantIntProvider;
import net.minecraft.util.math.int_provider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {


	public static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
			new Identifier(SlumberingMod.MOD_ID, "hypnos"));

	public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(RegistryKeys.WORLD,
			new Identifier(SlumberingMod.MOD_ID, "hypnos"));

	public static final RegistryKey<DimensionType> SLUMBERING_DIM_TYPE = ModDimensionTypes.HYPNOS_DIM_TYPE;




	public static void registerDimensions() {
		 SlumberingMod.LOGGER.info("Registering Dimensions for " + SlumberingMod.MOD_ID);
	}
}
