package YodasMod.patches;

import YodasMod.relics.MiniatureBlackHole;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

class MiniatureBlackHolePatch {

    @SpirePatch2(
            clz = AbstractRelic.class,
            method = "obtain"
    )
    public static class onObtain {
        @SpirePrefixPatch
        public static SpireReturn Prefix(AbstractRelic __instance) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasRelic(MiniatureBlackHole.ID)) {
                p.getRelic(MiniatureBlackHole.ID).flash();
                UnlockTracker.markRelicAsSeen(__instance.relicId);
                __instance.isDone = true;
                __instance.isObtained = true;
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch2(
            clz = AbstractRelic.class,
            method = "instantObtain",
            paramtypez = {}
    )
    public static class onInstantObtain {
        @SpirePrefixPatch
        public static SpireReturn Prefix(AbstractRelic __instance) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.hasRelic(MiniatureBlackHole.ID)) {
                p.getRelic(MiniatureBlackHole.ID).flash();
                UnlockTracker.markRelicAsSeen(__instance.relicId);
                __instance.isDone = true;
                __instance.isObtained = true;
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch2(
            clz = AbstractRelic.class,
            method = "instantObtain",
            paramtypez = {AbstractPlayer.class, int.class, boolean.class}
    )
    public static class onInstantObtain2 {
        @SpirePrefixPatch
        public static SpireReturn Prefix(AbstractRelic __instance, AbstractPlayer p) {
            if (p.hasRelic(MiniatureBlackHole.ID)) {
                p.getRelic(MiniatureBlackHole.ID).flash();
                UnlockTracker.markRelicAsSeen(__instance.relicId);
                __instance.isDone = true;
                __instance.isObtained = true;
                return SpireReturn.Return(null);
            } else {
                return SpireReturn.Continue();
            }
        }
    }
}