package YodasMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

public class UpgradeEffectAction extends AbstractGameAction {
    private AbstractCard theCard;

    public UpgradeEffectAction(AbstractCard card) {
        this.theCard = card;
    }

    public void update() {
        this.tickDuration();
        if (this.isDone && this.theCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
            this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        }

    }
}
