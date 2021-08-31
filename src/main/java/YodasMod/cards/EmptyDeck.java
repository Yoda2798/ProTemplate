package YodasMod.cards;

import YodasMod.actions.EmptyDeckAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import YodasMod.cards.AbstractEasyCard;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;

import static YodasMod.YodasMod.makeID;
import static YodasMod.util.Wiz.*;

public class EmptyDeck extends AbstractEasyCard {
    public final static String ID = makeID("EmptyDeck");
    // intellij stuff skill, self, uncommon, , , , , ,

    public EmptyDeck() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, CardColor.PURPLE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EmptyDeckAction());
        this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        this.addToBot(new ChangeStanceAction("Neutral"));
    }

    public void upp() {
        this.isInnate = true;
    }
}