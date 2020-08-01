package me.spartann.foodplus.common.items.juicer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

import java.util.Optional;

public enum JuiceBottleFullness implements IStringSerializable {

    EMPTY(0, "0pc", "Empty"),
    ONE_QUARTER(1, "25pc", "Nearly Full"),
    ONE_HALF(2, "50pc", "Half Full"),
    THREE_QUARTERS(3, "75pc", "Mostly Full"),
    FULL(4, "100pc", "Full");

    @Override
    public String toString() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public float getPropertyOverrideValue() {
        return nbtID;
    }

    public JuiceBottleFullness decreaseFullnessByOneStep() {
        if (nbtID == 0) return this;
        for (JuiceBottleFullness fullness : JuiceBottleFullness.values()) {
            if (fullness.nbtID == nbtID - 1) return fullness;
        }
        return this;
    }

    public static JuiceBottleFullness fromNBT(CompoundNBT compoundNBT, String tagname) {
        byte fullnessID = 0;  // default in case of error
        if (compoundNBT != null && compoundNBT.contains(tagname)) {
            fullnessID = compoundNBT.getByte(tagname);
        }
        Optional<JuiceBottleFullness> fullness = getFullnessFromID(fullnessID);
        return fullness.orElse(FULL);
    }

    public void putIntoNBT(CompoundNBT compoundNBT, String tagname) {
        compoundNBT.putByte(tagname, nbtID);
    }

    private final byte nbtID;
    private final String name;
    private final String description;

    JuiceBottleFullness(int i_NBT_ID, String i_name, String i_description) {
        this.nbtID = (byte) i_NBT_ID;
        this.name = i_name;
        this.description = i_description;
    }

    private static Optional<JuiceBottleFullness> getFullnessFromID(byte ID) {
        for (JuiceBottleFullness fullness : JuiceBottleFullness.values()) {
            if (fullness.nbtID == ID) return Optional.of(fullness);
        }
        return Optional.empty();
    }

}
