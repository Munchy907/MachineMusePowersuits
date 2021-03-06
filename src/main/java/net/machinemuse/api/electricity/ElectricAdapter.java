package net.machinemuse.api.electricity;

import cofh.api.energy.IEnergyContainerItem;
import ic2.api.item.IElectricItem;
import mekanism.api.energy.IEnergizedItem;
import net.machinemuse.powersuits.common.ModCompatibility;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Ported to Java by lehjr on 11/4/16.
 */
public abstract class ElectricAdapter {
    public static ElectricAdapter wrap(ItemStack stack) {
        if (stack == null)
            return null;
        Item i = stack.getItem();
        if (i instanceof IMuseElectricItem) {
            return new MuseElectricAdapter(stack);
        } else if (ModCompatibility.isMekanismLoaded() && i instanceof IEnergizedItem) {
            if (!((IEnergizedItem) i).canSend(stack)) // don't count items that can't supply power
                return null;
            return new MekanismElectricAdapter(stack);
        } else if (ModCompatibility.isRFAPILoaded() && i instanceof IEnergyContainerItem) {
            return new TEElectricAdapter(stack);
        } else if (ModCompatibility.isIndustrialCraftLoaded() && i instanceof IElectricItem) {
            return new IC2ElectricAdapter(stack);
        } else {
            return null;
        }
    }

    public abstract double getCurrentMPSEnergy();

    public abstract double getMaxMPSEnergy();

    public abstract double drainMPSEnergy(double requested);

    public abstract double giveMPSEnergy(double provided);
}