package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import static YodasMod.YodasMod.makeID;

public class BrassKnuckles extends AbstractEasyRelic {
    public static final String ID = makeID("BrassKnuckles");

    public BrassKnuckles() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.RED);
    }
}
