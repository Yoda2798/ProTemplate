package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static YodasMod.YodasMod.makeID;

public class Shedinja extends AbstractEasyRelic {
    public static final String ID = makeID("Shedinja");
    private static final int INTANGIBLE_AMOUNT = 2;
    private boolean firstTurn = true;

    public Shedinja() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atPreBattle() {
        this.firstTurn = true;// 44
    }// 45

    public void atTurnStart() {
        if (this.firstTurn) {
            AbstractPlayer p = AbstractDungeon.player;
            this.flash();
            this.addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, this.INTANGIBLE_AMOUNT), this.INTANGIBLE_AMOUNT));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.firstTurn = false;
        }

    }
}
