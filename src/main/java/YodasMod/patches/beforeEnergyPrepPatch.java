package YodasMod.patches;

import YodasMod.relics.SlaversWhip;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpirePatch2(
        clz = AbstractPlayer.class,
        method = "preBattlePrep"
)
public class beforeEnergyPrepPatch {

    private static final Logger logger = LogManager.getLogger(beforeEnergyPrepPatch.class.getName());

    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert()
    {
        if (AbstractDungeon.player.hasRelic("yodasmod:SlaversWhip")) {
            ((SlaversWhip)AbstractDungeon.player.getRelic("yodasmod:SlaversWhip")).beforeEnergyPrep();
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
