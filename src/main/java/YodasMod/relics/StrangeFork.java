package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class StrangeFork extends AbstractEasyRelic {
    public static final String ID = makeID("StrangeFork");

    public StrangeFork() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (!this.grayscale) {
            AbstractDungeon.player.hand.moveToDiscardPile(card);
            this.flash();
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }
}
