package YodasMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MulliganAction extends AbstractGameAction {
    private float startingDuration;
    private int additionalDraw;

    public MulliganAction(int magicNumber) {
        this.target = AbstractDungeon.player;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        this.additionalDraw = magicNumber;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            int count = AbstractDungeon.player.hand.size();
            this.addToTop(new DrawCardAction(this.target, count + this.additionalDraw));
            this.addToTop(new DiscardAction(this.target, this.target, count, true));
            this.isDone = true;
        }
    }
}
