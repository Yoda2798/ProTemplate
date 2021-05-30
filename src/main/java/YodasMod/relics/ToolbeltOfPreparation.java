package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class ToolbeltOfPreparation extends AbstractEasyRelic {
    public static final String ID = makeID("ToolbeltOfPreparation");
    private static final int TURN_ACTIVATION = 2;
    private static final int NUM_CARDS = 2;

    public ToolbeltOfPreparation() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 24
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));// 25
            this.counter = -1;
            this.grayscale = true;
        }

    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}
