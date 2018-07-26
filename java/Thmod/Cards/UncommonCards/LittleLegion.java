package Thmod.Cards.UncommonCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Thmod.Actions.common.ChangeOrbAction;
import Thmod.Actions.unique.ChooseAction;
import Thmod.Cards.AbstractKomeijiCards;
import Thmod.Orbs.NingyouOrb;

public class LittleLegion extends AbstractKomeijiCards {
    public static final String ID = "LittleLegion";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private static final int COST = 3;

    public LittleLegion() {
        super("LittleLegion", LittleLegion.NAME,  3, LittleLegion.DESCRIPTION, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
}

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        final ChooseAction choice = new ChooseAction(this, m, LittleLegion.EXTENDED_DESCRIPTION[0], false, 1);
        choice.add(LittleLegion.EXTENDED_DESCRIPTION[1], LittleLegion.EXTENDED_DESCRIPTION[2], () -> {
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 1));
                }
            }
        });
        choice.add(LittleLegion.EXTENDED_DESCRIPTION[3], LittleLegion.EXTENDED_DESCRIPTION[4], () -> {
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 2));
                }
            }
        });
        choice.add(LittleLegion.EXTENDED_DESCRIPTION[5], LittleLegion.EXTENDED_DESCRIPTION[6], () -> {
            for (int i = (AbstractDungeon.player.orbs.size() - 1); i >= 0; i--) {
                if (AbstractDungeon.player.orbs.get(i) instanceof NingyouOrb) {
                    AbstractDungeon.actionManager.addToBottom(new ChangeOrbAction(i, 3));
                }
            }
        });
        AbstractDungeon.actionManager.addToBottom(choice);
    }

    public AbstractCard makeCopy() {
        return new LittleLegion();
    }

    public void upgrade() {
        if (!(this.upgraded)) {
            this.upgradeName();
            this.upgradeBaseCost(2);
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("LittleLegion");
        NAME = LittleLegion.cardStrings.NAME;
        DESCRIPTION = LittleLegion.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = LittleLegion.cardStrings.EXTENDED_DESCRIPTION;
    }
}
