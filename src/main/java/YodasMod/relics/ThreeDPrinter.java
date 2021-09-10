package YodasMod.relics;

import static YodasMod.YodasMod.makeID;

public class ThreeDPrinter extends AbstractEasyRelic {
    public static final String ID = makeID("3DPrinter");

    public ThreeDPrinter() {
        super(ID, RelicTier.SHOP, LandingSound.CLINK);
    }
}
