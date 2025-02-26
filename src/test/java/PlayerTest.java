import Casino.Casino;
import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import Games.RouletteCollection.Roulette;

//import Games.RouletteCollection.IRouletteBet;
//import Games.RouletteCollection.OddEvenBet;
import People.Dealer;

import Interfaces.IRouletteBet;

import People.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    Scanner scanner;

    @Before
    public void setup(){
        player = new Player("Bob", 50, 100);
    }

    @Test
    public void canGetName(){
        Assert.assertEquals("Bob", player.getName());
    }

    @Test
    public void canGetAge(){
        Assert.assertEquals(50, player.getAge());
    }

    @Test
    public void canGetNumberOfChips(){
        Assert.assertEquals(0, player.getNumberOfChips());
    }

    @Test
    public void canGetStartingMoney(){
        Assert.assertEquals(100, player.getMoneyInWallet(), 0.0);
    }

    @Test
    public void canAddCardsToHand(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        player.addCards(card);
        Assert.assertEquals(1, player.getNumberOfCards());
    }

    @Test
    public void canPickCards(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        player.addCards(card);
        Card pickedCard = player.pickCard(card);
        Assert.assertEquals(pickedCard, card);
        Assert.assertEquals(0, player.getNumberOfCards());
    }

    @Test
    public void cantPickCards(){
        Card card = new Card(CardRank.FIVE, CardSuit.HEARTS);
        Card pickedCard = player.pickCard(card);
        assertNull(pickedCard);
    }

    @Test
    public void canEnterCasino(){
        assertTrue(player.isOldEnoughToEnter());
    }

    @Test
    public void cantEnterCasino(){
        Player player2 = new Player("Sally", 12,100);
        assertFalse(player2.isOldEnoughToEnter());
    }

    @Test
    public void canBuyChips(){
        scanner = new Scanner("20");
        player.buyChips(scanner);
        Assert.assertEquals(100, player.getNumberOfChips());
    }

    @Test
    public void cantBuyChipsIfNotEnoughMoney(){
        scanner = new Scanner("1000\n20");
        player.buyChips(scanner);
        Assert.assertEquals(100, player.getNumberOfChips());
    }

    @Test
    public void canAddChips(){
        player.addChips(10);
        Assert.assertEquals(10, player.getNumberOfChips());
    }
    @Test
    public void canRemoveChips() {
        player.addChips(300);
        player.removeChips(200);
        assertEquals(100, player.getNumberOfChips());
    }

    @Test
    public void canCashInChips(){
        player.addChips(10);
        player.cashInChips();
        Assert.assertEquals(102, player.getMoneyInWallet(),0.0);
    }

    @Test
    public void canGetHand(){
        ArrayList<Card> returnedHand = player.getHand();
        Assert.assertEquals(0, returnedHand.size());
    }

//    @Test
//    public void canAddIRouletteBetToBetList(){
//        IRouletteBet bet = new ColourBet("red", 8);
//        //When: I add bet topo list
//        player.addRouletteBet(bet);
//        //Then: I expect there to be one bet in the list
//        assertEquals(1, player.betList().size());
//    }


//    @Test
//    public void canRemoveBetFromList(){
//        IRouletteBet bet = new ColourBet("red", 8);
//        player.addRouletteBet(bet);
//        IRouletteBet bet2 = new ColourBet("red", 8);
//        player.addRouletteBet(bet2);
//
//        //When: I remove a bet
//        player.removeRouletteBet();
//
//        //Then: I expect just one bet left
//        assertEquals(1,player.betList().size());
//    }

    @Test
    public void playerCanChooseGameAndAddedToGame(){
        Casino casino= new Casino();
        Player player = new Player ("John",21,600);
        Roulette roulette = new Roulette(new Dealer("John", 40));
        Scanner scanner = new Scanner("1\n");
        casino.addGameToCasino(1, roulette);
        player.chooseGame(scanner, casino.getGames());
        assertEquals(1, roulette.getNumberOfPlayers());

    }


    //CASE: Player has enough chips to make a bet
    @Test
    public void canMakeBet(){

        //Given: we have a player
        //And: They have converted their money to chips
        player.addChips(4);
        //And: The Player wishes to make a Bet
        //Scanner scanner = new Scanner ("4\n");
        //When: IO check if they have enough chips to make bet
        //Then: Expect true
        assertTrue(player.hasEnoughChipsToBet(4));
    }
    //CASE: Player does not have  enough chips to make a bet
    @Test
    public void canNotMakeBet(){
        //Given: we have a player
        //And: They have converted their money to chips
        player.addChips(23);
        //And: The Player wishes to make a Bet and has entered the bet ammount.
        // Scanner scanner = new Scanner ("24\n");
        //When: IO check if they have enough chips to make bet
        //Then: Expect true
        assertFalse(player.hasEnoughChipsToBet(24));
    }

    @Test
    public void playerCanMakeBetOnPlayerWin(){
        Scanner scanner = new Scanner("player\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.PLAYER, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerLose(){
        Scanner scanner = new Scanner("dealer\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);

        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.DEALER, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerDraw(){
        Scanner scanner = new Scanner("tie\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.TIE, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeColourBet(){
        Scanner scanner = new Scanner("1\n10\nred");
        player.addChips(20);
        IRouletteBet bet = player.makeRouletteBet(scanner);
        assertEquals(10, bet.getBetAmount());
    }


}
