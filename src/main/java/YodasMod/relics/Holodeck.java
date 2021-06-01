package YodasMod.relics;

import YodasMod.ui.SimulationOption;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Girya;
import com.megacrit.cardcrawl.relics.PeacePipe;
import com.megacrit.cardcrawl.relics.Shovel;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import java.util.ArrayList;

import static YodasMod.YodasMod.makeID;

public class Holodeck extends AbstractEasyRelic {
    public static final String ID = makeID("Holodeck");
    public static final int FOCUS_LIMIT = 3;

    public Holodeck() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, CardColor.BLUE);
        this.counter = 0;
    }

    public void atBattleStart() {
        if (this.counter != 0) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FocusPower(AbstractDungeon.player, this.counter), this.counter));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }

    public boolean canSpawn() {
        if (AbstractDungeon.floorNum >= 48 && !Settings.isEndless)
            return false;
        int campfireRelicCount = 0;
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof PeacePipe || r instanceof Shovel || r instanceof Girya)
                campfireRelicCount++;
        }
        return (campfireRelicCount < 2);
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new SimulationOption(this.counter < 2));
    }
}
