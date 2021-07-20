package YodasMod;

import YodasMod.cards.BelowTheBelt;
import YodasMod.potions.*;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import YodasMod.cards.AbstractEasyCard;
import YodasMod.cards.cardvars.SecondDamage;
import YodasMod.cards.cardvars.SecondMagicNumber;
import YodasMod.relics.AbstractEasyRelic;
import org.apache.logging.log4j.LogManager;

import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class YodasMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostPowerApplySubscriber {

    public static final String modID = "yodasmod";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    //public static final Logger logger = LogManager.getLogger(YodasMod.class.getName());

    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/power.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG.png";

    public YodasMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(YodaCharacter.Enums.YODASMOD_COLOR, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        YodasMod thismod = new YodasMod();
    }

    public void receiveEditPotions() {

        BaseMod.addPotion(BatteryAcid.class, BatteryAcid.liquidColor, BatteryAcid.hybridColor, BatteryAcid.spotsColor, BatteryAcid.POTION_ID, BatteryAcid.playerClass);
        BaseMod.addPotion(BottledApotheosis.class, BottledApotheosis.liquidColor, BottledApotheosis.hybridColor, BottledApotheosis.spotsColor, BottledApotheosis.POTION_ID);
        BaseMod.addPotion(BottledHand.class, BottledHand.liquidColor, BottledHand.hybridColor, BottledHand.spotsColor, BottledHand.POTION_ID);
        BaseMod.addPotion(FelfirePotion.class, FelfirePotion.liquidColor, FelfirePotion.hybridColor, FelfirePotion.spotsColor, FelfirePotion.POTION_ID);
        BaseMod.addPotion(LiquidGold.class, LiquidGold.liquidColor, LiquidGold.hybridColor, LiquidGold.spotsColor, LiquidGold.POTION_ID);
        BaseMod.addPotion(PoisonFlask.class, PoisonFlask.liquidColor, PoisonFlask.hybridColor, PoisonFlask.spotsColor, PoisonFlask.POTION_ID, PoisonFlask.playerClass);
        BaseMod.addPotion(RedBull.class, RedBull.liquidColor, RedBull.hybridColor, RedBull.spotsColor, RedBull.POTION_ID);
        BaseMod.addPotion(RetainPotion.class, RetainPotion.liquidColor, RetainPotion.hybridColor, RetainPotion.spotsColor, RetainPotion.POTION_ID);
        BaseMod.addPotion(SeekingPotion.class, SeekingPotion.liquidColor, SeekingPotion.hybridColor, SeekingPotion.spotsColor, SeekingPotion.POTION_ID);
        BaseMod.addPotion(SnakePotion.class, SnakePotion.liquidColor, SnakePotion.hybridColor, SnakePotion.spotsColor, SnakePotion.POTION_ID);
        BaseMod.addPotion(SuperEnergyPotion.class, SuperEnergyPotion.liquidColor, SuperEnergyPotion.hybridColor, SuperEnergyPotion.spotsColor, SuperEnergyPotion.POTION_ID);

        /*new AutoAdd(modID)
                .packageFilter(AbstractEasyPotion.class)
                .any(AbstractEasyPotion.class, (info, potion) -> {

                    //System.out.println(potion);
                    //System.out.println(potion.getClass());
                    BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.POTION_ID);
                });*/

        /*new AutoAdd(modID)
                .packageFilter(AbstractEasyPotion.class)
                .any(AbstractEasyPotion.class, (info, potion) -> {

                    if (potion.playerClass == null) {
                        class cccc = potion.getClass();

                        BaseMod.addPotion(, potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.POTION_ID);
                    } else {
                        BaseMod.addPotion(potion.getClass(), potion.liquidColor, potion.hybridColor, potion.spotsColor, potion.POTION_ID, potion.playerClass);
                    }
                });*/
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new YodaCharacter(YodaCharacter.characterStrings.NAMES[1], YodaCharacter.Enums.YODA_CHARACTER),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, YodaCharacter.Enums.YODA_CHARACTER);
        receiveEditPotions();
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {

                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        switch (relic.color) {
                            case RED:
                                BaseMod.addRelic(relic, RelicType.RED);
                                break;
                            case GREEN:
                                BaseMod.addRelic(relic, RelicType.GREEN);
                                break;
                            case BLUE:
                                BaseMod.addRelic(relic, RelicType.BLUE);
                                break;
                            case PURPLE:
                                BaseMod.addRelic(relic, RelicType.PURPLE);
                                break;
                            default:
                                BaseMod.addRelicToCustomPool(relic, relic.color);
                                break;
                        }
                    }
                    UnlockTracker.markRelicAsSeen(relic.relicId);
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(MonsterStrings.class, modID + "Resources/localization/eng/Monsterstrings.json");

        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/eng/Potionstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/eng/UIstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(VulnerablePower.POWER_ID) && source == AbstractDungeon.player && target != AbstractDungeon.player) {
            for (AbstractCard c: AbstractDungeon.player.discardPile.group) {
                if (c instanceof BelowTheBelt) {
                    AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));
                }
            }
        }
    }
}
