package YodasMod.patches;

import YodasMod.relics.Homakunda;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.WrathStance;

@SpirePatch2(
        clz = WrathStance.class,
        method = "atDamageReceive"
)
public class HomakundaPatch {

    @SpirePrefixPatch
    public static SpireReturn<Float> Prefix(float damage, DamageInfo.DamageType type) {
        if (AbstractDungeon.player.hasRelic(Homakunda.ID)) {
            return SpireReturn.Return(type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage);
        } else {
            return SpireReturn.Continue();
        }
    }
}
