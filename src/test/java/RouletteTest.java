import Games.RouletteCollection.Roulette;
import Games.RouletteCollection.*;
import Interfaces.IRouletteBet;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class RouletteTest {
    Roulette roulette;
    Dealer dealer;
    ArrayList<Integer> betNumbers;
    Player player;
    Scanner scanner;
    ColourBet colourBet;

    @Before
    public void before() {
        dealer = new Dealer("Rob", 40);
        roulette = new Roulette(dealer);
        betNumbers = new ArrayList<Integer>();
        betNumbers.add(17);
        betNumbers.add(12);

        player = new Player("Jill", 28, 100);
    }

    @Test
    public void canGetNumberOfPlayers() {
        assertEquals(0, roulette.getNumberOfPlayers());
    }
    @Test
    public void canGetPlayers() {
        assertNotNull(roulette.getPlayers());
    }
    @Test
    public void canGetDealer() {
        assertEquals(dealer, roulette.getDealer());
    }
    @Test
    public void canGetCapacity() {
        assertEquals(7, roulette.getCapacity());
    }

    @Test
    public void canAddPlayer() {
        roulette.addPlayer(player);
        assertEquals(1, roulette.getNumberOfPlayers());
    }

    @Test
    public void canRemovePlayerIfThere() {
        roulette.addPlayer(player);
        roulette.removePlayer(player);
        assertEquals(0, roulette.getNumberOfPlayers());
    }
    @Test
    public void cantRemovePlayerIfNotThere() {
        roulette.removePlayer(player);
        assertEquals(0, roulette.getNumberOfPlayers());
    }

    /*@Test
    public void canPlaceOneBet() {
        //given you have a player
        //and the player has enough chips for a bet
        player.addChips(20);
        //and they have a bet list
        //when the player makes a bet
        roulette.bet(10, betNumbers, player);

        //their bet is in the list of bets
        HashMap<Player, HashMap<Integer, Integer>> bets = roulette.getBets();

        assertEquals(1, bets.size());
        assertEquals(2, bets.get(player).size());
        int betAmount = bets.get(player).get(17);
        assertEquals(10, betAmount);

        assertEquals(0, player.getNumberOfChips());
    }*/

    @Test
    public void canPlay() {
        //given we have a player with 20 chips
        player.addChips(20);
        scanner = new Scanner("1\n8\nred");
        //And: Player is in our Player List
        roulette.addPlayer(player);

        //and the player has placed the bet
        //IRouletteBet bet = new ColourBet("red", 8);
        //And: the bet is on the player list
        //player.addRouletteBet(bet);

        //when play is called with a result of 11
        roulette.play(scanner);


        //Then: the player should have 28
        assertEquals(28, player.getNumberOfChips());

    }



    @Test
    public void canSpin() {
        //when seeded with 3

        assertEquals(RouletteSetUp.Eighteen,roulette.spin());
    }


    @Test
    public void canSetUpRouletteStartList(){
        //Given:
        //When: I create a roulette game
        //Then: I expect there to be a list of 36 Roulette Set up items
        assertEquals(36, roulette.getStartPoint().size());
    }

    //Case: Player Makes Two Bets: One Colour, and one odd even
    //And: Both bets successful
//    @Test
//    public void canMakeMoreThaOneBet(){
//        //Given: We have a Player that is in the roulette game
//        roulette.addPlayer(player);
//        player.addChips(20);
//        scanner = new Scanner("9\nred");
//        //And: The pLayer has made a colour bet that a red colour will come up
//        //IRouletteBet bet = new ColourBet("red", 9);
//        //Add: The player has made a second bet on the number coming up being even
//        IRouletteBet bet2 = new OddEvenBet();
//        //And: The bets have been added to the players list of bets
//        //player.addRouletteBet(bet);
//        player.addRouletteBet(bet2);
//
//
//        //When: We play the Roulette Game
//        roulette.play(scanner);
//
//        //Then: We expect the player to have
//        assertEquals(14, player.getNumberOfChips());
//    }

    //Case: Two bets and one is successful
//    @Test
//    public void twoBetsMadeOneUnsuccessful(){
//        //Given: We have a Player that is in the roulette game
//        roulette.addPlayer(player);
//        //And: The pLayer has made a colour bet that a red colour will come up
//        //IRouletteBet bet = new ColourBet("black", 9);
//        IRouletteBet bet = new ColourBet();
//        //Add: The player has made a second bet on the number coming up being even
//        IRouletteBet bet2 = new OddEvenBet(5 , "even");
//        //And: The bets have been added to the players list of bets
//        player.addRouletteBet(bet);
//        player.addRouletteBet(bet2);
//
//
//        //When: We play the Roulette Game
//        roulette.play();
//
//        //Then: We expect the player to have
//        assertEquals(-4, player.getNumberOfChips());
//    }



    //Case: Two bets and neither are sucessful
//    @Test
//    public void twoBetsMadeBothUnsuccessful(){
//        //Given: We have a Player that is in the roulette game
//        roulette.addPlayer(player);
//        //And: The pLayer has made a colour bet that a red colour will come up
//        //IRouletteBet bet = new ColourBet("black", 9);
//        //Add: The player has made a second bet on the number coming up being even
//        IRouletteBet bet2 = new OddEvenBet(5 , "odd");
//        //And: The bets have been added to the players list of bets
//        //player.addRouletteBet(bet);
//        player.addRouletteBet(bet2);
//
//
//        //When: We play the Roulette Game
//        roulette.play();
//
//        //Then: We expect the player to have
//        assertEquals(-14, player.getNumberOfChips());
//    }

    //Case: Can handle a combination bet
//    @Test
//    public void playCombinationBet(){
//        //Given: We have a Roulette Game
//        //And: The Game has an active  Player
//        roulette.addPlayer(player);
//        //And: The active Player has made a combination bet believing the number will be black and odd simultaneously
//        IRouletteBet bet = new ColourBet();
//        IRouletteBet bet2 = new OddEvenBet(5 , "odd");
//        IRouletteBet combinationBet = new CombinationBet(bet, bet2);
//        //And: The combination bet is in the players bet list
//        player.addRouletteBet(combinationBet);
//
//        //When: I Play Roulette (seeding gives)
//        roulette.play();
//
//        //Then: I expect the player to loose (number from spin seeding is not odd).
//        assertEquals(-14, player.getNumberOfChips());
//    }



}
