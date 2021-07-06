package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;

import static YodasMod.YodasMod.makeID;

public class ZombieArm extends AbstractEasyRelic {
    public static final String ID = makeID("ZombieArm");
    private static final int MIN_COST = 3;

    public ZombieArm() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.costForTurn >= MIN_COST || (card.cost == -1 && EnergyPanel.getCurrentEnergy() >= MIN_COST)) {
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ArrayList<AbstractCard> cards = new ArrayList<>();

            for (AbstractCard c: AbstractDungeon.player.hand.group) {
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce) {
                    cards.add(c);
                }
            }

            for (CardQueueItem i: AbstractDungeon.actionManager.cardQueue) {
                if (i.card != null) {
                    cards.remove(i.card);
                }
            }

            if (!cards.isEmpty()) {
                cards.get(AbstractDungeon.cardRandomRng.random(0, cards.size() - 1)).setCostForTurn(0);
            }
        }
    }
}
