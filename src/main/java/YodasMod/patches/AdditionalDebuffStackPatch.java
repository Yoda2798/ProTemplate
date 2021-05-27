package YodasMod.patches;

import YodasMod.relics.AchillesHeel;
import YodasMod.relics.BrassKnuckles;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;

@SpirePatch2(
        clz = ApplyPowerAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {
            AbstractCreature.class,
            AbstractCreature.class,
            AbstractPower.class,
            int.class,
            boolean.class,
            AbstractGameAction.AttackEffect.class
        }
)
public class AdditionalDebuffStackPatch {

    private static final Logger logger = LogManager.getLogger(AdditionalDebuffStackPatch.class.getName());

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"target", "source", "powerToApply"}
    )
    public static void Insert(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source, AbstractPower powerToApply)
    {
        if (AbstractDungeon.player.hasRelic(BrassKnuckles.ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Vulnerable")) {
            AbstractDungeon.player.getRelic(BrassKnuckles.ID).flash();
            try {
                Field f = ApplyPowerAction.class.getDeclaredField("powerToApply");
                f.setAccessible(true);
                AbstractPower newThing = powerToApply;
                ++newThing.amount;
                f.set(__instance, newThing);
            } catch (NoSuchFieldException | IllegalAccessException | SecurityException e) {
                logger.info("Error applying Brass Knuckles");
            }
            ++__instance.amount;
        } else if (AbstractDungeon.player.hasRelic(AchillesHeel.ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Weakened")) {
            AbstractDungeon.player.getRelic(AchillesHeel.ID).flash();
            try {
                Field f = ApplyPowerAction.class.getDeclaredField("powerToApply");
                f.setAccessible(true);
                AbstractPower newThing = powerToApply;
                ++newThing.amount;
                f.set(__instance, newThing);
            } catch (NoSuchFieldException | IllegalAccessException | SecurityException e) {
                logger.info("Error applying Achilles' Heel");
            }
            ++__instance.amount;
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
