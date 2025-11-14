package net.succ.succsmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SyncCooldownPacket(String itemId, int ticks) implements CustomPacketPayload {

    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath("succsessentials", "sync_cooldown");

    public static final Type<SyncCooldownPacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncCooldownPacket> CODEC =
            StreamCodec.of(
                    (buf, p) -> {
                        buf.writeUtf(p.itemId);
                        buf.writeInt(p.ticks);
                    },
                    buf -> new SyncCooldownPacket(buf.readUtf(), buf.readInt())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SyncCooldownPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            var player = ctx.player();

            if (player == null) return;

            player.getCooldowns().addCooldown(
                    net.minecraft.core.registries.BuiltInRegistries.ITEM
                            .get(ResourceLocation.parse(packet.itemId)),
                    packet.ticks
            );
        });
    }
}
