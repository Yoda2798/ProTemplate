package YodasMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;

import static YodasMod.YodasMod.makeID;

public class RedBull extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("RedBull");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = CardHelper.getColor(233,10,20);
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = null;

    public RedBull() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.S, PotionColor.NONE, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.potency == 1) {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[2];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        --potency;
        if (this.potency > 0) {
            this.flash();
            this.updateDescription();
        } else {
            AbstractDungeon.topPanel.destroyPotion(this.slot);
        }
    }

    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
