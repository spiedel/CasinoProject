package People;

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

    public void dealCard(Person person){
        person.addCards(deck.removeCardFromDeck());
    }
}
