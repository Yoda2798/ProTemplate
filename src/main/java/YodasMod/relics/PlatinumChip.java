package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;

import static YodasMod.YodasMod.makeID;

public class PlatinumChip extends AbstractEasyRelic {
    public static final String ID = makeID("PlatinumChip");

    public PlatinumChip() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, AbstractCard.CardColor.BLUE);
    }

    public void atBattleStart() {
        this.flash();
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, 3), 1));
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }
}
