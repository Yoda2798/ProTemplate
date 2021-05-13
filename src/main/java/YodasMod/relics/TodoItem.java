package YodasMod.relics;

import YodasMod.YodaCharacter;

import static YodasMod.YodasMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, YodaCharacter.Enums.YODASMOD_COLOR);
    }
}
