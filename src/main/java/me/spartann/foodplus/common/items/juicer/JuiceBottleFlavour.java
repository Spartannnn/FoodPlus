package me.spartann.foodplus.common.items.juicer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

import java.awt.*;
import java.util.Optional;

public enum JuiceBottleFlavour implements IStringSerializable {

    PEAR(0, "pear", Color.GREEN),
    CHERRY(1, "cherry", Color.RED),
    MANGO(2, "mango", new Color(255, 205, 72));

    @Override
    public String toString()
    {
        return this.name;
    }

    public String getName() {return this.name;}

    public Color getRenderColour() {return renderColour;}

    public static JuiceBottleFlavour fromNBT(CompoundNBT compoundNBT, String tagname)
    {
        byte flavourID = 0;
        if (compoundNBT != null && compoundNBT.contains(tagname)) {
            flavourID = compoundNBT.getByte(tagname);
        }
        Optional<JuiceBottleFlavour> flavour = getFlavourFromID(flavourID);
        return flavour.orElse(PEAR);
    }

    public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
        compoundNBT.putByte(tagname, nbtID);
    }

    private final byte nbtID;
    private final String name;
    private final Color renderColour;

    JuiceBottleFlavour(int i_NBT_ID, String i_name, Color i_renderColour)
    {
        this.nbtID = (byte)i_NBT_ID;
        this.name = i_name;
        this.renderColour = i_renderColour;
    }

    private static Optional<JuiceBottleFlavour> getFlavourFromID(byte ID) {
        for (JuiceBottleFlavour flavour : JuiceBottleFlavour.values()) {
            if (flavour.nbtID == ID) return Optional.of(flavour);
        }
        return Optional.empty();
    }

    public static JuiceBottleFlavour getFlavourByStack(ItemStack stack) {
        CompoundNBT tag = stack.getTag();
        byte flavourID = 0;
        if(tag != null && tag.contains(JuiceBottleItem.NBT_TAG_NAME_FULLNESS))
            flavourID = tag.getByte(JuiceBottleItem.NBT_TAG_NAME_FULLNESS);
        Optional<JuiceBottleFlavour> flavour = getFlavourFromID(flavourID);
        return flavour.orElse(PEAR);
    }

    public static JuiceBottleFlavour byName(String name) {
        for(JuiceBottleFlavour flavours : values())
            if(flavours.name.equals(name))
                return flavours;

        return PEAR;
    }
}
