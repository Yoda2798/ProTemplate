package YodasMod.powers;

import YodasMod.actions.UpgradeEffectAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Optional;

import static YodasMod.YodasMod.makeID;

public class UpgradeNextCardPower extends AbstractEasyPower {
    public static final String POWER_ID = makeID("UpgradeNextCardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UpgradeNextCardPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.loadRegion("repair");
    }

    // thank you papa thquinn
    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        if (c.canUpgrade()) {
            Optional<AbstractCard> deckCard = p.masterDeck.group.stream().filter(d -> d.uuid == c.uuid).findAny();
            if (deckCard.isPresent()) {
                c.upgrade();
                deckCard.get().upgrade();
                this.addToTop(new UpgradeEffectAction(c));
                this.addToTop(new ReducePowerAction(p, p, this, 1));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}
