package YodasMod.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static YodasMod.YodasMod.makeID;

public class StrangeKnife extends AbstractEasyRelic {
    public static final String ID = makeID("StrangeKnife");

    public StrangeKnife() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void onUsePotion() {
        if (AbstractDungeon.cardRandomRng.random(3) == 0) {
            this.grayscale = true;
        }
    }
}
