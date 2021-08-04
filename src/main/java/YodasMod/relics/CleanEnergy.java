package YodasMod.relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static YodasMod.YodasMod.makeID;

public class CleanEnergy extends AbstractEasyRelic {
    public static final String ID = makeID("CleanEnergy");

    public CleanEnergy() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void obtain() {
        AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < p.relics.size(); ++i) {
            if (p.relics.get(i).tier == RelicTier.STARTER) {
                instantObtain(AbstractDungeon.player, i, true);
                return;
            }
        }
        super.obtain();
    }

    @Override
    public boolean canSpawn() {
        for (AbstractRelic r: AbstractDungeon.player.relics) {
            if (r.tier == RelicTier.STARTER) {
                return true;
            }
        }
        return false;
    }
}
