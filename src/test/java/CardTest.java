import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
    Card card;
    Card card2;

    @Before
    public void before() {
        card = new Card(CardRank.ACE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.ACE, CardSuit.HEARTS);
    }

    @Test
    public void canGetRank() {
        assertEquals(CardRank.ACE, card.getRank());
    }

    @Test
    public void canGetSuit() {
        assertEquals(CardSuit.DIAMONDS, card.getSuit());
    }

    @Test
    public void canGetRankValue() {
        assertEquals(1, card.getRankValue());
    }

    @Test
    public void canChangeRankForAll() {
        card.changeValue(11);

        assertEquals(11, card.getRankValue());
        assertEquals(11, card2.getRankValue());
        assertEquals(11, CardRank.ACE.getValue());
    }
}
