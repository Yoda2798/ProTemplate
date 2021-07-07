package YodasMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YodasMod.YodasMod.makeID;

public class BelowTheBelt extends AbstractEasyCard {
    public final static String ID = makeID("BelowTheBelt");
    // intellij stuff attack, enemy, common, 4, 2, , , ,

    public BelowTheBelt() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, CardColor.RED);
        this.baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upp() {
        upgradeDamage(2);
    }
}