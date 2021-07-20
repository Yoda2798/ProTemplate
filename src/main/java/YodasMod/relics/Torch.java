package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class Torch extends AbstractEasyRelic {
    public static final String ID = makeID("Torch");
    private static final int TURN_ACTIVATION = 2;
    private static final int ENERGY_AMT = 1;

    public Torch() {
        super(ID, RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == TURN_ACTIVATION) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 24
            this.addToTop(new GainEnergyAction(ENERGY_AMT));
            this.counter = -1;
            this.grayscale = true;
        }

    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}
