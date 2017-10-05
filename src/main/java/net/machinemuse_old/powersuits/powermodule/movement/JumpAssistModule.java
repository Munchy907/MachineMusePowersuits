package net.machinemuse_old.powersuits.powermodule.movement;

import net.machinemuse_old.api.IModularItem;
import net.machinemuse_old.api.moduletrigger.IPlayerTickModule;
import net.machinemuse_old.api.moduletrigger.IToggleableModule;
import net.machinemuse_old.general.gui.MuseIcon;
import net.machinemuse_old.numina.player.NuminaPlayerUtils;
import net.machinemuse_old.powersuits.control.PlayerInputMap;
import net.machinemuse_old.powersuits.event.MovementManager;
import net.machinemuse_old.powersuits.item.ItemComponent;
import net.machinemuse_old.powersuits.powermodule.PowerModuleBase;
import net.machinemuse_old.utils.MuseCommonStrings;
import net.machinemuse_old.utils.MuseItemUtils;
import net.machinemuse_old.utils.MusePlayerUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class JumpAssistModule extends PowerModuleBase implements IToggleableModule, IPlayerTickModule {
    public static final String MODULE_JUMP_ASSIST = "Jump Assist";
    public static final String JUMP_ENERGY_CONSUMPTION = "Jump Energy Consumption";
    public static final String JUMP_MULTIPLIER = "Jump Boost";
    public static final String JUMP_FOOD_COMPENSATION = "Jump Exhaustion Compensation";

    public JumpAssistModule(List<IModularItem> validItems) {
        super(validItems);
        addSimpleTradeoff(this, "Power", JUMP_ENERGY_CONSUMPTION, "J", 0, 25, JUMP_MULTIPLIER, "%", 1, 4);
        addSimpleTradeoff(this, "Compensation", JUMP_ENERGY_CONSUMPTION, "J", 0, 5, JUMP_FOOD_COMPENSATION, "%", 0, 1);
        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.servoMotor, 4));
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_MOVEMENT;
    }

    @Override
    public String getDataName() {
        return MODULE_JUMP_ASSIST;
    }

    @Override
    public String getUnlocalizedName() {
        return "jumpAssist";
    }

    @Override
    public void onPlayerTickActive(EntityPlayer player, ItemStack item) {
        PlayerInputMap movementInput = PlayerInputMap.getInputMapFor(player.getCommandSenderEntity().getName());
        boolean jumpkey = movementInput.jumpKey;
        if (jumpkey) {
            double multiplier = MovementManager.getPlayerJumpMultiplier(player);
            if (multiplier > 0) {
                player.motionY += 0.15 * Math.min(multiplier, 1) * MusePlayerUtils.getWeightPenaltyRatio(MuseItemUtils.getPlayerWeight(player), 25000);
                MovementManager.setPlayerJumpTicks(player, multiplier - 1);
            }
            player.jumpMovementFactor = player.getAIMoveSpeed() * .2f;
        } else {
            MovementManager.setPlayerJumpTicks(player, 0);
        }
        NuminaPlayerUtils.resetFloatKickTicks(player);
    }

    @Override
    public void onPlayerTickInactive(EntityPlayer player, ItemStack item) {
    }

    @Override
    public TextureAtlasSprite getIcon(ItemStack item) {
        return MuseIcon.jumpAssist;
    }
}