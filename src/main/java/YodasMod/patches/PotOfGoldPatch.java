package YodasMod.patches;

import YodasMod.relics.PotOfGold;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.map.RoomTypeAssigner;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import javassist.CtBehavior;

@SpirePatch2(
        clz = AbstractDungeon.class,
        method = "generateMap"
)
public class PotOfGoldPatch {

    @SpireInsertPatch(
            locator = PotOfGoldPatch.Locator.class
    )
    public static void Insert() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasRelic(PotOfGold.ID) && !p.getRelic(PotOfGold.ID).usedUp) {
            RoomTypeAssigner.assignRowAsRoomType(AbstractDungeon.map.get(0), ShopRoom.class);
            p.getRelic(PotOfGold.ID).usedUp = true;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {

            Matcher finalMatcher = new Matcher.MethodCallMatcher(RoomTypeAssigner.class,"assignRowAsRoomType");

            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
