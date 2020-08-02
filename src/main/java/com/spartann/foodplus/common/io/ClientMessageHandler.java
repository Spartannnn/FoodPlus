package com.spartann.foodplus.common.io;

import com.spartann.foodplus.client.particles.data.ScaledColoredParticleData;
import com.spartann.foodplus.common.registries.ModParticleTypes;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ClientMessageHandler {

    public static void onMessageReceived(final ParticleMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        if (sideReceived != LogicalSide.CLIENT) {
            return;
        }

        Optional<ClientWorld> clientWorld = LogicalSidedProvider.CLIENTWORLD.get(sideReceived);
        if (!clientWorld.isPresent()) {
            return;
        }
        ctx.enqueueWork(() -> processMessage(clientWorld.get(), message));
    }

    private static void processMessage(ClientWorld clientWorld, ParticleMessage message) {
        Vec3d look = message.getPosition();
        Vec3d startpos = message.getDestination();
        clientWorld.addParticle(new ScaledColoredParticleData(ModParticleTypes.LIGHTNING, true, 0xAFC6FF, 1), startpos.x, startpos.y, startpos.z, look.x, look.y, look.z);
    }


}
