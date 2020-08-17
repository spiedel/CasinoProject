package People;

import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;

public class Dealer extends Person {


    private CardDeck deck;

    public Dealer(String name, int age) {
        super(name, age);
        this.numberOfChips = 1000;
        this.deck = new CardDeck();
    }

    public CardDeck getDeck() {
        return deck;
    }

    public Card dealCard(Person person){
        Card card = deck.removeCardFromDeck();
        person.addCards(card);
        return card;
    }
}
