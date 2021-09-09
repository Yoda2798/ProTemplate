package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import com.megacrit.cardcrawl.relics.RunicPyramid;

import static YodasMod.YodasMod.makeID;

public class Gumguard extends AbstractEasyRelic {
    public static final String ID = makeID("Gumguard");

    public Gumguard() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, 1), 1));
    }

    public boolean canSpawn() {
        return !AbstractDungeon.player.hasRelic(RunicPyramid.ID);
    }
}
