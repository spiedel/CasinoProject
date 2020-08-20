package People;

import DeckOfCardsClasses.Card;

import java.util.ArrayList;

public abstract class Person {

    private String name;
    private int age;
    protected int numberOfChips;
    protected ArrayList<Card> hand;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNumberOfChips() {
        return numberOfChips;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addChips(int chips){
        numberOfChips += chips;
    }

    public void removeChips(int chips){
        numberOfChips -= chips;
    }

    public boolean isOldEnoughToEnter(){
        if(getAge() >= 18){
            return true;
        } else{
            return false;
        }
    }

    public void addCards(Card card){
        hand.add(card);
    }

    public Card pickCard(Card card){
        if(hand.contains(card)) {
            int index = hand.indexOf(card);
            return hand.remove(index);
        }else{
            return null;
        }
    }

    public int getNumberOfCards(){
        return hand.size();
    }


}
