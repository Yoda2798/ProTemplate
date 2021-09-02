package YodasMod.patches;

import YodasMod.relics.JawWormTrophy;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import javassist.CtBehavior;

@SpirePatch2(
        clz = AbstractCreature.class,
        method = "loadAnimation"
)
public class JawWormTrophyPatch {
    @SpireInsertPatch(
            locator = JawWormTrophyPatch.Locator.class,
            localvars = {"scale"}
    )
    public static void Insert(AbstractCreature __instance, @ByRef float[] scale) {
        if (AbstractDungeon.player.hasRelic(JawWormTrophy.ID)
                && (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss
                        || (__instance instanceof AbstractMonster && ((AbstractMonster)__instance).type == AbstractMonster.EnemyType.BOSS))) {
            scale[0] += JawWormTrophy.MODIFIER_AMT;
            /*for (AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
                if (m.type == AbstractMonster.EnemyType.BOSS) {
                    scale[0] += JawWormTrophy.MODIFIER_AMT;
                    return;
                }
            }*/
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
