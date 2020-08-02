package com.spartann.foodplus.common.io;

import com.spartann.foodplus.FoodPlusMod;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class NetworkMessageHandler {

    public static void onMessageReceived(final ParticleMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        if (sideReceived != LogicalSide.SERVER) {
            return;
        }

        ctx.enqueueWork(() -> processMessage(message));
    }

    private static void processMessage(ParticleMessage message) {
        ParticleMessage response = new ParticleMessage(message.getPosition(), message.getDestination());
        FoodPlusMod.CHANNEL_INSTANCE.send(PacketDistributor.PLAYER.noArg(), response);
    }


}
