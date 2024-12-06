package com.mythcore.slumbering_mod.world.biome;

import com.mythcore.slumbering_mod.SlumberingMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

public class ModBiomes {

	public static final RegistryKey<Biome> LIMBO_BIOME = RegistryKey.of(RegistryKeys.BIOME,
		new Identifier(SlumberingMod.MOD_ID, "limbo"));

	public static void registerBiomes() {
		SlumberingMod.LOGGER.info("Registering Biomes for " + SlumberingMod.MOD_ID);
	}
}
