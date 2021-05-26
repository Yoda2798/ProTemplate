package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class Propeller extends AbstractEasyRelic {
    public static final String ID = makeID("Propeller");
    private static final int TURN_ACTIVATION = 4;

    public Propeller() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 4) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 22));
            this.counter = -1;
            this.grayscale = true;
        }

    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}
