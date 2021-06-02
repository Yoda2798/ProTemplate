package YodasMod.patches;

import YodasMod.relics.Pencil;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch2(
        clz = DrawCardAction.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {AbstractCreature.class, int.class, boolean.class}
)
public class PencilPatch {

    @SpirePrefixPatch
    public static void Prefix(@ByRef int[] amount) {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasRelic(Pencil.ID) && !p.getRelic(Pencil.ID).grayscale) {
            p.getRelic(Pencil.ID).flash();
            amount[0]++;
        }
    }
}
