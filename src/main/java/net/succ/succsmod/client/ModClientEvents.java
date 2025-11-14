package net.succ.succsmod.client;

import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.succ.succsmod.network.UseDisplacementPacket;
import net.succ.succsmod.network.UseReplacementPacket;

public class ModClientEvents {

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Displacement
        if (ModKeyBindings.BRACELET_DISPLACEMENT.consumeClick()) {
            mc.getConnection().send(new UseDisplacementPacket());
        }

        // Replacement
        if (ModKeyBindings.BRACELET_REPLACEMENT.consumeClick()) {
            mc.getConnection().send(new UseReplacementPacket());
        }
    }
}
