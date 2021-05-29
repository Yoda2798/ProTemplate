package YodasMod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public abstract class AbstractEasyPotion extends CustomPotion {
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

    /*@Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = this.DESCRIPTIONS[0] + this.potency + this.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }*/

    @Override
    public AbstractPotion makeCopy() {
        try {
            return (AbstractPotion)this.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException var2) {
            throw new RuntimeException("Failed to auto-generate makeCopy for potion: " + this.POTION_ID);
        }
    }


}
