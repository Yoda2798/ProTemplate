package YodasMod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import static YodasMod.YodasMod.makeID;

public class Intervention extends AbstractEasyRelic implements ClickableRelic {
    public static final String ID = makeID("Intervention");
    public static final int HP_THRESHOLD = 10;

    public Intervention() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onRightClick() {
        if (!this.grayscale) {
            for (AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped() && (m.hasPower("Minion") || m.currentHealth <= HP_THRESHOLD)) {
                    this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
                    this.addToBot(new WaitAction(0.5F));
                    this.addToBot(new JudgementAction(m, m.currentHealth));
                }
            }
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }
}
