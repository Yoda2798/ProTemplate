package YodasMod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static YodasMod.YodasMod.makeID;

public class AchillesHeel extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("AchillesHeel");

    public AchillesHeel() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.GREEN);
    }

    @Override
    public int onApplyPowerStacks(AbstractPower powerToApply, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (powerToApply.ID.equals(WeakPower.POWER_ID) && target != null && !target.isPlayer) {
            this.flash();
            powerToApply.amount++;
            stackAmount++;
        }
        return stackAmount;
    }

    @Override
    public boolean onApplyPower(AbstractPower powerToApply, AbstractCreature target, AbstractCreature source) {
        return true;
    }
}