package YodasMod.relics;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.Ectoplasm;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static YodasMod.YodasMod.makeID;

public class PotOfGold extends AbstractEasyRelic {
    public static final String ID = makeID("PotOfGold");
    private static final int GOLD_AMT = 999;

    public PotOfGold() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK);
    }

    public void onEquip() {
        AbstractPlayer p = AbstractDungeon.player;
        CardCrawlGame.sound.play("GOLD_GAIN");
        p.gainGold(GOLD_AMT);
        for(int i = 0; i < GOLD_AMT; ++i) {
            AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }

    public boolean canSpawn() {
        return !AbstractDungeon.player.hasRelic(Ectoplasm.ID);
    }
}
