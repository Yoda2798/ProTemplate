package YodasMod.ui;

import YodasMod.vfx.CampfireSimulationEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import static YodasMod.YodasMod.makeID;

public class SimulationOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SimulationOption"));
    public static final String[] TEXT = uiStrings.TEXT;

    public SimulationOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireSimulationEffect());
        }

    }

    /*static {
        uiStrings = CardCrawlGame.languagePack.getUIString("Lift Option");
        TEXT = uiStrings.TEXT;
    }*/
}