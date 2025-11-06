package net.succ.succsmod.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.succ.succsmod.SuccsMod;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SuccsMod.MOD_ID);

    public static final Supplier<SoundEvent> FUNKY_MUSIC = registerSoundEvent("funky_music");
    public static final ResourceKey<JukeboxSong> FUNKY_MUSIC_KEY = createSong("funky_music");

    public static final Supplier<SoundEvent> BASS_MUSIC = registerSoundEvent("bass_music");
    public static final ResourceKey<JukeboxSong> BASS_MUSIC_KEY = createSong("bass_music");

    public static final Supplier<SoundEvent> CLEAR_MUD = registerSoundEvent("clear_mud");
    public static final ResourceKey<JukeboxSong> CLEAR_MUD_KEY = createSong("clear_mud");

    public static final Supplier<SoundEvent> ORB_SUMMON = registerSoundEvent("orb_summon");
    public static final Supplier<SoundEvent> TJ_HURT = registerSoundEvent("tj_hurt");
    public static final Supplier<SoundEvent> TJ_DEATH = registerSoundEvent("tj_death");


    private static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name));
    }

    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }


    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
