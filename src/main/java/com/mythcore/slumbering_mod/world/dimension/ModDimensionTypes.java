package com.mythcore.slumbering_mod.world.dimension;

import com.mythcore.slumbering_mod.SlumberingMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.int_provider.UniformIntProvider;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class ModDimensionTypes {
	public static final RegistryKey<DimensionType> HYPNOS_DIM_TYPE = RegistryKey.of(
		RegistryKeys.DIMENSION_TYPE, new Identifier(SlumberingMod.MOD_ID, "hypnos_type")
	);

	public static DimensionType createSlumberingDimensionType() {
		return new DimensionType(
			OptionalLong.of(18000L), // Fixed time
			true, // Skylight
			false, // Has ceiling
			false, // Ultra-warm
			false, // Natural
			1.0, // Coordinate scale
			false, // Respawn anchor works
			false, // Bed works
			0, // minimum height
			256, // maximum height
			256, // Logical height
			BlockTags.INFINIBURN_OVERWORLD,
			new Identifier("minecraft", "overworld"), // Effects
			0, // ambient light
			new DimensionType.MonsterSettings(
				false,
				false,
				UniformIntProvider.create(0, 7),
				7) // Monster spawn settings
		);
	}
}
