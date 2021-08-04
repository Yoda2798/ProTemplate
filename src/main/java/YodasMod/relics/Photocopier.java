package YodasMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

import java.util.ArrayList;

import static YodasMod.YodasMod.makeID;

public class Photocopier extends AbstractEasyRelic {
    public static final String ID = makeID("Photocopier");

    public Photocopier() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    public void onEquip() {
        this.flash();
        ArrayList<AbstractCard> deck = new ArrayList<>(AbstractDungeon.player.masterDeck.group);
        for (AbstractCard card: deck) {
            AbstractDungeon.topLevelEffects.add(new FastCardObtainEffect(card, Settings.WIDTH * 0.5f, Settings.HEIGHT * 0.5f));
        }
    }
}
