package net.succ.succsmod.client;

import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {

    // Keybinding for Bracelet of Displacement
    public static final KeyMapping BRACELET_DISPLACEMENT = new KeyMapping(
            "key.succsessentials.bracelet_displacement",   // translation key
            GLFW.GLFW_KEY_R,                         // default key
            "key.categories.succsessentials"                // category
    );

    // Keybinding for Bracelet of Replacement
    public static final KeyMapping BRACELET_REPLACEMENT = new KeyMapping(
            "key.succsessentials.bracelet_replacement",
            GLFW.GLFW_KEY_G,
            "key.categories.succsessentials"
    );

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(BRACELET_DISPLACEMENT);
        event.register(BRACELET_REPLACEMENT);
    }
}
