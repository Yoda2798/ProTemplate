package YodasMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import YodasMod.cards.AbstractEasyCard;

import static YodasMod.YodasMod.makeID;
import static YodasMod.util.Wiz.*;

public class DefendPlus extends AbstractEasyCard {
    public final static String ID = makeID("DefendPlus");
    // intellij stuff skill, self, basic, , , 10, 15, , 

    public DefendPlus() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, CardColor.BLUE);
        baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeBlock(15);
    }
}