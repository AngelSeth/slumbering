package com.mythcore.slumbering_mod;


import com.mythcore.slumbering_mod.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.test.TestServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mythcore.slumbering_mod.world.dimension.ModDimensions.WORLD_KEY;

public class SlumberingMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Slumbering");
	public static final String MOD_ID = "slumbering_mod";

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", MOD_ID);
		ModDimensions.registerDimensions();
	}






	/*public void readyServer(MinecraftServer server) {
		if (server instanceof TestServer) return; // Game Test server does not support custom dimensions.

		ServerWorld overworld = server.getWorld(World.OVERWORLD);
		ServerWorld targetWorld = server.getWorld(WORLD_KEY);

		if (targetWorld == null) {
			throw new AssertionError("Test world somehow doesn't exist.");
		}

		CowEntity cow = EntityType.COW.create(overworld);

		if (!cow.getWorld().getRegistryKey().equals(World.OVERWORLD)) {
			throw new AssertionError("Cow was spawned but isn't in the overworld.");
		}

		var target = new TeleportTarget(Vec3d.ZERO, new Vec3d(1, 1, 1), 45f, 60f);
		CowEntity teleportedEntity = FabricDimensions.teleport(cow, targetWorld, target);

		if (teleportedEntity == null || !teleportedEntity.getWorld().getRegistryKey().equals(WORLD_KEY)) {
			throw new AssertionError("Cow was not teleported correctly.");
		}

		if (!teleportedEntity.getPos().equals(target.position)) {
			throw new AssertionError("Cow was moved to different world, but not to the correct location.");
		}
	}*/
}
