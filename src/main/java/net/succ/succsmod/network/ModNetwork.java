package net.succ.succsmod.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.succ.succsmod.SuccsMod;

@EventBusSubscriber(modid = SuccsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetwork {

    @SubscribeEvent
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        event.registrar(SuccsMod.MOD_ID)
                .playBidirectional(
                        UseDisplacementPacket.TYPE,
                        UseDisplacementPacket.CODEC,
                        UseDisplacementPacket::handle
                )
                .playBidirectional(
                        UseReplacementPacket.TYPE,
                        UseReplacementPacket.CODEC,
                        UseReplacementPacket::handle
                )
                .playToClient(
                        SyncCooldownPacket.TYPE,
                        SyncCooldownPacket.CODEC,
                        SyncCooldownPacket::handle
                );
    }

    // helper method for sending packets to the client
    public static void sendToClient(CustomPacketPayload payload, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    // helper for syncing cooldowns
    public static void sendCooldownToClient(ServerPlayer player, Item item, int ticks) {
        sendToClient(
                new SyncCooldownPacket(
                        net.minecraft.core.registries.BuiltInRegistries.ITEM
                                .getKey(item)
                                .toString(),
                        ticks
                ),
                player
        );
    }
}

