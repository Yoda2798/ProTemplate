package YodasMod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class TwinSunflower extends AbstractEasyRelic {
    public static final String ID = makeID("TwinSunflower");

    public TwinSunflower() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
        this.counter = 0;
    }

    public void beforeEnergyPrep() {
        if (this.counter == 1) {
            if (!this.pulse) {this.beginLongPulse();}
            this.flash();
            ++AbstractDungeon.player.energy.energyMaster;
            this.counter = 0;
        } else {
            this.counter = 1;
        }
    }

    public void onVictory() {
        if (this.pulse) {
            --AbstractDungeon.player.energy.energyMaster;
            this.stopPulse();
        } else {
            this.beginLongPulse();
        }
    }
}
