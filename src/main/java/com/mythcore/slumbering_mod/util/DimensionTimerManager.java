package com.mythcore.slumbering_mod.util;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.mythcore.slumbering_mod.SlumberingMod.MOD_ID;

public class DimensionTimerManager {

	private static final Map<UUID, Boolean> playersInDimension = new HashMap<>();

	public static void onTick(ServerWorld world) {
		// Get the Overworld instance
		ServerWorld overworld = world.getServer().getWorld(World.OVERWORLD);
		if (overworld == null || !overworld.isDay()) return; // Exit if Overworld is null or not daytime

		// Iterate through tracked players
		playersInDimension.forEach((uuid, isInDimension) -> {
			if (!isInDimension) return;

			ServerPlayerEntity player = world.getServer().getPlayerManager().getPlayer(uuid);
			if (player == null || !player.getWorld().getRegistryKey().getValue().getPath().equals("hypnos")) {
				return;
			}

			// Get player's spawn position or fallback to Overworld spawn
			BlockPos spawnPos = player.getSpawnPointPosition();
			if (spawnPos == null || !overworld.isChunkLoaded(spawnPos)) {
				spawnPos = overworld.getSpawnPos();
			}

			try {
				// Ensure the chunk is loaded before teleporting
				overworld.getChunk(spawnPos.getX() >> 4, spawnPos.getZ() >> 4);

				// Teleport the player and restore inventory
				player.sendMessage(Text.literal("The day has come in the Overworld! Returning you there."), false);
				player.teleport(
					overworld,
					spawnPos.getX() + 0.5,
					spawnPos.getY() + 0.5,
					spawnPos.getZ() + 0.5,
					player.getYaw(),
					player.getPitch()
				);
				InventoryManager.restoreInventory(player); // Restore inventory

				// Stop tracking the player after teleporting
				stopTracking(player);
			} catch (Exception e) {
				System.err.println("Error teleporting player: " + e.getMessage());
				e.printStackTrace();
			}
		});

	}

	// Start tracking the player
	public static void startTracking(ServerPlayerEntity player) {
		playersInDimension.put(player.getUuid(), true);
	}

	// Stop tracking the player
	public static void stopTracking(ServerPlayerEntity player) {
		playersInDimension.put(player.getUuid(), false);
	}
}





//OLD TIMER CODE, DEPRECATED

	//private static final Map<UUID, Integer> playerTimers = new HashMap<>();
	// Called on every server tick
	/*public static void onTick(ServerWorld world) {
		playerTimers.forEach((uuid, ticks) -> {
			// Increment the timer for each player
			playerTimers.put(uuid, ticks + 1);
			ServerPlayerEntity player = world.getServer().getPlayerManager().getPlayer(uuid);
			ServerWorld overworld = player.getServer().getWorld(World.OVERWORLD);
			// Example: Do something after 200 ticks (10 seconds at 20 ticks/second)
			if (ticks == 200) {


				BlockPos spawnPos = player.getSpawnPointPosition();

				if (player != null) {
					overworld.getChunk(spawnPos.getX() >> 4, spawnPos.getZ() >> 4);
					player.sendMessage(Text.literal("You've been in the dimension for 10 seconds!"), false);
					player.teleport(overworld, spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
					InventoryManager.restoreInventory(player);
					//stopTimer(player);
				}
			}
		});
	}



	// Start tracking the player when they enter the dimension
	public static void startTimer(ServerPlayerEntity player) {
		playerTimers.put(player.getUuid(), 0); // Initialize the timer at 0
	}

	// Stop tracking the player when they leave the dimension
	public static void stopTimer(ServerPlayerEntity player) {
		playerTimers.remove(player.getUuid()); // Remove the player from the map
	}*/


