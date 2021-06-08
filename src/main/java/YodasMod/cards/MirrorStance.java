package YodasMod.cards;

import YodasMod.actions.MirrorStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YodasMod.YodasMod.makeID;

public class MirrorStance extends AbstractEasyCard {
    public final static String ID = makeID("MirrorStance");
    // intellij stuff skill, self_and_enemy, common, , , , , ,

    public MirrorStance() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY, CardColor.PURPLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MirrorStanceAction(p, m));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}