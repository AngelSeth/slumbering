package com.mythcore.slumbering_mod.mixin;

import com.mojang.datafixers.util.Either;
import com.mythcore.slumbering_mod.util.DimensionTimerManager;
import com.mythcore.slumbering_mod.util.InventoryManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mythcore.slumbering_mod.world.dimension.ModDimensions.WORLD_KEY;

@Mixin(PlayerEntity.class)
public class PlayerEntitySleepMixin {
	@Inject(method = "trySleep", at = @At("HEAD"), cancellable = true)
	public void onPlayerSleep(BlockPos pos, CallbackInfoReturnable<Either<PlayerEntity.SleepFailureReason, Unit>> cir) {
		if (!((PlayerEntity) (Object) this).getWorld().isClient) {
			ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

			// Perform teleportation
			teleportToSlumber(player);

			// Optionally, cancel the sleeping process
			cir.setReturnValue(Either.right(Unit.INSTANCE));

		}
	}

	private void teleportToSlumber(ServerPlayerEntity player) {
		World slumber = player.getServer().getWorld(WORLD_KEY);
		player.teleport((ServerWorld) slumber, slumber.getSpawnPos().getX(), slumber.getSpawnPos().getY(), slumber.getSpawnPos().getZ(), 0, 0);
		InventoryManager.saveInventory(player);
		InventoryManager.clearInventory(player);
		//DimensionTimerManager.startTimer(player);
		DimensionTimerManager.startTracking(player);
	}


}
