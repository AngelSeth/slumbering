package com.mythcore.slumbering_mod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

public class SlumberingBedBlock extends BedBlock {

	public SlumberingBedBlock(DyeColor color, Settings settings) {
		super(color, settings);
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, BlockHitResult hitResult) {
		if (world.isClient) {
			return ActionResult.CONSUME;
		} else {
			if (state.get(PART) != BedPart.HEAD) {
				pos = pos.offset((Direction)state.get(FACING));
				state = world.getBlockState(pos);
				if (!state.isOf(this)) {
					return ActionResult.CONSUME;
				}
			}

			if (!isBedWorking(world)) {
				world.removeBlock(pos, false);
				BlockPos blockPos = pos.offset(((Direction)state.get(FACING)).getOpposite());
				if (world.getBlockState(blockPos).isOf(this)) {
					world.removeBlock(blockPos, false);
				}

				Vec3d vec3d = pos.ofCenter();
				world.createExplosion((Entity)null, world.getDamageSources().badRespawnPoint(vec3d), (ExplosionBehavior)null, vec3d, 5.0F, true, World.ExplosionSourceType.BLOCK);
				return ActionResult.SUCCESS;
			} else {
				entity.trySleep(pos).ifLeft((sleepFailureReason) -> {
					if (sleepFailureReason.toText() != null) {
						entity.sendMessage(sleepFailureReason.toText(), true);
					}

				});
				return ActionResult.SUCCESS;
			}
		}
	}


}
