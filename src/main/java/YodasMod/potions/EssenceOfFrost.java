package YodasMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static YodasMod.YodasMod.makeID;

public class EssenceOfFrost extends AbstractEasyPotion {
    public static final String POTION_ID = makeID("EssenceOfFrost");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public static final Color liquidColor = CardHelper.getColor(73, 74, 240);
    public static final Color hybridColor = null;
    public static final Color spotsColor = null;

    public static final AbstractPlayer.PlayerClass playerClass = AbstractPlayer.PlayerClass.DEFECT;

    public EssenceOfFrost() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.S, PotionColor.NONE, playerClass);
    }

    @Override
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.CHANNEL.NAMES[0]), GameDictionary.keywords.get(GameDictionary.CHANNEL.NAMES[0])));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.FROST.NAMES[0]), GameDictionary.keywords.get(GameDictionary.FROST.NAMES[0])));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.potency = this.getPotency();
            for (int i = 0; i < this.potency; i++) {
                this.addToBot(new ChannelAction(new Frost()));
            }
        }
    }

    @Override
    public int getPotency(final int potency) {
        return 3;
    }
}
