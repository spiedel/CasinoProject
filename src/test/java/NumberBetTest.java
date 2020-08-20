import Games.RouletteCollection.NumberBet;
import Games.RouletteCollection.OddEvenBet;
import Games.RouletteCollection.Roulette;
import Games.RouletteCollection.RouletteSetUp;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class NumberBetTest {

    NumberBet numberBet;
    Roulette roulette;
    Scanner scanner;
    Player player;

    @Before
    public void setUp(){
        numberBet = new NumberBet();
        roulette = new Roulette(new Dealer("Toby", 35));
        player = new Player("John", 20, 100);
    }

    //Test: Getters

    @Test
    public void canGetBetType(){
        player.addChips(20);
        scanner = new Scanner("10\n2");
        numberBet.makeBet(scanner, player);
        assertTrue(numberBet.getNumberBetOn() == 2);
    }

    @Test
    public void canGetBetAmount(){
        player.addChips(20);
        scanner = new Scanner("5\n10");
        numberBet.makeBet(scanner, player);
        assertEquals(5, numberBet.getBetAmount());
    }

    @Test
    public void betIsCorrect(){
        //Given; WE have a roulette Game
        //And: we have spun for a value
        RouletteSetUp rouletteValue = roulette.spin();

        player.addChips(20);
        scanner = new Scanner("10\n18");
        numberBet.makeBet(scanner, player);
        assertTrue(numberBet.isBetSuccessful(rouletteValue));
    }

    @Test
    public void betIsNotSuccessful(){
        //Given: we have a roulette game
        //And: we have spun for a value
        RouletteSetUp rouletteValue = roulette.spin();
        //And: We have a bet
        numberBet = new NumberBet();

        player.addChips(20);
        scanner = new Scanner("10\n7");
        numberBet.makeBet(scanner, player);

        //When: we test if bet is successful
        //Then: Expect Result False
        assertFalse(numberBet.isBetSuccessful(rouletteValue));
    }


}
