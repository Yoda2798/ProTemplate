package YodasMod.relics;

import static YodasMod.YodasMod.makeID;

public class Pencil extends AbstractEasyRelic {
    public static final String ID = makeID("Pencil");

    public Pencil() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    public void onPlayerEndTurn() {
        this.grayscale = true;
    }

    public void atTurnStartPostDraw() {
        this.grayscale = false;
    }
}
