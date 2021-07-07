package YodasMod.patches;

import YodasMod.actions.ForceWaitAction;
import YodasMod.relics.SlimeHat;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import javassist.CtBehavior;

import static YodasMod.YodasMod.makeID;


public class SlimeHatPatch {
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(makeID("Champ"));

    @SpirePatch2(
            clz = SlimeBoss.class,
            method = "takeTurn"
    )
    public static class onTakeTurn {
        @SpirePrefixPatch
        public static void Prefix(SlimeBoss __instance) {
            if (ReflectionHacks.getPrivate(__instance, SlimeBoss.class, "firstTurn")) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_SLIMEBOSS_1A"));
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(__instance, monsterStrings.DIALOG[0], 0.5F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new ForceWaitAction(0.5f));
                ReflectionHacks.setPrivate(__instance, SlimeBoss.class, "firstTurn", false);
            }
        }
    }

    @SpirePatch2(
            clz = SlimeBoss.class,
            method = "getMove"
    )
    public static class onGetMove {
        @SpireInsertPatch(
                locator = SlimeHatPatch.onGetMove.Locator.class
        )
        public static void Insert(SlimeBoss __instance) {
            if (AbstractDungeon.player.hasRelic(SlimeHat.ID)) {
                ReflectionHacks.setPrivate(__instance, SlimeBoss.class, "firstTurn", true);
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {

                Matcher finalMatcher = new Matcher.MethodCallMatcher(SlimeBoss.class,"setMove");

                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
