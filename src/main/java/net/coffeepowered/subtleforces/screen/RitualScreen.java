package net.coffeepowered.subtleforces.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.coffeepowered.subtleforces.rituals.HealSelfPayload;
import net.minecraft.client.gui.components.Button;
import net.neoforged.neoforge.network.PacketDistributor;

public class RitualScreen extends Screen {
    public RitualScreen() {
        super(Component.literal("Ritual"));

    }


    @Override
    protected void init(){
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Confirm ritual (close screen)
        this.addRenderableWidget(
                Button.builder(
                        Component.literal("Confirm Ritual"),
                        b -> this.onClose()
                ).bounds(centerX - 50, centerY - 10, 100, 20).build()
        );

        // Heal self button
        this.addRenderableWidget(
                Button.builder(
                        Component.literal("Heal"),
                        b -> {
                            PacketDistributor.sendToServer(
                                    new HealSelfPayload()
                            );
                        }
                ).bounds(centerX - 50, centerY + 20, 100, 20).build()
        );
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
