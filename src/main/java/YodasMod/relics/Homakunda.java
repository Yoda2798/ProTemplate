package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import static YodasMod.YodasMod.makeID;

public class Homakunda extends AbstractEasyRelic {
    public static final String ID = makeID("Homakunda");

    public Homakunda() {
        super(ID, RelicTier.RARE, LandingSound.MAGICAL, CardColor.PURPLE);
    }
}
