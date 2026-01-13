package net.coffeepowered.subtleforces.rituals;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record HealSelfPayload() implements CustomPacketPayload {

    public static final Type<HealSelfPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("subtleforces",  "heal_self"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HealSelfPayload> CODEC =
            StreamCodec.unit(new HealSelfPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(HealSelfPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer player) {
                player.heal(6.0F);
            }
        });
    }
}

