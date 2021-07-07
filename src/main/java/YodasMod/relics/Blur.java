package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;

import static YodasMod.YodasMod.makeID;

public class Blur extends AbstractEasyRelic {
    public static final String ID = makeID("Blur");
    private static final int BLUR_AMT = 2;

    public Blur() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlurPower(AbstractDungeon.player, BLUR_AMT), BLUR_AMT));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
