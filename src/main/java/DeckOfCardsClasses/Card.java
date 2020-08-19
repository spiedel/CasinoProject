package DeckOfCardsClasses;

public class Card {
    private CardRank rank;
    private CardSuit suit;
    private int value;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = rank.getValue();
    }

    public void changeValue(int value) {
        rank.setValue(value);
    }

    public int getRankValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
