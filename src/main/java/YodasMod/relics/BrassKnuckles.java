package YodasMod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static YodasMod.YodasMod.makeID;

public class BrassKnuckles extends AbstractEasyRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("BrassKnuckles");

    public BrassKnuckles() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.RED);
    }

    @Override
    public int onApplyPowerStacks(AbstractPower powerToApply, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (powerToApply.ID.equals(VulnerablePower.POWER_ID) && target != null && !target.isPlayer) {
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