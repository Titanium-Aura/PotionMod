package net.titaniumaura.potionmod.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.titaniumaura.potionmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class ElevationEffect extends InstantenousMobEffect {
    public ElevationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Vec3 pos = livingEntity.position();
        livingEntity.teleportTo(pos.x, pos.y, pos.z);
        return true;
    }


    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
        Vec3 pos = getNearestValidPosition(livingEntity.level(), livingEntity.position(), 50 * (amplifier + 1));
        livingEntity.teleportTo(pos.x, pos.y, pos.z);

    }


    private Vec3 getNearestValidPosition(Level level, Vec3 pos, int teleportAmount) {
        int airBlocks = 0;
        boolean fullBlockCheck = false;
        for (int i = 0; i < teleportAmount; i++) {
            if (fullBlockCheck == true) {
                if (level.getBlockState(new BlockPos((int) pos.x, (int) pos.y + i, (int) pos.z)).is(Blocks.AIR)) {
                    airBlocks += 1;
                } else {
                    airBlocks = 0;
                }
                if (airBlocks == 2) {
                    return new Vec3(pos.x, pos.y + i, pos.z);
                }

            } else {
                if (!level.getBlockState(new BlockPos((int) pos.x, (int) pos.y + i, (int) pos.z)).is(Blocks.AIR)) {
                    fullBlockCheck = true;
                }
            }

        }
        return new Vec3(pos.x, pos.y + teleportAmount, pos.z);
    }
}
