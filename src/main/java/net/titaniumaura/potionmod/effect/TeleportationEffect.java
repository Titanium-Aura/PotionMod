package net.titaniumaura.potionmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.InstantenousMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


public class TeleportationEffect extends InstantenousMobEffect {
    public TeleportationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        teleport(livingEntity, amplifier);
        return true;
    }


    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {
        teleport(livingEntity, amplifier);
    }


    private void teleport (LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();

        if (!level.isClientSide) {
            for (int i = 0; i < 16; ++i) {
                double d0 = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - (double) 0.5F) * ((double) 60.0F * (amplifier + 1));
                double d1 = Mth.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8), (double) level.getMinBuildHeight(), (double) (level.getMinBuildHeight() + ((ServerLevel) level).getLogicalHeight() - 1));
                double d2 = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - (double) 0.5F) * ((double) 60.0F * (amplifier + 1));
                if (livingEntity.isPassenger()) {
                    livingEntity.stopRiding();
                }
                Vec3 vec3 = livingEntity.position();
                if (livingEntity.randomTeleport(d0, d1, d2, true)) {
                    level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
                    SoundSource soundsource = SoundSource.PLAYERS;
                    SoundEvent soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;

                    level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundevent, soundsource);
                    livingEntity.resetFallDistance();
                    break;
                }
            }
        }


    }

}


