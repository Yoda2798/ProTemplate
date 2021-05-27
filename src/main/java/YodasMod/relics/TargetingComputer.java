package YodasMod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static YodasMod.YodasMod.makeID;

public class TargetingComputer extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("TargetingComputer");

    public TargetingComputer() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.BLUE);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (!this.grayscale && damageAmount > 0 && target != p && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToTop(new ApplyPowerAction(target, p, new LockOnPower(target, 1), 1));
        }
    }

    @Override
    public void onRightClick() {
        if (this.grayscale) {
            this.grayscale = false;
        } else {
            this.grayscale = true;
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 4.5F, CardCrawlGame.playerName+this.DESCRIPTIONS[1], true));
        }
    }
}
