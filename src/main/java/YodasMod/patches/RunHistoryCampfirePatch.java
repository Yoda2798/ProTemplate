package YodasMod.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.runHistory.RunPathElement;
import com.megacrit.cardcrawl.screens.stats.CampfireChoice;
import javassist.CtBehavior;

import static YodasMod.YodasMod.makeID;

@SpirePatch2(
        clz = RunPathElement.class,
        method = "getTipDescriptionText"
)
public class RunHistoryCampfirePatch {
    private static final String TEXT_SIMULATED = CardCrawlGame.languagePack.getUIString(makeID("SimulationOption")).TEXT[3];
    private static final String TEXT_TRANSFORMED = ReflectionHacks.getPrivateStatic(RunPathElement.class, "TEXT_TRANSFORMED");
    private static final String TEXT_OBTAIN_TYPE_CARD = ReflectionHacks.getPrivateStatic(RunPathElement.class, "TEXT_OBTAIN_TYPE_CARD");
    private static final String TEXT_OBTAIN_HEADER = ReflectionHacks.getPrivateStatic(RunPathElement.class, "TEXT_OBTAIN_HEADER");
    private static final String TEXT_MISSING_INFO = ReflectionHacks.getPrivateStatic(RunPathElement.class, "TEXT_MISSING_INFO");

    @SpireInsertPatch(
            locator = RunHistoryCampfirePatch.Locator.class,
            localvars = {"sb"}
    )
    public static void Insert(RunPathElement __instance, StringBuilder sb) {
        CampfireChoice campfireChoice = ReflectionHacks.getPrivate(__instance, RunPathElement.class, "campfireChoice");
        if (campfireChoice != null) {
            int sbLength = sb.length();
            switch (campfireChoice.key) {
                case "YODA:SIMULATION":
                    sb.delete(sbLength - TEXT_MISSING_INFO.length(), sbLength);
                    sb.append(String.format(TEXT_SIMULATED, campfireChoice.data));
                    break;
                case "YODA:TRANSFORM":
                    sb.delete(sbLength - TEXT_MISSING_INFO.length(), sbLength);
                    String[] data = campfireChoice.data.split("YYOODDAA");
                    sb.append(String.format(TEXT_TRANSFORMED, CardLibrary.getCardNameFromMetricID(data[0])));
                    sb.append(" NL ").append(TEXT_OBTAIN_HEADER);
                    sb.append(" NL ").append(" TAB ").append(TEXT_OBTAIN_TYPE_CARD).append(CardLibrary.getCardNameFromMetricID(data[1]));
                    break;
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {

            Matcher finalMatcher = new Matcher.FieldAccessMatcher(RunPathElement.class,"relicsObtained");

            int[] result = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);

            return new int[] {result[1]};
        }
    }
}
