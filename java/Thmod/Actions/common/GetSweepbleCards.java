package Thmod.Actions.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

import Thmod.ThMod;

public class GetSweepbleCards {
    public GetSweepbleCards(){

    }

    public static CardGroup getSweepbleCards () {
        String cardid;
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (Iterator localIterator = AbstractDungeon.player.masterDeck.group.iterator(); localIterator.hasNext();) {
            AbstractCard c = (AbstractCard)localIterator.next();
            for (Iterator Iterator = ThMod.campids.iterator(); Iterator.hasNext(); ) {
                cardid = (String) Iterator.next();
                if (c.cardID.equals(cardid))
                    retVal.group.add(c);
            }
        }
        return retVal;
    }
}
