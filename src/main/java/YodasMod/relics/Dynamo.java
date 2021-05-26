package YodasMod.relics;


import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YodasMod.YodasMod.makeID;

public class Dynamo extends AbstractEasyRelic {
    public static final String ID = makeID("Dynamo");
    private boolean triggeredThisTurn = false;

    public Dynamo() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK);
    }

    public void atTurnStart() {
        this.triggeredThisTurn = false;
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (!this.triggeredThisTurn && c.costForTurn == 0) {
            this.triggeredThisTurn = true;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }
}
