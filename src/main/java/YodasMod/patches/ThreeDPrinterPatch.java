package YodasMod.patches;

import YodasMod.relics.ThreeDPrinter;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.SingleRelicViewPopup;

@SpirePatch2(
        clz = SingleRelicViewPopup.class,
        method = "close"
)
public class ThreeDPrinterPatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractRelic ___relic) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p != null && p.hasRelic(ThreeDPrinter.ID) && !p.getRelic(ThreeDPrinter.ID).grayscale && ___relic.tier != AbstractRelic.RelicTier.BOSS && !(___relic instanceof ThreeDPrinter)) {
            ___relic.makeCopy().instantObtain();
            p.getRelic(ThreeDPrinter.ID).grayscale = true;
        }
    }
}