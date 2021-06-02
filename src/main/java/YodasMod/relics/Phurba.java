package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.WrathStance;

import static YodasMod.YodasMod.makeID;

public class Phurba extends AbstractEasyRelic {
    public static final String ID = makeID("Phurba");

    public Phurba() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, CardColor.PURPLE);
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance instanceof WrathStance && !this.grayscale) {
            this.flash();
            this.grayscale = true;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }
}
