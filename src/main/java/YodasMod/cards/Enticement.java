package YodasMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static YodasMod.YodasMod.makeID;

public class Enticement extends AbstractEasyCard {
    public final static String ID = makeID("Enticement");
    // intellij stuff , all_enemy, uncommon, , , 18, 6, 2,

    public Enticement() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, CardColor.RED);
        this.baseBlock = 18;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        for (AbstractMonster monster: AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeBlock(6);
    }
}