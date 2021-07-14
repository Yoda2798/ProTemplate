package YodasMod.relics;

import YodasMod.rewards.LinkedRewardItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

import static YodasMod.YodasMod.makeID;

public class DealOrNoDeal extends AbstractEasyRelic {
    public static final String ID = makeID("DealOrNoDeal");
    private static final int RELIC_AMT = 2;
    private boolean cardsReceived = true;

    public DealOrNoDeal() {
        super(ID, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void onEquip() {
        this.cardsReceived = false;
    }

    public void update() {
        super.update();
        if (!this.cardsReceived && !AbstractDungeon.isScreenUp) {
            AbstractDungeon.combatRewardScreen.open();
            AbstractDungeon.combatRewardScreen.rewards.clear();
            /*for (int i = 0; i < RELIC_AMT; i++) {
                AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(RelicTier.BOSS)));
            }*/
            LinkedRewardItem reward1 = new LinkedRewardItem(new LinkedRewardItem(new RewardItem(AbstractDungeon.returnRandomRelic(RelicTier.BOSS))));
            LinkedRewardItem reward2 = new LinkedRewardItem(reward1, AbstractDungeon.returnRandomRelic(RelicTier.BOSS));
            AbstractDungeon.combatRewardScreen.rewards.add(reward1);
            AbstractDungeon.combatRewardScreen.rewards.add(reward2);
            AbstractDungeon.combatRewardScreen.positionRewards();
            AbstractDungeon.overlayMenu.proceedButton.setLabel("yo mama lol");
            this.cardsReceived = true;
            AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
            this.flash();
        }
    }
}
