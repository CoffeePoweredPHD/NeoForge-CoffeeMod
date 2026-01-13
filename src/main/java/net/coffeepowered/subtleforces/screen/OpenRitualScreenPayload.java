package net.coffeepowered.subtleforces.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.minecraft.resources.ResourceLocation;


public record OpenRitualScreenPayload() implements CustomPacketPayload {

    public static final Type<OpenRitualScreenPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("subtleforces", "open_ritual"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenRitualScreenPayload> CODEC =
            StreamCodec.unit(new OpenRitualScreenPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static OpenRitualScreenPayload decode(FriendlyByteBuf buf) {
        return new OpenRitualScreenPayload();
    }

    public void encode(FriendlyByteBuf buf) {
        // no data
    }

    public static void handle(OpenRitualScreenPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft.getInstance()
                    .setScreen(new net.coffeepowered.subtleforces.screen.RitualScreen());
        });
    }
}