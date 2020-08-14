import Games.Roulette;
import People.Dealer;
import People.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RouletteTest {
    Roulette roulette;
    Dealer dealer;
    ArrayList<Integer> betNumbers;
    Player player;

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

    /*@Test
    public void canPlay() {
        //given we have a player with 20 chips
        player.addChips(20);
        //and a list of two bets
        //and the player has placed the bet
        roulette.bet(10, betNumbers, player);

        //when play is called with a result of 11
        roulette.play();

        //then the player should have 20 chips
        assertEquals(20, player.getNumberOfChips());
        // the dealer should have 1010 chips
        assertEquals(1010, dealer.getNumberOfChips());
    }*/

    @Test
    public void canSpin() {
        //when seeded with 3
        int result = roulette.spin();
        assertEquals(17, result);
    }


    @Test
    public void canSetUpRouletteStartList(){
        //Given:
        //When: I create a roulette game
        //Then: I expect there to be a list of 36 Roulette Set up items
        assertEquals(36, roulette.getStartPoint().size());
    }
}
