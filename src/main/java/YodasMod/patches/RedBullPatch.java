package YodasMod.patches;

import YodasMod.actions.ForceWaitAction;
import YodasMod.potions.RedBull;
import YodasMod.relics.SlimeHat;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.WingBoots;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import javassist.CtBehavior;

import static YodasMod.YodasMod.makeID;


public class RedBullPatch {
    @SpirePatch2(
            clz = MapRoomNode.class,
            method = "wingedIsConnectedTo"
    )
    public static class onWingedIsConnectedTo {
        @SpireInsertPatch(
                locator = RedBullPatch.onWingedIsConnectedTo.Locator.class,
                localvars = {"edge"}
        )
        public static SpireReturn<Boolean> Insert(MapRoomNode node, MapEdge edge) {
            if (node.y == edge.dstY && AbstractDungeon.player.hasPotion(RedBull.POTION_ID)) {
                return SpireReturn.Return(true);
            } else {
                return SpireReturn.Continue();
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class,"hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(
            clz = MapRoomNode.class,
            method = "update"
    )
    public static class onUpdate {
        @SpireInsertPatch(
                locator = RedBullPatch.onUpdate.Locator.class,
                localvars = {"normalConnection", "wingedConnection"}
        )
        public static void Insert(boolean normalConnection, boolean wingedConnection) {
            AbstractPlayer p = AbstractDungeon.player;
            if (!normalConnection && wingedConnection && p.hasPotion(RedBull.POTION_ID)) {
                // don't use up Winged Boots if using potion
                if (p.hasRelic(WingBoots.ID)) {
                    ++p.getRelic(WingBoots.ID).counter;
                }
                for (AbstractPotion pot : p.potions) {
                    if (pot.ID.equals(RedBull.POTION_ID)) {
                        pot.use(null);
                        return;
                    }
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class,"hasRelic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(
            clz = DungeonMapScreen.class,
            method = "updateControllerInput"
    )
    public static class DungeonMapScreenPatch {
        @SpireInsertPatch(
                locator = RedBullPatch.DungeonMapScreenPatch.Locator.class,
                localvars = {"flightMatters"}
        )
        public static void Insert(@ByRef boolean[] flightMatters) {
            if (!flightMatters[0] && AbstractDungeon.player.hasPotion(RedBull.POTION_ID)) {
                flightMatters[0] = true;
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ModHelper.class,"isModEnabled");
                return new int[] {LineFinder.findInOrder(ctMethodToPatch, finalMatcher)[0] + 1};
            }
        }
    }
}
