import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardDeck;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import Games.BlackJackCollection.Blackjack;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class BlackJackTest {

    Blackjack blackjack;
    Dealer dealer;
    Player player;


    @Before
    public void setUp(){
        dealer = new Dealer("John",40);
        player = new Player("Sarah",30, 500);
        blackjack = new Blackjack(dealer, 10);

    }

    @Test
    public void canGetNumberOfPlayers() {
        assertEquals(0, blackjack.getNumberOfPlayers());
    }
    @Test
    public void canGetPlayers() {
        assertNotNull(blackjack.getPlayers());
    }
    @Test
    public void canGetDealer() {
        assertEquals(dealer, blackjack.getDealer());
    }
    @Test
    public void canGetCapacity() {
        assertEquals(10, blackjack.getCapacity());
    }

    @Test
    public void canAddPlayer() {
        blackjack.addPlayer(player);
        assertEquals(1, blackjack.getNumberOfPlayers());
    }

    @Test
    public void canRemovePlayerIfThere() {
        blackjack.addPlayer(player);
        blackjack.removePlayer(player);
        assertEquals(0, blackjack.getNumberOfPlayers());
    }
    @Test
    public void cantRemovePlayerIfNotThere() {
        blackjack.removePlayer(player);
        assertEquals(0, blackjack.getNumberOfPlayers());
    }


    @Test
    public void canGetPlayerHandTotal(){
        Card card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.FIVE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertEquals(8, blackjack.getHandTotal(player));
    }

    @Test
    public void canCheckIfPlayerIsBust(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.QUEEN,CardSuit.SPADES);
        Card card3 = new Card(CardRank.FIVE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(blackjack.isBust(player));
    }
    @Test
    public void canCheckIfPlayerIsNotBust(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.QUEEN,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertFalse(blackjack.isBust(player));
    }

    @Test
    public void doesPlayerAceValueChangeToOne(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        Card card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card3);
        blackjack.changeAceValue(player);
        assertEquals(19, blackjack.getHandTotal(player));
    }

    @Test
    public void doesDealerAceValueChangeToOne(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.SIX,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        Card card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        dealer.addCards(card3);
        blackjack.changeAceValue(dealer);
        assertEquals(17, blackjack.getHandTotal(dealer));
    }

    @Test
    public void doesPlayerAceValueChangeToOneTwice(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        Card card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card3);
        Card card4 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card4);
        blackjack.changeAceValue(player);
        assertEquals(20, blackjack.getHandTotal(player));
    }

    @Test
    public void doesDealerAceValueChangeToOneTwice(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        Card card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        dealer.addCards(card3);
        Card card4 = new Card(CardRank.ACE,CardSuit.SPADES);
        dealer.addCards(card4);
        blackjack.changeAceValue(dealer);
        assertEquals(15, blackjack.getHandTotal(dealer));
    }

    @Test
    public void doesDealerHaveTwoCards(){
        dealer.getDeck().addDeck();
        blackjack.initialDealerDeal();
        assertEquals(2, dealer.getNumberOfCards());
    }

    @Test
    public void canGetDealerInitialScore(){
        dealer.getDeck().addDeck();
        blackjack.initialDealerDeal();
        assertEquals(13, blackjack.getHandTotal(dealer));
    }
    @Test
    public void canGetDealerScore(){
        dealer.getDeck().addDeck();
       blackjack.initialDealerDeal();
       blackjack.getDealersScore();
       assertEquals(4,dealer.getNumberOfCards());
       assertEquals(20, blackjack.getHandTotal(dealer));
    }
    @Test
    public void canGetPlayerScore(){
        Scanner scanner = new Scanner("hit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        blackjack.initialPlayerDeal();
        blackjack.getPlayerScore(scanner, player);
        assertEquals(3, player.getNumberOfCards());
        assertEquals(16, blackjack.getHandTotal(player));
    }

//    @Test
//    public void PlayerStands(){
//        Scanner scanner = new Scanner("10\nstand");
//        dealer.getDeck().addDeck();
//        blackjack.addPlayer(player);
//        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
//        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
//        player.addCards(card);
//        player.addCards(card2);
//        Card card3 = new Card (CardRank.SEVEN, CardSuit.HEARTS);
//        dealer.addCards(card3);
//        dealer.addCards(card);
//        blackjack.play(scanner);
//        assertEquals(17,blackjack.getHandTotal(dealer));
//        assertEquals(18, blackjack.getHandTotal(player));
//        assertEquals(10, player.getNumberOfChips());
//        assertEquals(990, dealer.getNumberOfChips());
//    }
    @Test
    public void canPlayerHitsOnce(){
        Scanner scanner = new Scanner("10\nhit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        player.addChips(10);
        blackjack.play(scanner);
        assertEquals(0,blackjack.getHandTotal(dealer));
        assertEquals(0, blackjack.getHandTotal(player));
        assertEquals(0, player.getNumberOfChips());
        assertEquals(1010, dealer.getNumberOfChips());
    }
    @Test
    public void canPlayerGoBust(){
        Scanner scanner = new Scanner("10\nhit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        player.addChips(10);
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
        Card card3 = new Card (CardRank.SEVEN, CardSuit.HEARTS);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        blackjack.play(scanner);
        assertEquals(0, blackjack.getHandTotal(player));
        assertEquals(0, player.getNumberOfChips());
    }

    @Test
    public void playerWinsIfDealerGoesBust(){
        Scanner scanner = new Scanner("20\nhit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        player.addChips(20);
        Card card = new Card(CardRank.THREE, CardSuit.DIAMONDS);

        dealer.addCards(card);
        blackjack.play(scanner);
        assertEquals(980, dealer.getNumberOfChips());
        assertEquals(0,blackjack.getHandTotal(dealer));
        assertEquals(0, blackjack.getHandTotal(player));
        assertEquals(40, player.getNumberOfChips());
    }

    @Test
    public void dealerWinsIfThePlayerHasntGoneBust(){
        Scanner scanner = new Scanner("10\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        player.addChips(10);
        blackjack.play(scanner);
        assertEquals(0,blackjack.getHandTotal(player));
        assertEquals(0, blackjack.getHandTotal(dealer));
        assertEquals(1010, dealer.getNumberOfChips());
        assertEquals(0, player.getNumberOfChips());
    }

    }












