package YodasMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.DrawPower;

import static YodasMod.YodasMod.makeID;

public class SnakePotion extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("SnakePotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = Color.WHITE;
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = null;

    public SnakePotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SNECKO, PotionEffect.RAINBOW, liquidColor, hybridColor, spotsColor, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency > 1) {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[2] + this.potency + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[3];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new DrawCardAction(p, this.potency));
        this.addToBot(new ApplyPowerAction(p, p, new DrawPower(p, this.potency), this.potency));
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
