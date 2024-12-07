package com.mythcore.slumbering_mod.util;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlayerDeathEvent {
	public static void register() {
		// Listen for player respawn events
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			if (!(newPlayer instanceof ServerPlayerEntity serverPlayer)) return;

			// Check if the player was in the custom dimension
			if (DimensionTimerManager.isPlayerInDimension(serverPlayer)) {
				// Restore the inventory
				InventoryManager.restoreInventory(serverPlayer);

				// Stop tracking the player in the dimension
				DimensionTimerManager.stopTracking(serverPlayer);
			}
		});
	}
}
