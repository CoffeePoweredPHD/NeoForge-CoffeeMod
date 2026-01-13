package net.coffeepowered.subtleforces.network;

import net.coffeepowered.subtleforces.rituals.HealSelfPayload;
import net.coffeepowered.subtleforces.screen.OpenRitualScreenPayload;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;


public class ModNetworking {


    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("subtleforces")
                .playToClient(
                        OpenRitualScreenPayload.TYPE,
                        OpenRitualScreenPayload.CODEC,
                        OpenRitualScreenPayload::handle
                );
        event.registrar("subtleforces")
                .playToServer(
                        HealSelfPayload.TYPE,
                        HealSelfPayload.CODEC,
                        HealSelfPayload::handle
                );
    }
}