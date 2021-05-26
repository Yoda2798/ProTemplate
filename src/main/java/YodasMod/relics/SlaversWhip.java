package YodasMod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static YodasMod.YodasMod.makeID;

public class SlaversWhip extends AbstractEasyRelic {
    public static final String ID = makeID("SlaversWhip");

    public SlaversWhip() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void beforeEnergyPrep() {
        boolean isEliteOrBoss = AbstractDungeon.getCurrRoom().eliteTrigger;
        Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var2.hasNext()) {
            AbstractMonster m = (AbstractMonster)var2.next();
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
            }
        }

        if (!isEliteOrBoss) {
            this.beginLongPulse();
            this.flash();
            ++AbstractDungeon.player.energy.energyMaster;
            ++AbstractDungeon.player.energy.energyMaster;
        }

    }// 49

    public void onVictory() {
        if (this.pulse) {
            --AbstractDungeon.player.energy.energyMaster;
            --AbstractDungeon.player.energy.energyMaster;
            this.stopPulse();
        }

    }
}
