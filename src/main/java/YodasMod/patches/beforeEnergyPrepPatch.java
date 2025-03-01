package YodasMod.patches;

import YodasMod.relics.SlaversWhip;
import YodasMod.relics.TwinSunflower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

@SpirePatch2(
        clz = AbstractPlayer.class,
        method = "preBattlePrep"
)
public class beforeEnergyPrepPatch {

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert()
    {
        if (AbstractDungeon.player.hasRelic(SlaversWhip.ID)) {
            ((SlaversWhip)AbstractDungeon.player.getRelic(SlaversWhip.ID)).beforeEnergyPrep();
        }
        if (AbstractDungeon.player.hasRelic(TwinSunflower.ID)) {
            ((TwinSunflower)AbstractDungeon.player.getRelic(TwinSunflower.ID)).beforeEnergyPrep();
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {

            Matcher finalMatcher = new Matcher.MethodCallMatcher(EnergyManager.class,"prep");

            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
