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
    CardDeck cardDeck;

    @Before
    public void setUp(){
        cardDeck = new CardDeck();
        dealer = new Dealer("John",40);
        player = new Player("Sarah",30, 500);
        blackjack = new Blackjack(cardDeck, dealer, 10);

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
    public void canGetCardDeck(){
        blackjack.getCardDeck();
        assertEquals(cardDeck,blackjack.getCardDeck());
    }
    @Test
    public void canAddCardsToDeck(){
        blackjack.getCardDeck().addDeck();
        assertEquals(52,blackjack.getCardDeck().getNumberOfCards());
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
    public void doesDealerHaveTwoCards(){
        dealer.getDeck().addDeck();
        blackjack.initialDealerDeal();
        assertEquals(2, dealer.getNumberOfCards());
    }

    @Test
    public void canGetDealerInitialScore(){
        dealer.getDeck().addDeck();
        blackjack.initialDealerDeal();
        assertEquals(3, blackjack.getHandTotal(dealer));
    }
    @Test
    public void canGetDealerScore(){
        dealer.getDeck().addDeck();
       blackjack.initialDealerDeal();
       blackjack.getDealersScore();
       assertEquals(6,dealer.getNumberOfCards());
       assertEquals(21, blackjack.getHandTotal(dealer));
    }
    @Test
    public void canGetPlayerScore(){
        Scanner scanner = new Scanner("hit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        blackjack.initialPlayerDeal();
        blackjack.getPlayerScore(scanner, player);
        assertEquals(3, player.getNumberOfCards());
        assertEquals(6, blackjack.getHandTotal(player));
    }

    @Test
    public void PlayerStands(){
        Scanner scanner = new Scanner("stand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        Card card3 = new Card (CardRank.SEVEN, CardSuit.HEARTS);
        dealer.addCards(card3);
        dealer.addCards(card);
        blackjack.play(scanner);
        assertEquals(17,blackjack.getHandTotal(dealer));
        assertEquals(18, blackjack.getHandTotal(player));
        assertEquals(10, player.getNumberOfChips());
        assertEquals(990, dealer.getNumberOfChips());
    }
    @Test
    public void canPlayerHitsOnce(){
        Scanner scanner = new Scanner("hit\nstand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        Card card3 = new Card (CardRank.SEVEN, CardSuit.HEARTS);
        dealer.addCards(card3);
        dealer.addCards(card);
        blackjack.play(scanner);
        assertEquals(17,blackjack.getHandTotal(dealer));
        assertEquals(19, blackjack.getHandTotal(player));
        assertEquals(10, player.getNumberOfChips());
        assertEquals(990, dealer.getNumberOfChips());
    }
    @Test
    public void canPlayerGoBust(){
        Scanner scanner = new Scanner("hit\nstand");
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
        assertEquals(25, blackjack.getHandTotal(player));
        assertEquals(0, player.getNumberOfChips());
    }

    @Test
    public void playerWinsIfDealerGoesBust(){
        Scanner scanner = new Scanner("hit\nstand");
        dealer.getDeck().addCardsToDeck(new Card(CardRank.JACK, CardSuit.HEARTS));
        dealer.getDeck().addCardsToDeck(new Card(CardRank.JACK, CardSuit.CLUBS));
        blackjack.addPlayer(player);
        Card card = new Card(CardRank.SEVEN, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        blackjack.play(scanner);
        assertEquals(990, dealer.getNumberOfChips());
        assertEquals(25,blackjack.getHandTotal(dealer));
        assertEquals(10, blackjack.getHandTotal(player));
        assertEquals(10, player.getNumberOfChips());
    }

    @Test
    public void dealerWinsIfThePlayerHasntGoneBust(){
        Scanner scanner = new Scanner("stand");
        dealer.getDeck().addDeck();
        blackjack.addPlayer(player);
        player.addChips(10);
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.EIGHT, CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        Card card3 = new Card (CardRank.SEVEN, CardSuit.HEARTS);
        player.addCards(card3);
        player.addCards(card);
        blackjack.play(scanner);
        assertEquals(17,blackjack.getHandTotal(player));
        assertEquals(18, blackjack.getHandTotal(dealer));
        assertEquals(1010, dealer.getNumberOfChips());
        assertEquals(0, player.getNumberOfChips());
    }

    }












