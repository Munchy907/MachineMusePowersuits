package net.machinemuse_old.utils;

import net.machinemuse_old.api.ModuleManager;
import net.machinemuse_old.powersuits.item.ItemPowerArmorChestplate;
import net.machinemuse_old.powersuits.powermodule.armor.WaterTankModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by User: Andrew2448
 * 4:52 PM 6/21/13
 */
public class WaterUtils {

    public static double getPlayerWater(EntityPlayer player) {
        double water = 0;
        for (ItemStack stack : MuseItemUtils.getModularItemsInInventory(player)) {
            if (stack.getItem() instanceof ItemPowerArmorChestplate && ModuleManager.itemHasActiveModule(stack, WaterTankModule.MODULE_WATER_TANK)) {
                water = MuseItemUtils.getWaterLevel(stack);
            }
        }
        return water;
    }

    public static double getMaxWater(EntityPlayer player) {
        double water = 0;
        for (ItemStack stack : MuseItemUtils.getModularItemsInInventory(player)) {
            if (stack.getItem() instanceof ItemPowerArmorChestplate && ModuleManager.itemHasActiveModule(stack, WaterTankModule.MODULE_WATER_TANK)) {
                water = ModuleManager.computeModularProperty(stack, WaterTankModule.WATER_TANK_SIZE);
            }
        }
        return water;
    }
}