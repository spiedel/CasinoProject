import Games.RouletteCollection.ColourBet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
