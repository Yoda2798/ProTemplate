package YodasMod.cards;

import YodasMod.actions.OneForAllAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YodasMod.YodasMod.makeID;

public class OneForAll extends AbstractEasyCard {
    public final static String ID = makeID("OneForAll");
    // intellij stuff attack, enemy, rare, 10, 4, , , 3, 1

    public OneForAll() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, CardColor.BLUE);
        this.baseDamage = 10;
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new OneForAllAction(this.magicNumber, 0));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}