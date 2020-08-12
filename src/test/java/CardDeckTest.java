import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardDeckTest {
    CardDeck cardDeck;

    @Before
    public void before() {
        cardDeck = new CardDeck();
    }

    @Test
    public void startsWithNoCards() {
        assertEquals(0, cardDeck.getNumberOfCards());
    }

    @Test
    public void canAddDeck() {
        cardDeck.addDeck();
        assertEquals(52, cardDeck.getNumberOfCards());
    }

    @Test
    public void canAddTwoDecks() {
        cardDeck.addDeck();
        cardDeck.addDeck();
        assertEquals(104, cardDeck.getNumberOfCards());
    }

    @Test
    public void canAddCardToDeck() {
        Card card = new Card(CardRank.TWO, CardSuit.CLUBS);
        cardDeck.addCardsToDeck(card);
        assertEquals(1, cardDeck.getNumberOfCards());
    }

    @Test
    public void canRemoveCardFromDeck() {
        //given we have a deck with one card
        Card card = new Card(CardRank.THREE, CardSuit.SPADES);
        cardDeck.addCardsToDeck(card);

        //when we remove the top card
        Card removedCard = cardDeck.removeCardFromDeck();

        //then it has the same suit and rank
        assertEquals(CardRank.THREE, removedCard.getRank());
        assertEquals(CardSuit.SPADES, removedCard.getSuit());

        //and there is no cards left in the deck
        assertEquals(0, cardDeck.getNumberOfCards());
    }
}
