package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YodasMod.YodasMod.makeID;

public class TwoHeads extends AbstractEasyRelic {
    public static final String ID = makeID("TwoHeads");
    private static boolean ATTACK_USED = false;
    private static boolean SKILL_USED = false;
    private static boolean POWER_USED = false;

    public TwoHeads() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atBattleStart() {
        ATTACK_USED = false;
        SKILL_USED = false;
        POWER_USED = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse) {
            // check if used up respective type, this could be cleaner somehow but I am bad
            if (card.type == AbstractCard.CardType.ATTACK) {
                if (ATTACK_USED) {return;}
                ATTACK_USED = true;
            } else if (card.type == AbstractCard.CardType.SKILL) {
                if (SKILL_USED) {return;}
                SKILL_USED = true;
            } else if (card.type == AbstractCard.CardType.POWER) {
                if (POWER_USED) {return;}
                POWER_USED = true;
            } else {
                return;
            }

            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }
}
