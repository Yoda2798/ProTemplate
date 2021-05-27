package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static YodasMod.YodasMod.makeID;

public class TargetingComputer extends AbstractEasyRelic {
    public static final String ID = makeID("TargetingComputer");

    public TargetingComputer() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.BLUE);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (damageAmount > 0 && target != p && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToTop(new ApplyPowerAction(target, p, new LockOnPower(target, 1), 1));
        }
    }
}
