package DeckOfCardsClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private List<Card> cards;

    public CardDeck() {
        cards = new ArrayList<Card>();
    }

    public void addDeck() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void addCardsToDeck(Card card) {
        cards.add(card);
        shuffle();
    }

    public Card removeCardFromDeck() {
        return cards.remove(0);
    }

    public void clear() {
        cards.clear();
    }

}
