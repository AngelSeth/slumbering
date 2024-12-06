package com.mythcore.slumbering_mod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class LimboBiome {

	public static Biome create(){
		SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
		//GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();

		return new Biome.Builder()
			.temperature(1.0F)
			.downfall(0.0F)
			.hasPrecipitation(false)
			.effects(
				new BiomeEffects.Builder()
					.skyColor(3946809) // Custom sky color
					.fogColor(11775405) // Fog color
					.waterColor(3552565) // Water color
					.waterFogColor(1512982) // Water fog color
					.grassColor(5723991) // Grass color
					.foliageColor(5920856) // Foliage color
					.loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP) // Ambient sound
					.moodSound(new BiomeMoodSound(
						SoundEvents.AMBIENT_CAVE, // Mood sound
						0, // Tick delay
						0, // Block search extent
						0.0F // Offset
					)).build()
			)
			.spawnSettings(spawnSettings.build())
			//.generationSettings(generationSettings.build())
			.build();
	}
}
