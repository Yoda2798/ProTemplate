package YodasMod.cards;

import YodasMod.actions.MulliganAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import YodasMod.cards.AbstractEasyCard;

import static YodasMod.YodasMod.makeID;
import static YodasMod.util.Wiz.*;

public class Mulligan extends AbstractEasyCard {
    public final static String ID = makeID("Mulligan");
    // intellij stuff , self, rare, , , , , 1, 2

    public Mulligan() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.SELF, CardColor.GREEN);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MulliganAction(this.magicNumber));
        this.addToBot(new GainEnergyAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}