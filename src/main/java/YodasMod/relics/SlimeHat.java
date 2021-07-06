package YodasMod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class SlimeHat extends AbstractEasyRelic {
    public static final String ID = makeID("SlimeHat");
    private static final int DRAW_AMT = 2;
    private static final int CARD_AMT = 4;

    public SlimeHat() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        AbstractDungeon.player.masterHandSize += DRAW_AMT;
    }

    public void onUnequip() {
        AbstractDungeon.player.masterHandSize -= DRAW_AMT;
    }

    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInDrawPileAction(new Slimed(), CARD_AMT, true, true));
    }
}
