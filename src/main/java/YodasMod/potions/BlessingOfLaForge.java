package YodasMod.potions;

import YodasMod.powers.UpgradeNextCardPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.DrawPower;

import static YodasMod.YodasMod.makeID;

public class BlessingOfLaForge extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("BlessingOfLaForge");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = CardHelper.getColor(219, 200, 15);
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = null;

    public BlessingOfLaForge() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.ANVIL, PotionColor.NONE, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency == 1) {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[2];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.UPGRADE.NAMES[0]), GameDictionary.keywords.get(GameDictionary.UPGRADE.NAMES[0])));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new UpgradeNextCardPower(p, this.potency), this.potency));
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
