package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static YodasMod.YodasMod.makeID;

public class IceLolly extends AbstractEasyRelic {
    public static final String ID = makeID("IceLolly");
    public boolean trigger = false;

    public IceLolly() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void onPlayerEndTurn() {
        this.trigger = (EnergyPanel.totalCount > 0);
    }

    public void atTurnStart() {
        if (this.trigger) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    public void onVictory() {
        this.trigger = false;
    }
}
