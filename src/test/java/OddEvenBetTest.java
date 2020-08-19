import Games.RouletteCollection.Roulette;
import Games.RouletteCollection.OddEvenBet;
import Games.RouletteCollection.RouletteSetUp;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class OddEvenBetTest {

    OddEvenBet oddEvenBet;
    Roulette roulette;
    Scanner scanner;
    Player player;

    @Before
    public void setUp(){
        oddEvenBet = new OddEvenBet();
        roulette = new Roulette(new Dealer("Toby", 35));
        player = new Player("John", 20, 100);
    }

    //Test: Getters

    @Test
    public void canGetBetType(){
        player.addChips(20);
        scanner = new Scanner("10\neven");
        oddEvenBet.makeBet(scanner, player);
        assertTrue(oddEvenBet.getOddOREven().equalsIgnoreCase("even"));
    }

    @Test
    public void canGetBetAmount(){
        player.addChips(20);
        scanner = new Scanner("5\nodd");
        oddEvenBet.makeBet(scanner, player);
        assertEquals(5, oddEvenBet.getBetAmount());
    }

    @Test
    public void betIsCorrect(){
        //Given; WE have a roulette Game
        //And: we have spun for a value
        RouletteSetUp rouletteValue = roulette.spin();

        player.addChips(20);
        scanner = new Scanner("10\neven");
        oddEvenBet.makeBet(scanner, player);

        //When: we test isBetTrue

        //Then: Expect to be False
        assertTrue(oddEvenBet.isBetSuccessful(rouletteValue));

    }

    @Test
    public void betIsNotSuccessful(){
        //Given: we have a roulette game
        //And: we have spun for a value
        RouletteSetUp rouletteValue = roulette.spin();
        //And: We have a bet
        oddEvenBet = new OddEvenBet();

        player.addChips(20);
        scanner = new Scanner("10\nodd");
        oddEvenBet.makeBet(scanner, player);

        //When: we test if bet is successful
        //Then: Expect Result False
        assertFalse(oddEvenBet.isBetSuccessful(rouletteValue));
    }



}
