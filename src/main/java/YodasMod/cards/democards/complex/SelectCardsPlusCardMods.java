package YodasMod.cards.democards.complex;

import basemod.AutoAdd;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import YodasMod.cardmods.EtherealMod;
import YodasMod.cardmods.ExhaustMod;
import YodasMod.cards.AbstractEasyCard;

import java.util.ArrayList;
import java.util.Collections;

import static YodasMod.YodasMod.makeID;
import static YodasMod.util.Wiz.*;

@AutoAdd.Ignore
public class SelectCardsPlusCardMods extends AbstractEasyCard {

    public final static String ID = makeID("SelectCardsPlusCardMods");
    // intellij stuff skill, self, uncommon

    public SelectCardsPlusCardMods() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> myCardsList = new ArrayList<>();
        ArrayList<AbstractCard> eligibleCardsList = getCardsMatchingPredicate(c -> c.cost == 0, true);
        Collections.shuffle(eligibleCardsList);
        for (int i = 0; i < 3; i++) {
            CardModifierManager.addModifier(eligibleCardsList.get(i), new EtherealMod());
            CardModifierManager.addModifier(eligibleCardsList.get(i), new ExhaustMod());
            myCardsList.add(eligibleCardsList.get(i));
        }
        atb(new SelectCardsAction(myCardsList, 1, "Choose a card to add into your hand with Ethereal and Exhaust.", (cards) -> {
            att(new MakeTempCardInHandAction(cards.get(0), 1, true));
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
} 