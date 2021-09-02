package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;

import static YodasMod.YodasMod.makeID;

public class JawWormTrophy extends AbstractEasyRelic {
    public static final String ID = makeID("JawWormTrophy");
    public static final float MODIFIER_AMT = 0.25F;

    public JawWormTrophy() {
        super(ID, RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return String.format(this.DESCRIPTIONS[0], MODIFIER_AMT * 100);
    }

    public void atBattleStart() {
        boolean isBoss = false;
        for (AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isBoss = true;
                break;
            }
        }
        if (isBoss) {
            this.flash();
            for (AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (m.currentHealth > (int)(m.maxHealth * (1.0F - MODIFIER_AMT))) {
                    m.currentHealth = (int)(m.maxHealth * (1.0F - MODIFIER_AMT));
                    m.healthBarUpdatedEvent();
                }
            }
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }
}