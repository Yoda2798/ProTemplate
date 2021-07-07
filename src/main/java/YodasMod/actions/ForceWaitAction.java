package YodasMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ForceWaitAction extends AbstractGameAction {
    public ForceWaitAction(float setDur) {
        this.setValues(null, null, 0);
        this.duration = setDur;

        this.actionType = ActionType.WAIT;
    }

    public void update() {
        this.tickDuration();
    }
}
