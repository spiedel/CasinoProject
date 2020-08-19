import Games.RouletteCollection.*;
import Interfaces.IRouletteBet;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class CombinationBetTest {

    CombinationBet combinationBet;
    IRouletteBet bet;
    IRouletteBet bet2;
    Scanner scanner;
    Player player;

    @Before
    public void setUp(){
        bet = new ColourBet();
        bet2 = new OddEvenBet();
        combinationBet = new CombinationBet();
        player = new Player("John", 20, 100);
    }

    @Test
    public void canGetBetAmount(){
        player.addChips(20);
        scanner = new Scanner("1\n4\nred\n2\n10\nodd");
        combinationBet.makeBet(scanner, player);
        assertEquals(14,combinationBet.getBetAmount());
    }


    //Case: Both conditions correct. The Colour is black and the number is even.
    @Test
    public void bothConditionsMet(){
        player.addChips(20);
        scanner = new Scanner("1\n4\nblack\n2\n10\neven");
        combinationBet.makeBet(scanner, player);
        assertTrue(combinationBet.isBetSuccessful(RouletteSetUp.Eight));
    }


    //Case: One Condition Correct, the other wrong
    @Test
    public void oneConditionMet(){
        player.addChips(20);
        scanner = new Scanner("1\n4\nblack\n2\n10\nodd");
        combinationBet.makeBet(scanner, player);
        assertFalse(combinationBet.isBetSuccessful(RouletteSetUp.Eighteen));
    }

    //Case: Neither Condition is met
    @Test
    public void noConditionsMet(){
        player.addChips(20);
        scanner = new Scanner("1\n4\nblack\n2\n10\neven");
        combinationBet.makeBet(scanner, player);
        assertFalse(combinationBet.isBetSuccessful(RouletteSetUp.Five));
    }
}
