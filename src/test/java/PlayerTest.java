import Casino.Casino;
import DeckOfCardsClasses.Card;
import DeckOfCardsClasses.CardRank;
import DeckOfCardsClasses.CardSuit;
import Games.BaccaratCollection.Baccarat;
import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import Games.Roulette;
import Games.RouletteCollection.ColourBet;

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
        player.buyChips();
        Assert.assertEquals(20, player.getNumberOfChips());
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
        Assert.assertEquals(150, player.getMoneyInWallet(),0.0);
    }

    @Test
    public void canGetHand(){
        ArrayList<Card> returnedHand = player.getHand();
        Assert.assertEquals(0, returnedHand.size());
    }

    @Test
    public void canAddIRouletteBetToBetList(){
        IRouletteBet bet = new ColourBet("red", 8);
        //When: I add bet topo list
        player.addRouletteBet(bet);
        //Then: I expect there to be one bet in the list
        assertEquals(1, player.betList().size());
    }


    @Test
    public void canRemoveBetFromList(){
        IRouletteBet bet = new ColourBet("red", 8);
        player.addRouletteBet(bet);
        IRouletteBet bet2 = new ColourBet("red", 8);
        player.addRouletteBet(bet2);

        //When: I remove a bet
        player.removeRouletteBet();

        //Then: I expect just one bet left
        assertEquals(1,player.betList().size());
    }

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
        player.buyChips();
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
        player.buyChips();
        //And: The Player wishes to make a Bet and has entered the bet ammount.
        // Scanner scanner = new Scanner ("24\n");
        //When: IO check if they have enough chips to make bet
        //Then: Expect true
        assertFalse(player.hasEnoughChipsToBet(24));
    }

    @Test
    public void playerCanMakeBetOnPlayerWin(){
        Scanner scanner = new Scanner("win\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.WIN, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerLose(){
        Scanner scanner = new Scanner("loss\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);

        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.LOSS, bet.getBaccaratOutcome());
    }

    @Test
    public void playerCanMakeBetOnPlayerDraw(){
        Scanner scanner = new Scanner("draw\n20");
        player.addChips(20);

        BaccaratBet bet = player.makeBaccaratBet(scanner);
        assertEquals(20, bet.getBetAmount());
        assertEquals(BaccaratOutcome.DRAW, bet.getBaccaratOutcome());
    }


}
