package YodasMod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public abstract class AbstractEasyPotion extends CustomPotion {
    public static String POTION_ID;
    public static  String[] DESCRIPTIONS;
    public static AbstractPlayer.PlayerClass playerClass;

    public AbstractEasyPotion(String name, String id, PotionRarity rarity, PotionSize size, PotionColor color) {
        super(name, id, rarity, size, color);
        this.potency = getPotency();
        this.isThrown = false;
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    /*@Override
    public AbstractPotion makeCopy() {
        return new this.getClass();
    }*/
}
