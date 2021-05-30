package YodasMod.relics;

import static YodasMod.YodasMod.makeID;

public class StrangeFork extends AbstractEasyRelic {
    public static final String ID = makeID("StrangeFork");

    public StrangeFork() {
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }
}
