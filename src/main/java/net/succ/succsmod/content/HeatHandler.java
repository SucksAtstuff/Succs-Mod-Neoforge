package net.succ.succsmod.content;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.effect.ModEffects;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.worldgen.biome.ModBiomes;

@EventBusSubscriber(modid = SuccsMod.MOD_ID) // GAME bus (aka Forge bus)
public class HeatHandler {
    // NBT key (namespaced so we don't collide with others)
    private static final String HEAT_KEY = "succsessentials:heat_ticks";

    // Tuning knobs
    private static final int DAMAGE_INTERVAL_TICKS = 40;    // 2s chip damage
    private static final int IGNITE_AFTER_TICKS    = 15 * 20; // 15s to catch fire
    private static final int IGNITE_DURATION_SEC   = 5;     // burn time
    private static final int DECAY_OUTSIDE         = 4;     // cool faster outside/protected
    private static final int DECAY_INSIDE_COOL     = 2;     // cool a bit if protected inside
    private static final int TRUE_FIRE_DURATION_TICKS = 5 * 20; // 5s burn
    private static final int TRUE_FIRE_AMPLIFIER      = 0;
    private static final int DECAY_CHILLING = 8; // strong cooldown while chilled

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post e) {
        Player player = e.getEntity();
        Level level = player.level();
        if (level.isClientSide) return;
        if (player.isCreative() || player.isSpectator()) return;

        // only care inside Solarblight
        if (!level.getBiome(player.blockPosition()).is(ModBiomes.SOLARBLIGHT_EXPANSE)) {
            decayHeat(player, DECAY_OUTSIDE);
            return;
        }

        // ✅ one unified protection gate
        if (isHeatProtected(player)) {
            // stronger cooldown if they’re actually CHILLING
            int decay = player.hasEffect(ModEffects.CHILLING_EFFECT) ? DECAY_CHILLING : DECAY_INSIDE_COOL;
            decayHeat(player, decay);
            // optional: clear your custom burn while protected
            if (player.hasEffect(ModEffects.TRUE_FIRE_EFFECT)) {
                player.removeEffect(ModEffects.TRUE_FIRE_EFFECT);
            }
            return;
        }

        // optional: don’t stack with water/burning
        if (player.isInWaterRainOrBubble() || player.isOnFire()) {
            return;
        }

        // accumulate heat
        CompoundTag tag = player.getPersistentData();
        int heat = tag.getInt(HEAT_KEY) + 1;
        tag.putInt(HEAT_KEY, heat);

        // periodic chip damage
        if (heat % DAMAGE_INTERVAL_TICKS == 0) {
            player.hurt(player.damageSources().hotFloor(), 2.0F);
        }

        // ignite after sustained exposure → TRUE_FIRE effect
        if (heat >= IGNITE_AFTER_TICKS && !player.hasEffect(ModEffects.TRUE_FIRE_EFFECT)) {
            player.addEffect(new MobEffectInstance(
                    ModEffects.TRUE_FIRE_EFFECT,
                    TRUE_FIRE_DURATION_TICKS,
                    TRUE_FIRE_AMPLIFIER,
                    false, true
            ));
        }
    }


    private static void decayHeat(Player p, int amount) {
        CompoundTag tag = p.getPersistentData();
        int heat = tag.getInt(HEAT_KEY);
        if (heat > 0) {
            heat = Math.max(0, heat - amount);
            tag.putInt(HEAT_KEY, heat);
        }
    }

    private static boolean isHeatProtected(Player p) {
        // if your registry uses DeferredHolder/RegistryObject, use .get():
        // return p.hasEffect(ModEffects.CHILLING_EFFECT.get()) || wearingCoolingSet(p);
        return p.hasEffect(ModEffects.CHILLING_EFFECT) || wearingCoolingSet(p);
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
