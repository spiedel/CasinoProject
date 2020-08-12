package DeckOfCardsClasses;

public class Card {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public void changeValue(int value) {
        rank.setValue(value);
    }

    public int getRankValue() {
        return this.rank.getValue();
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
