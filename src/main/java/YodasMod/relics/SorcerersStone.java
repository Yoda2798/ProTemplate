package YodasMod.relics;

import YodasMod.ui.TransformOption;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

import static YodasMod.YodasMod.makeID;

public class SorcerersStone extends AbstractEasyRelic {
    public static final String ID = makeID("SorcerersStone");

    public SorcerersStone() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    public boolean canSpawn() {
        return AbstractDungeon.floorNum < 48 || Settings.isEndless;
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new TransformOption(!CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).isEmpty()));
    }
}
