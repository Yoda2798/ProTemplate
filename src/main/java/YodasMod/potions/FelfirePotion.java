package YodasMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import java.util.Iterator;

import static YodasMod.YodasMod.makeID;

public class FelfirePotion extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("FelfirePotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = Color.LIME.cpy();
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = null;

    public FelfirePotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SPHERE, PotionEffect.RAINBOW, liquidColor, hybridColor, spotsColor, playerClass);
        this.isThrown = true;
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        for (AbstractMonster m: AbstractDungeon.getMonsters().monsters) {
            if (!m.isDeadOrEscaped() && (m.type == AbstractMonster.EnemyType.NORMAL || (this.potency > 1 && m.type == AbstractMonster.EnemyType.ELITE))) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
                this.addToBot(new WaitAction(0.5F));
                this.addToBot(new JudgementAction(m, m.currentHealth));
            }
        }
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
