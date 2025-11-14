package net.succ.succsmod.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.succ.succsmod.SuccsMod;
import net.succ.succsmod.item.ModItems;
import net.succ.succsmod.item.custom.BraceletOfDisplacement;
import top.theillusivec4.curios.api.CuriosApi;

public record UseDisplacementPacket() implements CustomPacketPayload {

    public static final ResourceLocation ID =
            ResourceLocation.fromNamespaceAndPath(SuccsMod.MOD_ID, "use_displacement");

    public static final Type<UseDisplacementPacket> TYPE = new Type<>(ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, UseDisplacementPacket> CODEC =
            StreamCodec.of(
                    (buf, packet) -> {},
                    buf -> new UseDisplacementPacket()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(UseDisplacementPacket packet, IPayloadContext ctx) {
        ServerPlayer player = (ServerPlayer) ctx.player();
        if (player == null) return;

        ctx.enqueueWork(() -> {

            // 1) Check Curios slot first
            CuriosApi.getCuriosInventory(player).ifPresent(inv -> {
                inv.findFirstCurio(ModItems.BRACELET_OF_DISPLACEMENT.get()).ifPresent(curio -> {
                    ItemStack stack = curio.stack();

                    ((BraceletOfDisplacement) stack.getItem()).use(player.level(), player, InteractionHand.MAIN_HAND);
                    return;
                });
            });

            // 2) Fallback to normal inventory
            player.getInventory().items.stream()
                    .filter(stack -> stack.is(ModItems.BRACELET_OF_DISPLACEMENT.get()))
                    .findFirst()
                    .ifPresent(stack -> {
                        ((BraceletOfDisplacement) stack.getItem()).use(player.level(), player, InteractionHand.MAIN_HAND);
                    });
        });
    }

}
