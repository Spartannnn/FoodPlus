package com.spartann.foodplus.common.io;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;

public class ParticleMessage {

    private Vec3d position;
    private Vec3d destination;
    private boolean isMessageValid;

    public ParticleMessage(Vec3d position, Vec3d destination) {
        this.position = position;
        this.destination = destination;
        this.isMessageValid = true;
    }

    public ParticleMessage() {
        this.isMessageValid = false;
    }

    public Vec3d getPosition() {
        return position;
    }

    public Vec3d getDestination() {
        return destination;
    }

    public boolean isMessageValid() {
        return isMessageValid;
    }

    public static ParticleMessage decode(PacketBuffer buf) {
        ParticleMessage retval = new ParticleMessage();
        try {
            double xStart = buf.readDouble();
            double yStart = buf.readDouble();
            double zStart = buf.readDouble();
            retval.position = new Vec3d(xStart, yStart, zStart);
            double xDest = buf.readDouble();
            double yDest = buf.readDouble();
            double zDest = buf.readDouble();
            retval.destination = new Vec3d(xDest, yDest, zDest);

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            return retval;
        }
        retval.isMessageValid = true;
        return retval;
    }

    public void encode(PacketBuffer buf) {
        buf.writeDouble(position.x);
        buf.writeDouble(position.y);
        buf.writeDouble(position.z);
        buf.writeDouble(destination.x);
        buf.writeDouble(destination.y);
        buf.writeDouble(destination.z);

    }

}
