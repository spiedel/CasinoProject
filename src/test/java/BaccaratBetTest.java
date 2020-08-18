import Games.BaccaratCollection.BaccaratBet;
import Games.BaccaratCollection.BaccaratOutcome;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BaccaratBetTest {
    BaccaratBet baccaratBet;
    Player player;


    @Before
    public void setup() {
        player = new Player("name", 18, 100);
    }

    @Test
    public void canReturnDrawAmount() {
        baccaratBet = new BaccaratBet(player, 10, BaccaratOutcome.DRAW);
        assertEquals(90, baccaratBet.getReturn());
    }

    @Test
    public void canReturnWinAmount() {
        baccaratBet = new BaccaratBet(player, 10, BaccaratOutcome.WIN);
        assertEquals(10, baccaratBet.getReturn());
    }

    @Test
    public void canReturnLossAmount() {
        baccaratBet = new BaccaratBet(player, 10, BaccaratOutcome.LOSS);
        assertEquals(9, baccaratBet.getReturn());
    }

}
