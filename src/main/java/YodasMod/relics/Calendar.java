package YodasMod.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static YodasMod.YodasMod.makeID;

public class Calendar extends AbstractEasyRelic {
    public static final String ID = makeID("Calendar");
    private static final int NUM_CARDS = 3;
    private static final int WEAK = 1;

    public Calendar() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void atTurnStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL && this.counter != -1) {
            ++this.counter;
            if (this.counter % NUM_CARDS == 0) {
                this.counter = -1;
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                for (AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters) {
                    this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, WEAK, false), WEAK, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }

    }

    public void onVictory() {
        this.counter = -1;
    }
}
