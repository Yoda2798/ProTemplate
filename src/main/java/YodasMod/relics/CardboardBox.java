package YodasMod.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.CallingBell;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static YodasMod.YodasMod.makeID;

public class CardboardBox extends AbstractEasyRelic {
    public static final String ID = makeID("CardboardBox");
    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(CallingBell.ID);
    private boolean relicsReceived = true;

    public CardboardBox() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    public void onEquip() {
        this.relicsReceived = false;
    }

    public void update() {
        super.update();
        if (!this.relicsReceived && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.COMMON)));
            AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(RelicTier.UNCOMMON)));
            AbstractDungeon.combatRewardScreen.positionRewards();
            AbstractDungeon.overlayMenu.proceedButton.setLabel(relicStrings.DESCRIPTIONS[2]);
            this.relicsReceived = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
            this.flash();
        }
    }
}
