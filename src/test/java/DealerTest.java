import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import People.Dealer;
import People.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealerTest {


    Dealer dealer;

    @Before
    public void setup(){
        dealer = new Dealer("Bob", 50);
    }

    @Test
    public void canGetName(){
        Assert.assertEquals("Bob", dealer.getName());
    }

    @Test
    public void canGetAge(){
        Assert.assertEquals(50, dealer.getAge());
    }

    @Test
    public void canGetNumberOfChips(){
        Assert.assertEquals(1000, dealer.getNumberOfChips());
    }

    @Test
    public void canGetDeck(){
        assertNotNull(dealer.getDeck());
    }

    @Test
    public void canAddCardsToDeck(){
        dealer.getDeck().addDeck();
        assertEquals(52,dealer.getDeck().getNumberOfCards());
    }


    @Test
    public void canAddCardsToHand(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        dealer.addCards(card);
        Assert.assertEquals(1, dealer.getNumberOfCards());
    }

    @Test
    public void canPickCards(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        dealer.addCards(card);
        Card pickedCard = dealer.pickCard(card);
        Assert.assertEquals(pickedCard, card);
        Assert.assertEquals(0, dealer.getNumberOfCards());
    }

    @Test
    public void cantPickCards(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        Card pickedCard = dealer.pickCard(card);
        assertNull(pickedCard);
    }


    @Test
    public void canGetHand(){
        ArrayList<Card> returnedHand = dealer.getHand();
        Assert.assertEquals(0, returnedHand.size());
    }


    @Test
    public void canAddChips(){
        dealer.addChips(10);
        Assert.assertEquals(1010, dealer.getNumberOfChips());
    }

    @Test
    public void canRemoveChips(){
        dealer.removeChips(500);
        assertEquals(500,dealer.getNumberOfChips());
    }

    @Test
    public void canDealCardsToPlayer(){
        //Given the dealer has a deck
        Card card = new Card(CardRank.FIVE,CardSuit.HEARTS);
        dealer.getDeck().addCardsToDeck(card);
        //And the deck has 1 card in it
        //And we have a player with no cards
        Player player = new Player("Sarah",20,400);
        //When the dealer deals 1 card to the player
        dealer.dealCard(player);
        //Then the player has that card and the dealer's deck has 0 cards
        assertEquals(1, player.getNumberOfCards());
        Card pickedCard = player.pickCard(card);
        assertEquals(CardRank.FIVE, pickedCard.getRank());
        assertEquals(CardSuit.HEARTS, pickedCard.getSuit());
        assertEquals(0,dealer.getDeck().getNumberOfCards());
    }

    @Test
    public void canDealCardsToDealer(){
        //Given the dealer has a deck
        Card card = new Card(CardRank.FIVE,CardSuit.HEARTS);
        dealer.getDeck().addCardsToDeck(card);
        //And the deck has 1 card in it
        //When the dealer deals 1 card to the player
        dealer.dealCard(dealer);
        //Then the player has that card and the dealer's deck has 0 cards
        assertEquals(1, dealer.getNumberOfCards());
        Card pickedCard = dealer.pickCard(card);
        assertEquals(CardRank.FIVE, pickedCard.getRank());
        assertEquals(CardSuit.HEARTS, pickedCard.getSuit());
        assertEquals(0,dealer.getDeck().getNumberOfCards());
    }

}
