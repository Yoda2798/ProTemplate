package YodasMod.patches;

import YodasMod.relics.StrangeFork;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

@SpirePatch2(
        clz = UseCardAction.class,
        method = "update"
)
public class StrangeForkPatch {

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"spoonProc"}
    )
    public static void Insert(UseCardAction __instance, boolean spoonProc)
    {
        AbstractPlayer p = AbstractDungeon.player;
        if (__instance.exhaustCard && !spoonProc && p.hasRelic(StrangeFork.ID) && !p.getRelic(StrangeFork.ID).grayscale) { //&& __instance.targetCard.type != AbstractCard.CardType.POWER) {
            p.getRelic(StrangeFork.ID).flash();
            p.getRelic(StrangeFork.ID).grayscale = true;
            __instance.exhaustCard = false;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {

            Matcher finalMatcher = new Matcher.FieldAccessMatcher(UseCardAction.class,"exhaustCard");

            int[] result = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);

            return new int[] {result[result.length - 1]};
        }
    }
}
