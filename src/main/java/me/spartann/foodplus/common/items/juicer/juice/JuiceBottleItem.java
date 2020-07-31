package me.spartann.foodplus.common.items.juicer.juice;

import me.spartann.foodplus.common.registries.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class JuiceBottleItem extends Item {

    public static final String NBT_TAG_NAME_FLAVOUR = "colour";
    public static final String NBT_TAG_NAME_FULLNESS = "fullness";

    public JuiceBottleItem(Properties properties) {
        super(properties);
        this.addPropertyOverride(new ResourceLocation("fullness"), JuiceBottleItem::getFullnessPropertyOverride);
    }

    public static JuiceBottleFlavour getFlavour(ItemStack stack) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        return JuiceBottleFlavour.fromNBT(compoundNBT, NBT_TAG_NAME_FLAVOUR);
    }

    public static JuiceBottleFullness getFullness(ItemStack stack) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        return JuiceBottleFullness.fromNBT(compoundNBT, NBT_TAG_NAME_FULLNESS);
    }

    private static float getFullnessPropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
        JuiceBottleFullness enumBottleFullness = getFullness(itemStack);
        return enumBottleFullness.getPropertyOverrideValue();
    }

    public static void setFlavour(ItemStack stack, JuiceBottleFlavour enumBottleFlavour) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        enumBottleFlavour.putIntoNBT(compoundNBT, NBT_TAG_NAME_FLAVOUR);
    }

    public static void setFullness(ItemStack stack, JuiceBottleFullness enumBottleFullness) {
        CompoundNBT compoundNBT = stack.getOrCreateTag();
        enumBottleFullness.putIntoNBT(compoundNBT, NBT_TAG_NAME_FULLNESS);
    }

    public static boolean isEqualJuice(ItemStack stack, ItemStack stack1) {
        JuiceBottleFlavour flavour = getFlavour(stack);
        JuiceBottleFlavour flavour1 = getFlavour(stack1);
        return flavour == flavour1;
    }

    @Override
    public void fillItemGroup(ItemGroup tab, NonNullList<ItemStack> subItems) {
        if (this.isInGroup(tab)) {
            for (JuiceBottleFlavour flavour : JuiceBottleFlavour.values()) {
                ItemStack subItemStack = new ItemStack(this, 1);
                setFlavour(subItemStack, flavour);
                setFullness(subItemStack, JuiceBottleFullness.FULL);
                subItems.add(subItemStack);
            }
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        return super.getDefaultInstance();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        final int TICKS_PER_SECOND = 20;
        final int DRINK_DURATION_SECONDS = 3;
        return DRINK_DURATION_SECONDS * TICKS_PER_SECOND;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
        ItemStack itemStackHeld = playerIn.getHeldItem(hand);
        JuiceBottleFullness fullness = getFullness(itemStackHeld);
        if (fullness == JuiceBottleFullness.EMPTY) return new ActionResult(ActionResultType.FAIL, itemStackHeld);

        playerIn.setActiveHand(hand);
        return new ActionResult(ActionResultType.PASS, itemStackHeld);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        JuiceBottleFullness fullness = getFullness(stack);
        fullness = fullness.decreaseFullnessByOneStep();
        fullness.putIntoNBT(stack.getTag(), NBT_TAG_NAME_FULLNESS);
        return stack;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        JuiceBottleFlavour flavour = getFlavour(stack);
        return super.getTranslationKey(stack) + "." + flavour.getName();
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        String fullnessText = getFullness(stack).getDescription();
        return new TranslationTextComponent(this.getTranslationKey(stack), fullnessText);
    }
}
