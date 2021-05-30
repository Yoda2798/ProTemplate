package YodasMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static YodasMod.YodasMod.makeID;

public class RetainPotion extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("RetainPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = Color.CYAN.cpy();
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = null;

    public RetainPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.EYE, PotionColor.NONE, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        if (this.potency > 1) {
            this.description = DESCRIPTIONS[1] + this.potency + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.RETAIN.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.RETAIN.NAMES[0])));
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new ApplyPowerAction(p, p, new EquilibriumPower(p, this.potency), this.potency));
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }
}
