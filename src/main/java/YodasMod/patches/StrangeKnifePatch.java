package YodasMod.patches;

import YodasMod.relics.StrangeKnife;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

@SpirePatch2(
        clz = TopPanel.class,
        method = "destroyPotion"
)
public  class StrangeKnifePatch {
    @SpirePrefixPatch
    public static SpireReturn Prefix() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasRelic(StrangeKnife.ID) && p.getRelic(StrangeKnife.ID).grayscale) {
            p.getRelic(StrangeKnife.ID).grayscale = false;
            p.getRelic(StrangeKnife.ID).flash();
            return SpireReturn.Return(null);
        } else {
            return SpireReturn.Continue();
        }
    }
}