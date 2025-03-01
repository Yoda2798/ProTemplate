package YodasMod.potions;

import YodasMod.actions.CustomDiscoveryAction;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;

import static YodasMod.YodasMod.makeID;

public class BluePotion extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("BluePotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = CardHelper.getColor(19, 20, 200);
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = AbstractPlayer.PlayerClass.DEFECT;

    private static CardGroup blueCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    static {
        for (AbstractCard c: CardLibrary.getCardList(CardLibrary.LibraryType.BLUE)) {
            if (!c.hasTag(AbstractCard.CardTags.HEALING)) {
                blueCards.addToTop(c);
            }
        }
    }

    public BluePotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.NONE, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[2];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new CustomDiscoveryAction(blueCards, 3, potency, true));
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
