package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class ElevationEffect extends InstantenousMobEffect {
    public ElevationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        teleportToValidPosition(livingEntity, amplifier);
        return true;
    }


    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
        teleportToValidPosition(livingEntity, amplifier);
    }


    private void teleportToValidPosition(LivingEntity livingEntity, int amplifier) {
        Vec3 pos = getNearestValidPosition(livingEntity.level(), livingEntity.getOnPos(), 50 * (amplifier + 1));
        livingEntity.teleportTo(pos.x + 0.5, pos.y - 1, pos.z + 0.5);
    }


    private Vec3 getNearestValidPosition(Level level, BlockPos pos, int teleportAmount) {
        int airBlocks = 0;
        boolean foundBlock = false;

        for (int i = 1; i < teleportAmount; i++) {
            if (foundBlock) {
                if (level.getBlockState(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ())).is(Blocks.AIR)) {
                    airBlocks += 1;
                } else {
                    airBlocks = 0;
                }
                if (airBlocks == 2) {
                    return new Vec3(pos.getX(), pos.getY() + i, pos.getZ());
                }

            } else {
                if (!level.getBlockState(new BlockPos(new BlockPos(pos.getX(), pos.getY() + i, pos.getZ()))).is(Blocks.AIR)) {
                    foundBlock = true;
                }
            }
        }
        return new Vec3(pos.getX(), pos.getY() + teleportAmount, pos.getZ());
    }
}
