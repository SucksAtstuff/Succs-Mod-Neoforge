package net.succ.succsmod.content;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.worldgen.biome.ModBiomes;

@EventBusSubscriber(modid = SuccsMod.MOD_ID)
public class SolarFlareHandler {
    private static final int   CHECK_EVERY_TICKS = 20;    // once per second
    private static final float CHANCE_PER_CHECK  = 0.06f; // ~6%/sec when conditions met
    private static final double RADIUS           = 4.5;   // AoE radius
    private static final float DAMAGE            = 2.0F;  // 1 heart

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post e) {
        Player player = e.getEntity();
        Level level = player.level();
        if (level.isClientSide) return;
        if (level.getGameTime() % CHECK_EVERY_TICKS != 0) return;

        // only in Solarblight
        Holder<Biome> biome = level.getBiome(player.blockPosition());
        if (!biome.is(ModBiomes.SOLARBLIGHT_EXPANSE)) return;

        // solar vibe: day, sky visible, no rain/thunder
        if (!level.isDay() || !level.canSeeSky(player.blockPosition()) || level.isRaining() || level.isThundering())
            return;

        if (level.getRandom().nextFloat() >= CHANCE_PER_CHECK) return;

        // pick a nearby surface spot
        RandomSource r = level.getRandom();
        int dx = Mth.nextInt(r, -12, 12);
        int dz = Mth.nextInt(r, -12, 12);
        BlockPos surface = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, player.blockPosition().offset(dx, 0, dz));

        // avoid water hits
        if (!level.getFluidState(surface).isEmpty() && level.getFluidState(surface).is(Fluids.WATER)) return;

        // 70% damaging, 30% cosmetic
        boolean damaging = r.nextFloat() < 0.70f;

        if (!(level instanceof ServerLevel s)) return;

        // visuals (server auto-broadcasts to nearby clients)
        s.playSound(null, surface, SoundEvents.BLAZE_SHOOT, SoundSource.WEATHER, 1.2f, 0.8f);
        s.sendParticles(ParticleTypes.ASH, surface.getX() + 0.5, surface.getY() + 1.2, surface.getZ() + 0.5,
                90, 2.2, 0.6, 2.2, 0.01);
        s.sendParticles(ParticleTypes.SMALL_FLAME, surface.getX() + 0.5, surface.getY() + 1.0, surface.getZ() + 0.5,
                60, 1.6, 0.4, 1.6, 0.02);

        if (!damaging) return;

        // AoE heat burst (use your TRUE_FIRE effect, like HeatHandler)
        AABB box = new AABB(surface).inflate(RADIUS, 2.0, RADIUS);
        List<LivingEntity> targets = s.getEntitiesOfClass(LivingEntity.class, box);
        for (LivingEntity le : targets) {
            if (isHeatProtected(le)) continue;

            le.hurt(s.damageSources().hotFloor(), DAMAGE);

            if (!le.hasEffect(ModEffects.TRUE_FIRE_EFFECT)) {
                le.addEffect(new MobEffectInstance(
                        ModEffects.TRUE_FIRE_EFFECT,
                        5 * 20, // 5s burn; change to your constant if you prefer
                        0,
                        false,
                        true
                ));
            }
        }
    }

    // === helpers ===

    private static boolean isHeatProtected(LivingEntity e) {
        // Full cooling armor set for players
        if (e instanceof Player p) {
            return wearingCoolingSet(p);
        }
        return false;
    }

    private static boolean wearingCoolingSet(Player p) {
        ItemStack head  = p.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = p.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs  = p.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet  = p.getItemBySlot(EquipmentSlot.FEET);

        boolean h = !head.isEmpty(); // not used, but kept for syntax consistency
        boolean c = !chest.isEmpty() && chest.is(ModItems.BIKINI_TOP.get());
        boolean l = !legs.isEmpty()  && legs.is(ModItems.SWIM_TRUNKS.get());
        boolean f = !feet.isEmpty(); // not used, but kept for syntax consistency

        return c && l;
    }
}
