package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Frost;

import static YodasMod.YodasMod.makeID;

public class ColdFace extends AbstractEasyRelic {
    public static final String ID = makeID("ColdFace");

    public ColdFace() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.BLUE);
    }

    public void atPreBattle() {
        AbstractDungeon.player.channelOrb(new Frost());
    }
}
