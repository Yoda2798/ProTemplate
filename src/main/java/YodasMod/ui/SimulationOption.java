package YodasMod.ui;

import YodasMod.util.TexLoader;
import YodasMod.vfx.CampfireSimulationEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import static YodasMod.YodasMod.*;

public class SimulationOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("SimulationOption"));
    public static final String[] TEXT = uiStrings.TEXT;

    public SimulationOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = TexLoader.getTexture(makeImagePath("ui/SimulationIcon.png"));
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireSimulationEffect());
        }
    }
}