package YodasMod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static YodasMod.YodasMod.makeID;

public class MiniatureBlackHole extends AbstractEasyRelic {
    public static final String ID = makeID("MiniatureBlackHole");

    public MiniatureBlackHole() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
        --AbstractDungeon.player.energy.energyMaster;
    }

    public boolean canSpawn() {
        return AbstractDungeon.actNum <= 1;
    }
}
