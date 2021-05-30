package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class FairlyOddParents extends AbstractEasyRelic {
    public static final String ID = makeID("FairlyOddParents");
    private int turnNumber = 0;

    public FairlyOddParents() {
        super(ID, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    public void atBattleStart() {
        this.turnNumber = 0;
    }

    public void atTurnStart() {
        ++this.turnNumber;
        if (!AbstractDungeon.player.hasRelic(("yodasmod:mish")) && this.turnNumber % 2 == 1) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    public void onEquip() {
        if (!AbstractDungeon.player.hasRelic(("yodasmod:mish"))) {
            // flash this, flash that, turn both grey, or could just keep above, and maybe remove flash?
            ++AbstractDungeon.player.energy.energyMaster;
        }
    }
}
