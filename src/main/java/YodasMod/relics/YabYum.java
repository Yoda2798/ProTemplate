package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

import static YodasMod.YodasMod.makeID;

public class YabYum extends AbstractEasyRelic {
    public static final String ID = makeID("YabYum");

    public YabYum() {
        super(ID, RelicTier.UNCOMMON, LandingSound.MAGICAL, CardColor.PURPLE);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;

        if (this.counter > 0) {
            this.flash();
            CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(p, p, new MantraPower(p, this.counter), this.counter));
        }

        this.counter = -1;
        this.grayscale = true;
    }

    public void onVictory() {
        AbstractPlayer p = AbstractDungeon.player;

        if (p.hasPower("Mantra")) {
            this.counter = p.getPower("Mantra").amount;
        } else {
            this.counter = -1;
        }
        this.grayscale = false;
    }
}
