package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import static YodasMod.YodasMod.makeID;

public class RoboticGremlinHorn extends AbstractEasyRelic {
    public static final String ID = makeID("RoboticGremlinHorn");

    public RoboticGremlinHorn() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY, CardColor.BLUE);
        this.energyBased = true;
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new IncreaseMaxOrbAction(1));
            this.addToBot(new ChannelAction(AbstractOrb.getRandomOrb(true)));
        }

    }
}
