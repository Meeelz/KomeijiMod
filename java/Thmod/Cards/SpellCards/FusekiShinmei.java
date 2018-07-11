package Thmod.Cards.SpellCards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FusekiShinmei extends AbstractSpellCards {
    public static final String ID = "FusekiShinmei";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 3;
    private int pointcost;
    private float moper;
    private float plper;

    public FusekiShinmei() {
        super("FusekiShinmei", FusekiShinmei.NAME,  3, FusekiShinmei.DESCRIPTION, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.pointcost = 5;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                moper = (m.currentHealth/m.maxHealth);
                plper = (p.currentHealth/p.maxHealth);
                p.currentHealth = (int)(p.maxHealth * moper);
                m.currentHealth = (int)(m.maxHealth * plper);
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p,p,"PointPower",this.pointcost));
            }
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        if (p.hasPower("PointPower")) {
            if (p.getPower("PointPower").amount >= this.pointcost) {
                return true;
            }
        }
        this.cantUseMessage = "我没有足够的P点";
        return false;
    }

    public AbstractCard makeCopy() {
        return new FusekiShinmei();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("FusekiShinmei");
        NAME = FusekiShinmei.cardStrings.NAME;
        DESCRIPTION = FusekiShinmei.cardStrings.DESCRIPTION;
    }
}
