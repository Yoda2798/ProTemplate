package YodasMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MirrorStanceAction extends AbstractGameAction {
    private AbstractMonster targetMonster;

    public MirrorStanceAction(AbstractPlayer p, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
            this.addToBot(new ChangeStanceAction("Wrath"));
        } else {
            this.addToBot(new ChangeStanceAction("Calm"));
        }
        this.isDone = true;
    }
}
