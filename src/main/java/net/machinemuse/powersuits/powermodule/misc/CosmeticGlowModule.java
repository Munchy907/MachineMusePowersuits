package net.machinemuse.powersuits.powermodule.misc;

import net.machinemuse.api.electricity.IModularItem;
import net.machinemuse.general.gui.MuseIcon;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.MuseCommonStrings;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CosmeticGlowModule extends PowerModuleBase {
    public static final String MODULE_GLOW = "Glow Module";
    public static final String RED_GLOW = "Red Glow";
    public static final String GREEN_GLOW = "Green Glow";
    public static final String BLUE_GLOW = "Blue Glow";

    public CosmeticGlowModule(List<IModularItem> validItems) {
        super(validItems);
        addTradeoffProperty("Red Glow", RED_GLOW, 1, "%");
        addTradeoffProperty("Green Glow", GREEN_GLOW, 1, "%");
        addTradeoffProperty("Blue Glow", BLUE_GLOW, 1, "%");
    }

    @Override
    public boolean isAllowed() {
        return false;
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_COSMETIC;
    }

    @Override
    public String getDataName() {
        return MODULE_GLOW;
    }

    @Override
    public String getUnlocalizedName() { return "cosmeticGlow";
    }

    @Override
    public String getDescription() {
        return "Change the appearance of the glowy bits on your armor.";
    }

    @Override
    public String getTextureFile() {
        return "netherstar";
    }

    @Override
    public TextureAtlasSprite getIcon(ItemStack item) {
        return MuseIcon.cosmeticGlow;
    }
}