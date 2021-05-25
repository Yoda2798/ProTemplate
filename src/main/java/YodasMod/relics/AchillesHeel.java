package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;

import static YodasMod.YodasMod.makeID;

public class AchillesHeel extends AbstractEasyRelic {
    public static final String ID = makeID("AchillesHeel");

    public AchillesHeel() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, CardColor.GREEN);
    }
}
