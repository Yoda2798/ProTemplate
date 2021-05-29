package YodasMod.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public abstract class AbstractEasyPotion extends AbstractPotion {
    public static String POTION_ID;
    public static  String[] DESCRIPTIONS;
    public static AbstractPlayer.PlayerClass playerClass;

    public AbstractEasyPotion(String name, String id, PotionRarity rarity, PotionSize size, PotionColor color, AbstractPlayer.PlayerClass playerClass) {
        super(name, id, rarity, size, color);
        this.potency = getPotency();
        if (playerClass != null) {
            switch (playerClass) {
                case IRONCLAD:
                    this.labOutlineColor = Settings.RED_RELIC_COLOR;
                    break;
                case THE_SILENT:
                    this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
                    break;
                case DEFECT:
                    this.labOutlineColor = Settings.BLUE_RELIC_COLOR;
                    break;
                case WATCHER:
                    this.labOutlineColor = Settings.PURPLE_RELIC_COLOR;
                    break;
                default:
                    // TODO: add logic for custom characters here
                    break;

            }
        }
    }

    public AbstractEasyPotion(String name, String id, AbstractPotion.PotionRarity rarity, AbstractPotion.PotionSize size, AbstractPotion.PotionEffect effect, Color liquidColor, Color hybridColor, Color spotsColor, AbstractPlayer.PlayerClass playerClass) {
        super(name, id, rarity, size, effect, liquidColor, hybridColor, spotsColor);
        this.potency = getPotency();
        if (playerClass != null) {
            switch (playerClass) {
                case IRONCLAD:
                    this.labOutlineColor = Settings.RED_RELIC_COLOR;
                    break;
                case THE_SILENT:
                    this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
                    break;
                case DEFECT:
                    this.labOutlineColor = Settings.BLUE_RELIC_COLOR;
                    break;
                case WATCHER:
                    this.labOutlineColor = Settings.PURPLE_RELIC_COLOR;
                    break;
                default:
                    // TODO: add logic for custom characters here
                    break;

            }
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        try {
            return (AbstractPotion)this.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("Failed to auto-generate makeCopy for potion: " + POTION_ID);
        }
    }


}
