package com.mythcore.slumbering_mod.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {
	private static final Map<UUID, NbtCompound> savedInventories = new HashMap<>();

	// Save the player's inventory
	public static void saveInventory(ServerPlayerEntity player) {
		NbtList inventoryNbtList = new NbtList();
		player.getInventory().writeNbt(inventoryNbtList); // Serialize the inventory

		NbtCompound nbtCompound = new NbtCompound();
		nbtCompound.put("Inventory", inventoryNbtList); // Wrap the NbtList in a compound
		savedInventories.put(player.getUuid(), nbtCompound); // Store the compound
	}

	// Restore the player's inventory
	public static void restoreInventory(ServerPlayerEntity player) {
		UUID playerId = player.getUuid();
		if (savedInventories.containsKey(playerId)) {
			NbtCompound nbtCompound = savedInventories.get(playerId); // Retrieve the compound
			NbtList inventoryNbtList = nbtCompound.getList("Inventory", 10); // Extract the NbtList
			player.getInventory().readNbt(inventoryNbtList); // Deserialize the inventory
			savedInventories.remove(playerId); // Clear saved data after restoring
		} else {
			player.getInventory().clear(); // Clear inventory if no saved data exists
		}
	}

	// Clear the player's inventory
	public static void clearInventory(ServerPlayerEntity player) {
		player.getInventory().clear(); // Empty the inventory
	}
}
