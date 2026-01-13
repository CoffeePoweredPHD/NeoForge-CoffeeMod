package net.coffeepowered.subtleforces.event;

import net.coffeepowered.subtleforces.SubtleForces;
import net.coffeepowered.subtleforces.block.ModBlocks;
import net.coffeepowered.subtleforces.block.custom.RitualFire;
import net.coffeepowered.subtleforces.screen.OpenRitualScreenPayload;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = SubtleForces.MOD_ID)
public class CommonEvents {

    private static final java.util.Set<java.util.UUID> ACTIVE = new java.util.HashSet<>();


    //On jump, heal. Test
    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            //entity.heal(1);
            //entity.kill();
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();


        // Server-side only
        if (level.isClientSide) return;

        // Must be crouching
        if (!player.isCrouching()) {
            ACTIVE.remove(player.getUUID());
            return;
        }


        // Ray trace from player's eyes
        HitResult hit = player.pick(5.0D, 0.0F, false);

        if (hit.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult blockHit = (BlockHitResult) hit;
        BlockPos pos = blockHit.getBlockPos();
        BlockState state = level.getBlockState(pos);



        //if (level.getBlockState(blockHit.getBlockPos()).getBlock() instanceof CampfireBlock campfire //if its a campfire
        //        && !(level.getBlockState(blockHit.getBlockPos()).getBlock() instanceof RitualFire)  //and not a ritualfire
        //        && level.getBlockState(blockHit.getBlockPos()).getValue(CampfireBlock.LIT)) {  //and is actually on fire

        if (state.getBlock() instanceof CampfireBlock //if its a campfire
                && !(state.getBlock() instanceof RitualFire) //and not a ritualfire
                && state.getValue(CampfireBlock.LIT)) { //and is actually on fire


            Direction facing = state.getValue(CampfireBlock.FACING);
            boolean lit = state.getValue(CampfireBlock.LIT);

            BlockState ritualState = ModBlocks.RITUAL_FIRE.get()
                    .defaultBlockState()
                    .setValue(CampfireBlock.FACING, facing)
                    .setValue(CampfireBlock.LIT, lit);
            level.setBlock(pos, ritualState, Block.UPDATE_ALL);
            /*
            if (player instanceof ServerPlayer serverPlayer) {
                PacketDistributor.sendToPlayer(
                        serverPlayer,
                        new OpenRitualScreenPayload()



                );*/


            }
        }
    }


