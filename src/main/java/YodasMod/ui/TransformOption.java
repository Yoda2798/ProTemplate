package YodasMod.ui;

import YodasMod.util.TexLoader;
import YodasMod.vfx.CampfireTransformEffect;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import static YodasMod.YodasMod.makeID;
import static YodasMod.YodasMod.makeImagePath;

public class TransformOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("TransformOption"));
    public static final String[] TEXT = uiStrings.TEXT;

    public TransformOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = TEXT[1];
        this.img = TexLoader.getTexture(makeImagePath("ui/TransformIcon.png"));
    }

    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CampfireTransformEffect());
        }
    }
}