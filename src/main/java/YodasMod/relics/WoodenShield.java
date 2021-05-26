package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class WoodenShield extends AbstractEasyRelic {
    public static final String ID = makeID("WoodenShield");

    public WoodenShield() {
        super(ID, RelicTier.COMMON, LandingSound.SOLID);
    }

    public void onPlayerEndTurn() {
        this.flash();
        this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.counter));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        ++this.counter;
    }

    public void onVictory() {
        this.counter = -1;
    }
}
