import Games.Roulette;
import Games.RouletteCollection.ColourBet;
import Games.RouletteCollection.RouletteSetUp;
import People.Dealer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColourTestBet {

    ColourBet colourBet;

    @Before
    public  void setUp(){

        //To make bet need both colour and bet ammount
        colourBet= new ColourBet("red", 4);
    }

    @Test
    public void canGetBetAmount(){
        assertEquals(4, colourBet.getBetAmount(), 0);
    }

    @Test
    public void canGetColourBetOn(){
        assertEquals("red", colourBet.getColourBetOn());
    }

    //CASE: I Have bet on the correct colour
    @Test
    public void isCorrectColour(){
        //Given: we have a Roulette Game and dealer
        Roulette roulette = new Roulette(new Dealer("Matty", 25));
        //And: the game has a roulette setup and we have spun
        RouletteSetUp rouletteValue = roulette.spin();
        //When: I pass the roulette spin into is successful
        //Then: I expect the result to be true
        assertTrue(colourBet.isBetSuccessful(rouletteValue));
    }

    //Case: I have bet on the wrong colour
    @Test
    public void isWrongColour(){
        //Given: we have a roulette Game and dealer
        Roulette roulette = new Roulette(new Dealer("Matty", 25));
        //And: the game has a roulette setup and we have spun
        RouletteSetUp rouletteValue = roulette.spin();
        //And our bet is on the wrong colour
        colourBet = new ColourBet("black", 4);
        //When: I test if the same
        //Then: assert False
        assertFalse(colourBet.isBetSuccessful(rouletteValue));
    }

}
