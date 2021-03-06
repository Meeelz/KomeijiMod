package Thmod.Power;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KyoufuSaiminPower extends AbstractPower {
    public static final String POWER_ID = "KyoufuSaiminPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("KyoufuSaiminPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int counter;

    public KyoufuSaiminPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = "KyoufuSaiminPower";
        this.owner = owner;
        this.amount = 0;
        updateDescription();
        this.img = ImageMaster.loadImage("images/power/32/KyoufuSaiminPower.png");
        this.type = PowerType.DEBUFF;
        this.counter = 3;
    }

    public void atEndOfRound() {
        if(this.counter <= 1){
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner,this.owner,this));
            AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.FIRE));
        }
        else {
            this.counter -= 1;
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        this.amount += damageAmount;
        return super.onAttacked(info, damageAmount);
    }

//    public int onLoseHp(int damageAmount) {
//        this.amount += damageAmount;
//        return damageAmount;
//    }

    public void updateDescription()
    {
        this.description = DESCRIPTIONS[0];
    }
}
