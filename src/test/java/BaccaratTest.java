import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import Games.BaccaratCollection.Baccarat;
import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class BaccaratTest {

    Baccarat baccarat;
    Dealer dealer;
    Player player;
    Card card;
    Card card2;
    Card card3;

    @Before
    public void setup(){
        dealer = new Dealer("Bob", 30);
        player = new Player("Sarah",30, 500);
        baccarat = new Baccarat(dealer, 4);
    }

    @Test
    public void canGetNumberOfPlayers() {
        assertEquals(0, baccarat.getNumberOfPlayers());
    }
    @Test
    public void canGetPlayers() {
        assertNotNull(baccarat.getPlayers());
    }
    @Test
    public void canGetDealer() {
        assertEquals(dealer, baccarat.getDealer());
    }
    @Test
    public void canGetCapacity() {
        assertEquals(4, baccarat.getCapacity());
    }

    @Test
    public void canAddPlayer() {
        baccarat.addPlayer(player);
        assertEquals(1, baccarat.getNumberOfPlayers());
    }

    @Test
    public void canRemovePlayerIfThere() {
        baccarat.addPlayer(player);
        baccarat.removePlayer(player);
        assertEquals(0, baccarat.getNumberOfPlayers());
    }
    @Test
    public void cantRemovePlayerIfNotThere() {
        baccarat.removePlayer(player);
        assertEquals(0, baccarat.getNumberOfPlayers());
    }

    @Test
    public void canGetHand(){
        dealer.getDeck().addDeck();
        baccarat.initialDeal(player, dealer);
        assertEquals(2, player.getNumberOfCards());
        assertEquals(2, dealer.getNumberOfCards());
    }

    @Test
    public void canGetPlayerHandTotalBelowTen(){
        Card card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.FIVE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertEquals(8, baccarat.getHandTotal(player));
    }

    @Test
    public void canGetPlayerHandTotalAboveTen(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.FIVE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertEquals(5, baccarat.getHandTotal(player));
    }

    @Test
    public void DrawCardTrueIfLessThanFive(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertTrue(baccarat.isThirdCardDrawnToPlayer(player));
    }

    @Test
    public void DrawCardFalseIfGreaterThanFive(){
        Card card = new Card(CardRank.JACK, CardSuit.DIAMONDS);
        Card card2 = new Card(CardRank.SEVEN,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        assertFalse(baccarat.isThirdCardDrawnToPlayer(player));
    }

    @Test
    public void DealerCardTotalIsLessThanOrEqualToTwo(){
        card = new Card(CardRank.ACE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.ACE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToThreeAndPlayerThirdCardIsNotEqualToEight(){
        card = new Card(CardRank.ACE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToThreeAndPlayerThirdCardIsEqualToEight(){
        card = new Card(CardRank.ACE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.EIGHT,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToFourAndPlayerThirdCardIsGreaterThanOneAndLessThanEight(){
        card = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.THREE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToFourAndPlayerThirdCardIsNotGreaterThanOneAndLessThanEight(){
        card = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.TEN,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToFiveAndPlayerThirdCardIsGreaterThanThreeAndLessThanEight(){
        card = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.FOUR,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToFiveAndPlayerThirdCardIsNotGreaterThanThreeAndLessThanEight(){
        card = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.ACE,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToSixAndPlayerThirdCardIsGreaterThanFiveAndLessThanEight(){
        card = new Card(CardRank.FOUR, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.SIX,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsEqualToSixAndPlayerThirdCardIsNotGreaterThanFiveAndLessThanEight(){
        card = new Card(CardRank.FOUR, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.TWO,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsGreaterOrEqualToSeven(){
        card = new Card(CardRank.FOUR, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        card3 = new Card(CardRank.SIX,CardSuit.SPADES);
        player.addCards(card);
        player.addCards(card2);
        player.addCards(card3);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsLessThanSix(){
        card = new Card(CardRank.TWO, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        player.addCards(card);
        player.addCards(card2);
        assertFalse(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void DealerCardTotalIsGreaterOrEqualToSix(){
        card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        dealer.addCards(card2);
        player.addCards(card);
        player.addCards(card2);
        assertTrue(baccarat.isThirdCardDrawnToDealer(dealer, player));
    }

    @Test
    public void playerDraws(){
        card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.THREE,CardSuit.SPADES);
        dealer.addCards(card);
        player.addCards(card2);
        assertEquals(BaccaratOutcome.TIE, baccarat.getWinner(player, dealer));
    }

    @Test
    public void playerLoses(){
        card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.TWO,CardSuit.SPADES);
        dealer.addCards(card);
        player.addCards(card2);
        assertEquals(BaccaratOutcome.DEALER, baccarat.getWinner(player, dealer));
    }

    @Test
    public void playerWins(){
        card = new Card(CardRank.THREE, CardSuit.DIAMONDS);
        card2 = new Card(CardRank.FOUR,CardSuit.SPADES);
        dealer.addCards(card);
        player.addCards(card2);
        assertEquals(BaccaratOutcome.PLAYER, baccarat.getWinner(player, dealer));
    }

    @Test
    public void playerCanMakeBetOnPlayerWin(){
        Scanner scanner = new Scanner("player\n20");
        player.addChips(20);
        baccarat.addPlayer(player);
        baccarat.playersMakeBets(scanner);
        BaccaratBet bet = baccarat.getBetList().get(0);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.PLAYER, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerLose(){
        Scanner scanner = new Scanner("dealer\n20");
        player.addChips(20);
        baccarat.addPlayer(player);
        baccarat.playersMakeBets(scanner);
        BaccaratBet bet = baccarat.getBetList().get(0);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.DEALER, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerTie(){
        Scanner scanner = new Scanner("tie\n20");
        player.addChips(20);
        baccarat.addPlayer(player);
        baccarat.playersMakeBets(scanner);
        BaccaratBet bet = baccarat.getBetList().get(0);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.TIE, bet.getBaccaratOutcome());
    }

    @Test
    public void playerMakesWrongBet(){
        Scanner scanner = new Scanner("drw\ntie\n10");
        player.addChips(10);
        baccarat.addPlayer(player);
        baccarat.playersMakeBets(scanner);
        BaccaratBet bet = baccarat.getBetList().get(0);
        assertEquals(10, bet.getBetAmount());
        assertEquals(BaccaratOutcome.TIE, bet.getBaccaratOutcome());
    }

    @Test
    public void playerMakesCorrectBetAndWinsGame(){
        Scanner scanner = new Scanner("player\n10");
        player.addChips(10);
        baccarat.addPlayer(player);
        dealer.getDeck().addDeck();
        baccarat.play(scanner);
        assertEquals(20, player.getNumberOfChips());
        assertEquals(990, dealer.getNumberOfChips());
    }

    @Test
    public void playerMakesWrongBetAndWinsGame(){
        Scanner scanner = new Scanner("tie\n10");
        player.addChips(10);
        baccarat.addPlayer(player);
        dealer.getDeck().addDeck();
        baccarat.play(scanner);
        assertEquals(0, player.getNumberOfChips());
        assertEquals(1010, dealer.getNumberOfChips());
    }



}
