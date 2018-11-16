package Thmod.Cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

import java.util.ArrayList;
import java.util.Iterator;

import Thmod.Power.Abnormal.BlueAbnormity;
import Thmod.Power.Abnormal.GreenAbnormity;
import Thmod.Power.Abnormal.RedAbnormity;
import Thmod.vfx.AgararetaCoverEffect;
import Thmod.vfx.BorderVeryLongFlashEffect;

public class Agarareta extends AbstractSweepCards {
    public static final String ID = "Agarareta";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    private static final int COST = 1;

    public Agarareta() {
        super("Agarareta", Agarareta.NAME,  1, Agarareta.DESCRIPTION, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
    }

    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new RoomTintEffect(Color.BLACK.cpy(), 0.8F,4F,true));
        for (int i = 0; i < 10; ++i)
            AbstractDungeon.topLevelEffects.add(new AgararetaCoverEffect());
        AbstractDungeon.topLevelEffects.add(new BorderVeryLongFlashEffect(Color.PURPLE.cpy()));
        for (int i = (AbstractDungeon.getCurrRoom().monsters.monsters.size() - 1); i >= 0; i--) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if ((!(target.isDying)) && (target.currentHealth > 0) && (!(target.isEscaping))) {
                int roll = MathUtils.random(2);
                switch (roll) {
                    case 0:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new RedAbnormity(target, 3), 3));
                        break;
                    case 1:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new GreenAbnormity(target, 3), 3));
                        break;
                    case 2:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new BlueAbnormity(target, 3), 3));
                        break;
                    default:
                        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, p, new RedAbnormity(target, 3), 3));
                }
            }
        }
    }

    @Override
    public ArrayList<AbstractSweepCards> getOpposite() {
        final ArrayList<AbstractSweepCards> opposite = new ArrayList<>();
        opposite.add(new DochyakuKami());
        opposite.add(new Mishyaguji());
        return opposite;
    }

    public AbstractCard makeCopy() {
        return new Agarareta();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Agarareta");
        NAME = Agarareta.cardStrings.NAME;
        DESCRIPTION = Agarareta.cardStrings.DESCRIPTION;
    }
}
