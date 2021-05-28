package YodasMod.potions;

import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static YodasMod.YodasMod.makeID;

public class PoisonFlask extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("PoisonFlask");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public static final Color liquidColor = CardHelper.getColor(233,199,76);
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    //public static final AbstractPlayer.PlayerClass playerClass = AbstractPlayer.PlayerClass.THE_SILENT;

    public PoisonFlask() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.SPHERE, PotionColor.NONE);
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.labOutlineColor = Settings.GREEN_RELIC_COLOR;
        this.isThrown = true;
        this.targetRequired = true;
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new PoisonPower(target, AbstractDungeon.player, this.potency), this.potency));
    }

    @Override
    public AbstractPotion makeCopy() {
        return new PoisonFlask();
    }

    @Override
    public int getPotency(final int potency) {
        return 12;
    }
}
