package net.succ.succsmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.item.custom.BraceletOfReplacement;
import top.theillusivec4.curios.api.CuriosApi;

public record UseReplacementPacket() implements CustomPacketPayload {

    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "use_replacement");

    public static final Type<UseReplacementPacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, UseReplacementPacket> CODEC =
            StreamCodec.of(
                    (buf, packet) -> {},
                    buf -> new UseReplacementPacket()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(UseReplacementPacket packet, IPayloadContext ctx) {
        ServerPlayer player = (ServerPlayer) ctx.player();
        if (player == null) return;

        ctx.enqueueWork(() -> {

            // 1) Check Curios slots first
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
                inv.findFirstCurio(ModItems.BRACELET_OF_REPLACEMENT.get()).ifPresent(curio -> {
                    ItemStack stack = curio.stack();

                    ((BraceletOfReplacement) stack.getItem()).activate(stack, player);
                    return; // done → do NOT fallback to inventory
                });
            });

            // 2) If not in Curios, check normal inventory
            player.getInventory().items.stream()
                    .filter(stack -> stack.is(ModItems.BRACELET_OF_REPLACEMENT.get()))
                    .findFirst()
                    .ifPresent(stack -> {
                        ((BraceletOfReplacement) stack.getItem()).activate(stack, player);
                    });
        });
    }
}
