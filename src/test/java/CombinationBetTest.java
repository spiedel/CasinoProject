import Games.RouletteCollection.*;
import Interfaces.IRouletteBet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CombinationBetTest {

    CombinationBet combinationBet;
    IRouletteBet bet;
    IRouletteBet bet2;

    @Before
    public void setUp(){
        bet = new ColourBet();
        bet2 = new OddEvenBet();
        combinationBet = new CombinationBet(bet,bet2);
    }

    @Test
    public void canGetBetAmount(){
        assertEquals(11,combinationBet.getBetAmount());
    }


    //Case: Both conditions correct. The Colour is black and the number is even.
    @Test
    public void bothConditionsMet(){
        assertTrue(combinationBet.isBetSuccessful(RouletteSetUp.Eight));
    }


    //Case: One Condition Correct, the other wrong
    @Test
    public void oneConditionMet(){
        assertFalse(combinationBet.isBetSuccessful(RouletteSetUp.Eighteen));
    }

    //Case: Neither Condition is met
    @Test
    public void noConditionsMet(){
        assertFalse(combinationBet.isBetSuccessful(RouletteSetUp.Five));
    }
}
